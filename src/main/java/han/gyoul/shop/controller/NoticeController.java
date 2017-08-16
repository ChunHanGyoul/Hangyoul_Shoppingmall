package han.gyoul.shop.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import han.gyoul.shop.board.service.NoticeService;
import han.gyoul.shop.member.dto.MemberDTO;


@Controller
@SessionAttributes({"memberDTO","itemList"})
public class NoticeController {
	@Autowired
	private NoticeService noticeService;

	@RequestMapping("/NoticeMain")
	public String boardMain(Model model, HttpServletRequest request) {
		
		
		
		Map map = model.asMap();
		MemberDTO memberDTO = (MemberDTO)map.get("memberDTO");
		String loginTry = memberDTO.getMb_id();
		String loginTry2 = memberDTO.getMb_pw();
		System.out.println(loginTry);

		if (loginTry != null && loginTry2 != null) {
			if (request.getParameter("page") != null) {
				model.addAttribute("page", Integer.parseInt(request.getParameter("page")));
				String success = "1";
				model.addAttribute("success", success);
			}
		String success = "1";
		model.addAttribute("success", success);
		model.addAttribute("memberDTO", memberDTO);
		noticeService.listService(model);
		return "/notice/board_list";

		}else{
		String success = "0";
		model.addAttribute("success", success);
		return "/mainPage";
		}
		
	}

	@RequestMapping(value = "/NoticeWrite", method = RequestMethod.GET)
	public String boardWirteForm() {
		return "/notice/board_write";
	}

	@RequestMapping(value = "/NoticeWrite", method = RequestMethod.POST)
	public String boardWirte(Model model, HttpServletRequest request) throws IOException {
		noticeService.addService(model, request);
		return boardDetail(model, request);
	}

	@RequestMapping(value = "/NoticeDetail", method = RequestMethod.GET)
	public String boardDetail(Model model, HttpServletRequest request) {
		if (request.getParameter("num") != null) {
			model.addAttribute("num", Integer.parseInt(request.getParameter("num")));
		}
		noticeService.detailService(model);
		return "/notice/board_view";
	}

//	@RequestMapping(value = "/NoticeDownload", method = RequestMethod.GET)
//	public void boardDownload(Model model, HttpServletRequest request, HttpServletResponse response)
//			throws FileNotFoundException {
//		noticeService.downloadService(model, request, response);
//	}

	@RequestMapping(value = "/NoticeModify", method = RequestMethod.GET)
	public String boardModifyDetail(Model model, HttpServletRequest request) {
		if (request.getParameter("num") != null) {
			model.addAttribute("num", Integer.parseInt(request.getParameter("num")));
		}
		noticeService.modifyDetailService(model);
		return "/notice/board_modify";
	}

	@RequestMapping(value = "/NoticeModify", method = RequestMethod.POST)
	public String boardModify(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		noticeService.modifyService(model, request, response);
		return boardDetail(model, request);
	}

//	@RequestMapping(value = "/NoticeReply", method = RequestMethod.GET)
//	public String boardReplyDetail(Model model, HttpServletRequest request) {
//		if (request.getParameter("num") != null) {
//			model.addAttribute("num", Integer.parseInt(request.getParameter("num")));
//		}
//		boardService.replyMoveService(model);
//		return "/notice/board_reply";
//	}

//	@RequestMapping(value = "/NoticeReply", method = RequestMethod.POST)
//	public String boardReply(Model model, HttpServletRequest request) throws IOException {
//		int num = boardService.replyService(model, request);
//		model.addAttribute("num", num);
//		return boardDetail(model, request);
//	}

	@RequestMapping(value = "/NoticeSearchList", method = RequestMethod.POST)
	public String boardSearchList(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		Map map = model.asMap();
		model.addAttribute("keyfield", request.getParameter("keyfield"));
		model.addAttribute("keyword", request.getParameter("keyword"));
		if (request.getParameter("page") != null) {
			model.addAttribute("page", Integer.parseInt(request.getParameter("page")));
		}
		model = noticeService.searchListService(model);
		return "/notice/board_search_list";
	}

	@RequestMapping(value = "/NoticeDelete", method = RequestMethod.GET)
	public String boardDeleteForm(Model model, HttpServletRequest request) {
		if (request.getParameter("num") != null) {
			model.addAttribute("num", Integer.parseInt(request.getParameter("num")));
		}
		return "/notice/board_delete";
	}

	@RequestMapping(value = "/NoticeDelete", method = RequestMethod.POST)
	public String boardDelete(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (request.getParameter("num") != null) {
			model.addAttribute("num", Integer.parseInt(request.getParameter("num")));
		}
		if (request.getParameter("pass") != null) {
			model.addAttribute("pass", Integer.parseInt(request.getParameter("pass")));
		}
		model = noticeService.deleteService(model, response);
		return boardMain(model, request);
	}

//	@RequestMapping(value = "/Reply_Write", method = RequestMethod.POST)
//	public String replyInsert(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
//		request.setCharacterEncoding("UTF-8");
//		int num = Integer.parseInt(request.getParameter("boardNum"));
//		String reply_content = request.getParameter("reply_content");
//		String reply_writer = request.getParameter("reply_writer");
//		String reply_pass = request.getParameter("reply_pass");
//		model.addAttribute("num", num);
//		model.addAttribute("reply_content", reply_content);
//		model.addAttribute("reply_writer", reply_writer);
//		model.addAttribute("reply_pass", reply_pass);
//		boardService.replyAddService(model);
//		return boardDetail(model, request);
//	}

//	@RequestMapping(value = "/ReplyDelete", method = RequestMethod.GET)
//	public String replyDeleteForm(Model model, HttpServletRequest request) {
//		int replyNum=-1;
//		int num=-1;
//		
//		if (request.getParameter("reply_num") != null) {
//			replyNum = Integer.parseInt(request.getParameter("reply_num"));
//			model.addAttribute("reply_num", Integer.parseInt(request.getParameter("reply_num")));
//		}
//		if (request.getParameter("num") != null) {
//			model.addAttribute("num", Integer.parseInt(request.getParameter("num")));
//			num = Integer.parseInt(request.getParameter("num"));
//		}
//		request.setAttribute("num", num);
//		request.setAttribute("replyNum", replyNum);
//		return "/board/reply_delete";
//	}

//	@RequestMapping(value = "/ReplyDelete", method = RequestMethod.POST)
//	public String replyDelete(Model model, HttpServletRequest request, HttpServletResponse response)
//			throws IOException {
//		if (request.getParameter("num") != null) {
//			model.addAttribute("num", Integer.parseInt(request.getParameter("num")));
//		}
//		if (request.getParameter("reply_password") != null) {
//			model.addAttribute("reply_password", Integer.parseInt(request.getParameter("reply_password")));
//		}
//		if (request.getParameter("replyNum") != null) {
//			model.addAttribute("replyNum", Integer.parseInt(request.getParameter("replyNum")));
//		}
//		Map map = model.asMap();
//		model = boardService.replyDeleteService(model, response);
//		return boardDetail(model, request);
//	}
	
//	@RequestMapping(value = "/ReplyChange", method = RequestMethod.POST)
//	public String replyChange(Model model, HttpServletRequest request, HttpServletResponse response)
//			throws IOException {
//		String writer = request.getParameter("writer");
//		String content = request.getParameter("content");
//		String pass = request.getParameter("pass");
//		String board_no = request.getParameter("board_no");
//		int no = Integer.parseInt(request.getParameter("no"));
//		if(!boardService.isReplyWriter(no, pass)) {
//			return "fail";
//		}
//		boardService.replyChangeService(writer, content, pass, no);
//		return "redirect:/BoardDetail?num="+board_no;
//	}
}
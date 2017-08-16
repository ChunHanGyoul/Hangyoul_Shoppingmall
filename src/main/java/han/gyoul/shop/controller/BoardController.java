package han.gyoul.shop.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import han.gyoul.shop.board.service.BoardService;
import han.gyoul.shop.member.dto.MemberDTO;

@Controller
@SessionAttributes({"memberDTO","itemList"})
public class BoardController {
	@Autowired
	private BoardService boardService;

	@RequestMapping("/BoardMain")
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
				model.addAttribute("memberDTO", memberDTO);
			}
//			model = boardService.listService(model);
			String success = "1";
			model.addAttribute("success", success);
			boardService.listService(model);
			return "/board/board_list";

		}else{
		String success = "0";
		model.addAttribute("success", success);
		return "/mainPage";
		}
		
	}

	@RequestMapping(value = "/BoardWrite", method = RequestMethod.GET)
	public String boardWirteForm() {
		return "/board/board_write";
	}

	@RequestMapping(value = "/BoardWrite", method = RequestMethod.POST)
	public String boardWirte(Model model, HttpServletRequest request) throws IOException {
		boardService.addService(model, request);
		return boardDetail(model, request);
	}

	@RequestMapping(value = "/BoardDetail", method = RequestMethod.GET)
	public String boardDetail(Model model, HttpServletRequest request) {
		if (request.getParameter("num") != null) {
			model.addAttribute("num", Integer.parseInt(request.getParameter("num")));
		}
		boardService.detailService(model);
		return "/board/board_view";
	}
//
//	@RequestMapping(value = "/BoardDownload", method = RequestMethod.GET)
//	public void boardDownload(Model model, HttpServletRequest request, HttpServletResponse response)
//			throws FileNotFoundException {
//		boardService.downloadService(model, request, response);
//	}

	@RequestMapping(value = "/BoardModify", method = RequestMethod.GET)
	public String boardModifyDetail(Model model, HttpServletRequest request) {
		if (request.getParameter("num") != null) {
			model.addAttribute("num", Integer.parseInt(request.getParameter("num")));
		}
		boardService.modifyDetailService(model);
		return "/board/board_modify";
	}

	@RequestMapping(value = "/BoardModify", method = RequestMethod.POST)
	public String boardModify(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		boardService.modifyService(model, request, response);
		return boardDetail(model, request);
	}

	// ?���?
	@RequestMapping(value = "/BoardReply", method = RequestMethod.GET)
	public String boardReplyDetail(Model model, HttpServletRequest request) {
		if (request.getParameter("num") != null) {
			model.addAttribute("num", Integer.parseInt(request.getParameter("num")));
		}
		boardService.replyMoveService(model);
		return "/board/board_reply";
	}

	@RequestMapping(value = "/BoardReply", method = RequestMethod.POST)
	public String boardReply(Model model, HttpServletRequest request) throws IOException {
		int num = boardService.replyService(model, request);
		model.addAttribute("num", num);
		return boardDetail(model, request);
	}

	@RequestMapping(value = "/BoardSearchList", method = RequestMethod.POST)
	public String boardSearchList(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		Map map = model.asMap();
		model.addAttribute("keyfield", request.getParameter("keyfield"));
		model.addAttribute("keyword", request.getParameter("keyword"));
		if (request.getParameter("page") != null) {
			model.addAttribute("page", Integer.parseInt(request.getParameter("page")));
		}
		model = boardService.searchListService(model);
		return "/board/board_search_list";
	}

	@RequestMapping(value = "/BoardDelete", method = RequestMethod.GET)
	public String boardDeleteForm(Model model, HttpServletRequest request) {
		if (request.getParameter("num") != null) {
			model.addAttribute("num", Integer.parseInt(request.getParameter("num")));
		}
		return "/board/board_delete";
	}

	@RequestMapping(value = "/BoardDelete", method = RequestMethod.POST)
	public String boardDelete(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (request.getParameter("num") != null) {
			model.addAttribute("num", Integer.parseInt(request.getParameter("num")));
		}
		if (request.getParameter("pass") != null) {
			model.addAttribute("pass", Integer.parseInt(request.getParameter("pass")));
		}
		model = boardService.deleteService(model, response);
		return boardMain(model, request);
	}

	@RequestMapping(value = "/Reply_Write", method = RequestMethod.POST)
	public String replyInsert(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		int num = Integer.parseInt(request.getParameter("boardNum"));
		String reply_content = request.getParameter("reply_content");
		String reply_writer = request.getParameter("reply_writer");
		String reply_pass = request.getParameter("reply_pass");
		model.addAttribute("num", num);
		model.addAttribute("reply_content", reply_content);
		model.addAttribute("reply_writer", reply_writer);
		model.addAttribute("reply_pass", reply_pass);
		boardService.replyAddService(model);
		return boardDetail(model, request);
	}

	@RequestMapping(value = "/ReplyDelete", method = RequestMethod.GET)
	public String replyDeleteForm(Model model, HttpServletRequest request) {
		int replyNum=-1;
		int num=-1;
		
		if (request.getParameter("reply_num") != null) {
			replyNum = Integer.parseInt(request.getParameter("reply_num"));
			model.addAttribute("reply_num", Integer.parseInt(request.getParameter("reply_num")));
		}
		if (request.getParameter("num") != null) {
			model.addAttribute("num", Integer.parseInt(request.getParameter("num")));
			num = Integer.parseInt(request.getParameter("num"));
		}
		request.setAttribute("num", num);
		request.setAttribute("replyNum", replyNum);
		return "/board/reply_delete";
	}

	@RequestMapping(value = "/ReplyDelete", method = RequestMethod.POST)
	public String replyDelete(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (request.getParameter("num") != null) {
			model.addAttribute("num", Integer.parseInt(request.getParameter("num")));
		}
		if (request.getParameter("reply_password") != null) {
			model.addAttribute("reply_password", Integer.parseInt(request.getParameter("reply_password")));
		}
		if (request.getParameter("replyNum") != null) {
			model.addAttribute("replyNum", Integer.parseInt(request.getParameter("replyNum")));
		}
		Map map = model.asMap();
		model = boardService.replyDeleteService(model, response);
		return boardDetail(model, request);
	}
	// �鸮Ʈ �ϰ� �ٽ����غ��� �ȵǴºκа�ġ�� �ҽ� ������ȭ�ϱ�
	@RequestMapping(value = "/ReplyChange", method = RequestMethod.POST)
	public String replyChange(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String pass = request.getParameter("pass");
		String board_no = request.getParameter("board_no");
		int no = Integer.parseInt(request.getParameter("no"));
		if(!boardService.isReplyWriter(no, pass)) {
			return "fail";
		}
		boardService.replyChangeService(writer, content, pass, no);
		return "redirect:/BoardDetail?num="+board_no;
	}
}
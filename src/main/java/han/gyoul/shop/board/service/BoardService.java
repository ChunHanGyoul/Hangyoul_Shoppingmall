package han.gyoul.shop.board.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import han.gyoul.shop.board.dao.BoardDAO;
import han.gyoul.shop.board.model.BoardDTO;
import han.gyoul.shop.board.model.ReplyDTO;

@Service
public class BoardService {
	@Autowired
	private BoardDAO boardDAO;
	
	public Model addService(Model model, HttpServletRequest request) throws IOException {
		BoardDTO boardDTO = new BoardDTO();
		
		boolean result = false;
		boardDTO.setName(request.getParameter("name"));
		boardDTO.setPass(request.getParameter("pass"));
		boardDTO.setSubject(request.getParameter("subject"));
		boardDTO.setContent(request.getParameter("content"));
		int num = boardDAO.boardInsert(boardDTO);
		model.addAttribute("num", num);
		return model;
	}

	public Model deleteService(Model model, HttpServletResponse response) throws IOException {
//		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		boolean usercheck = false;
		Map map = model.asMap();
		int num = Integer.parseInt( map.get("num").toString());
		usercheck = boardDAO.isBoardWriter(num, ""+map.get("pass"));
		if (usercheck == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 다릅니다.');");
			out.println("location.href='./BoardMain';");
			out.println("</script>");
			out.close();
			return null;
		}
		boardDTO.setNum(num);
		boardDAO.boardDelete(num);
		return model;
	}

	public Model detailService(Model model) {
		List<ReplyDTO> replyList = new ArrayList<ReplyDTO>();
//		BoardDAO boardDAO = new BoardDAO( );
		BoardDTO boardDTO = new BoardDTO( );
		ReplyDTO replyDTO = new ReplyDTO();
		Map map = model.asMap();
		int num = Integer.parseInt( map.get("num").toString());
		boardDTO.setNum(num);
		replyDTO.setBoard_no(num);
		System.out.println("boardno : " + replyDTO.getBoard_no());
		int replycount = boardDAO.getReplyCount(replyDTO);
		System.out.println("reply count : " + replycount);
		boardDAO.setReadCountUpdate(boardDTO);
		boardDTO = boardDAO.getDetail(boardDTO);
		System.out.println("detail");
		replyList = boardDAO.getReplyList(boardDTO);
//		console 異쒕젰 �솗�씤
//		for(int i = 0; i < replyList.size(); i++) {
//			System.out.println("reply_no : " + replyList.get(i).getReply_no());
//			System.out.println("board_no : " + replyList.get(i).getBoard_no());
//			System.out.println("reply_writer : " + replyList.get(i).getReply_writer());
//			System.out.println("reply_content : " + replyList.get(i).getReply_content());
//		}
		model.addAttribute("replyList", replyList);
		model.addAttribute("boardDTO", boardDTO);
		model.addAttribute("replycount",replycount);
		return model;
	}

//	public void downloadService(Model model, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
//		String fileName = request.getParameter("attached_file");
//		String savePath = "./boardUpload";
//		ServletContext context = request.getSession().getServletContext();
//		String downPath = context.getRealPath(savePath);
//		String fielPath = downPath + "\\" + fileName;
//		byte b[] = new byte[4096];
//		new File(fielPath);
//		FileInputStream fileInputStream = new FileInputStream(fielPath);
//		String sEncoding = null;
//		try {
//			boolean MSIE = (request.getHeader("user-agent").indexOf("MSIE") != -1)
//					|| (request.getHeader("user-agent").indexOf("Trident") != -1);
//			String downType = request.getSession().getServletContext().getMimeType(fielPath);
//			if (downType == null)
//				downType = "application/octet-stream";
//			response.setContentType(downType);
//			if (MSIE) {
//				sEncoding = new String(fileName.getBytes("EUC-KR"), "ISO-8859-1").replaceAll("\\+", "%20");
//			} else {
//				sEncoding = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
//			}
//			response.setHeader("Content-Disposition", "attachment;filename=\"" + sEncoding + "\"");
//			ServletOutputStream servletOutputStream = response.getOutputStream();
//			int nunRead;
//			while ((nunRead = fileInputStream.read(b, 0, b.length)) != -1) {
//				servletOutputStream.write(b, 0, nunRead);
//			}
//			servletOutputStream.flush();
//			servletOutputStream.close();
//			fileInputStream.close();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}

	public Model listService(Model model){
//		BoardDAO boardDAO = new BoardDAO();
		List<?> boardList = new ArrayList<Object>();
		int page = 1;
		int limit = 10;
		Map modelMap  = model.asMap();
		if (modelMap.get("page") != null) {
			page = (Integer) modelMap.get("page");
		}
		int listcount = boardDAO.getListCount();
		boardList = boardDAO.getBoardList(page, limit);
		int maxpage = (int) ((double) listcount / limit + 0.95);
		int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		int endpage = startpage + 10 - 1;
		if (endpage > maxpage) {
			endpage = maxpage;
		}

		model.addAttribute("page", page);
		model.addAttribute("maxpage", maxpage);
		model.addAttribute("startpage",startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("listcount",listcount);
		model.addAttribute("boardList", boardList);
		return model;
	}

	public Model modifyDetailService(Model model) {
//		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		Map map = model.asMap();
		int num = Integer.parseInt( map.get("num").toString());
		boardDTO.setNum(num);
		boardDTO = boardDAO.getDetail(boardDTO);
		model.addAttribute("boardDTO", boardDTO);
		return model;
	}

	public Model modifyService(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		BoardDTO boardDTO = new BoardDTO();
		int num = Integer.parseInt(request.getParameter("num"));
		boolean usercheck = boardDAO.isBoardWriter(num, request.getParameter("pass"));
		if (usercheck == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 다릅니다.');");
			out.println("location.href='./BoardMain';");
			out.println("</script>");
			out.close();
			return null;
		}
		boardDTO.setNum(num);
		boardDTO.setName(request.getParameter("name"));
		boardDTO.setSubject(request.getParameter("subject"));
		boardDTO.setContent(request.getParameter("content"));
		boardDAO.boardModify(boardDTO);
		model.addAttribute("num", num);
		return model;
	}

	public Model replyMoveService(Model model) {
//		BoardDAO boardDAO = new BoardDAO( );
		BoardDTO boardDTO = new BoardDTO( );
		Map map = model.asMap();
		int num = Integer.parseInt( map.get("num").toString());
		boardDTO.setNum(num);
		boardDTO = boardDAO.getDetail(boardDTO);
		model.addAttribute("boardDTO", boardDTO);
		return model;
	}

	public int replyService(Model model, HttpServletRequest request) throws IOException {
//		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		int result = 0;
		boardDTO.setNum(Integer.parseInt(request.getParameter("num")));
		boardDTO.setName(request.getParameter("name"));
		boardDTO.setPass(request.getParameter("pass"));
		boardDTO.setSubject(request.getParameter("subject"));
		boardDTO.setContent(request.getParameter("content"));
		boardDTO.setAnswer_num(Integer.parseInt(request.getParameter("answer_num")));
		boardDTO.setAnswer_lev(Integer.parseInt(request.getParameter("answer_lev")));
		boardDTO.setAnswer_seq(Integer.parseInt(request.getParameter("answer_seq")));
		return boardDAO.boardReply(boardDTO);
	}
	
	public Model searchListService(Model model) {
		String keyword = null;
		Map map = model.asMap();
		keyword = (String) map.get("keyword");
		String keyfield = null;
		keyfield = (String) map.get("keyfield");
//		BoardDAO boardDAO = new BoardDAO();
		List<?> searchBoardlist = new ArrayList<Object>();
		int page = 1;
		int limit = 10;
		if (map.get("page") != null) {
			int num = Integer.parseInt( map.get("page").toString());
		}
		int searchlistcount = boardDAO.getSearchListCount(keyword, keyfield);
		searchBoardlist = boardDAO.getSearchList(keyword, keyfield, page, limit);
		int maxpage = (int) ((double) searchlistcount / limit + 0.95);
		int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		int endpage = startpage + 10 - 1;
		if (endpage > maxpage) {
			endpage = maxpage;
		}
		model.addAttribute("page", page);
		model.addAttribute("maxpage", maxpage);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("searchlistcount", searchlistcount);
		model.addAttribute("searchBoardlist", searchBoardlist);
		model.addAttribute("keyword", keyword);
		model.addAttribute("keyfield", keyfield);
		return model;
	}

	public void replyAddService(Model model) {
//		BoardDAO boardDAO = new BoardDAO();
		ReplyDTO replyDTO = new ReplyDTO();
		Map map = model.asMap();
		replyDTO.setReply_content((String) map.get("reply_content"));
		replyDTO.setBoard_no(Integer.parseInt( map.get("num").toString()));
		replyDTO.setReply_writer((String) map.get("reply_writer"));
		replyDTO.setReply_pass((String) map.get("reply_pass"));
		boardDAO.replyInsert(replyDTO);
	}
	
	public Model replyDeleteService(Model model, HttpServletResponse response) throws IOException {
//		BoardDAO boardDAO = new BoardDAO();
		ReplyDTO replyDTO = new ReplyDTO();
		Map map = model.asMap();
		int num = Integer.parseInt( map.get("num").toString());
		String reply_password = ""+ map.get("reply_password");;
		int replyNum = Integer.parseInt( map.get("replyNum").toString());
		boolean usercheck = false;
		usercheck = boardDAO.isReplyWriter(replyNum, reply_password);
		model.addAttribute("num", num);
		if (usercheck == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 다릅니다.');");
			out.println("location.href='./BoardDetail?num=" + num + "';");
			out.println("</script>");
			out.close();
			return null;
		}
		replyDTO.setReply_no(replyNum);
		boardDAO.replyDelete(replyNum);
		return model;
	}

	public void replyChangeService(String writer, String content, String pass, int no) {
//		BoardDAO boardDAO = new BoardDAO();
		Map map = new HashMap();
		map.put("writer", writer);
		map.put("content", content);
		map.put("pass", pass);
		map.put("no", no);
		boardDAO.replyChange(map);
		
	}
	
	public boolean isReplyWriter(int no, String pass) {
//		BoardDAO boardDAO = new BoardDAO();
		return boardDAO.isReplyWriter(no, pass);
	}
}

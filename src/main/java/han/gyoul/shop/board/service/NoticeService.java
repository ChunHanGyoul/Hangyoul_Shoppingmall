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

import han.gyoul.shop.board.dao.NoticeDAO;
import han.gyoul.shop.board.model.NoticeDTO;

@Service
public class NoticeService {
	@Autowired
	private NoticeDAO noticeDAO;
	
	public Model addService(Model model, HttpServletRequest request) throws IOException {
//		NoticeDAO NoticeDAO = new NoticeDAO();
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setName(request.getParameter("name"));
		noticeDTO.setPass(request.getParameter("pass"));
		noticeDTO.setSubject(request.getParameter("subject"));
		noticeDTO.setContent(request.getParameter("content"));
		int num = noticeDAO.NoticeInsert(noticeDTO);
		model.addAttribute("num", num);
		return model;
	}

	public Model deleteService(Model model, HttpServletResponse response) throws IOException {
//		NoticeDAO NoticeDAO = new NoticeDAO();
		NoticeDTO noticeDTO = new NoticeDTO();
		boolean usercheck = false;
		Map map = model.asMap();
		int num = Integer.parseInt( map.get("num").toString());
		usercheck = noticeDAO.isNoticeWriter(num, "" + map.get("pass"));
		if (usercheck == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 다릅니다.');");
			out.println("location.href='./NoticeMain';");
			out.println("</script>");
			out.close();
			return null;
		}
		noticeDTO.setNum(num);
		noticeDAO.NoticeDelete(num);
		return model;
	}

	public Model detailService(Model model) {
//		List<ReplyDTO> replyList = new ArrayList<ReplyDTO>();
//		NoticeDAO NoticeDAO = new NoticeDAO( );
		NoticeDTO noticeDTO = new NoticeDTO( );
//		ReplyDTO replyDTO = new ReplyDTO();
		Map map = model.asMap();
		int num = Integer.parseInt( map.get("num").toString());
		noticeDTO.setNum(num);
//		replyDTO.setNotice_no(num);
//		System.out.println("Noticeno : " + replyDTO.getNotice_no());
//		int replycount = NoticeDAO.getReplyCount(replyDTO);
//		System.out.println("reply count : " + replycount);
		noticeDAO.setReadCountUpdate(noticeDTO);
		noticeDTO = noticeDAO.getDetail(noticeDTO);
		System.out.println("detail");
//		replyList = NoticeDAO.getReplyList(NoticeDTO);
//		for(int i = 0; i < replyList.size(); i++) {
//			System.out.println("reply_no : " + replyList.get(i).getReply_no());
//			System.out.println("Notice_no : " + replyList.get(i).getNotice_no());
//			System.out.println("reply_writer : " + replyList.get(i).getReply_writer());
//			System.out.println("reply_content : " + replyList.get(i).getReply_content());
//		}
//		model.addAttribute("replyList", replyList);
		
		// key 媛믪� jsp�뿉�꽌 ${notice, name}濡� �궗�슜.
		model.addAttribute("noticeDTO", noticeDTO);
//		model.addAttribute("replycount",replycount);
		return model;
	}

//	public void downloadService(Model model, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
//		String fileName = request.getParameter("attached_file");
//		String savePath = "./noticeUpload";
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
//		NoticeDAO NoticeDAO = new NoticeDAO();
		List<?> NoticeList = new ArrayList<Object>();
		int page = 1;
		int limit = 10;
		Map modelMap  = model.asMap();
		if (modelMap.get("page") != null) {
			page = (Integer) modelMap.get("page");
		}
		int listcount = noticeDAO.getListCount();
		NoticeList = noticeDAO.getNoticeList(page, limit);
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
		model.addAttribute("noticeList", NoticeList);
		return model;
	}

	public Model modifyDetailService(Model model) {
//		NoticeDAO NoticeDAO = new NoticeDAO();
		NoticeDTO noticeDTO = new NoticeDTO();
		Map map = model.asMap();
		int num = Integer.parseInt( map.get("num").toString());
		noticeDTO.setNum(num);
		noticeDTO = noticeDAO.getDetail(noticeDTO);
		model.addAttribute("noticeDTO", noticeDTO);
		return model;
	}

	public Model modifyService(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
//		NoticeDAO NoticeDAO = new NoticeDAO();
		NoticeDTO noticeDTO = new NoticeDTO();
		int num = Integer.parseInt(request.getParameter("num"));
		boolean usercheck = noticeDAO.isNoticeWriter(num, request.getParameter("pass"));
		if (usercheck == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("비밀번호가 다릅니다.');");
			out.println("location.href='./NoticeMain';");
			out.println("</script>");
			out.close();
			return null;
		}
		noticeDTO.setNum(num);
		noticeDTO.setName(request.getParameter("name"));
		noticeDTO.setSubject(request.getParameter("subject"));
		noticeDTO.setContent(request.getParameter("content"));
		noticeDAO.NoticeModify(noticeDTO);
		model.addAttribute("num", num);
		return model;
	}

//	public Model replyMoveService(Model model) {
////		NoticeDAO NoticeDAO = new NoticeDAO( );
//		NoticeDTO NoticeDTO = new NoticeDTO( );
//		Map map = model.asMap();
//		int num = (int) map.get("num");
//		NoticeDTO.setNum(num);
//		NoticeDTO = NoticeDAO.getDetail(NoticeDTO);
//		model.addAttribute("NoticeDTO", NoticeDTO);
//		return model;
//	}

//	public int replyService(Model model, HttpServletRequest request) throws IOException {
////		NoticeDAO NoticeDAO = new NoticeDAO();
//		NoticeDTO NoticeDTO = new NoticeDTO();
//		int result = 0;
//		String realFolder = "";
//		String saveFolder = "./NoticeUpload";
//		int fileSize = 5 * 1024 * 1024;
//		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
//		MultipartRequest multipartRequest = null;
//		multipartRequest = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
//		NoticeDTO.setNum(Integer.parseInt(multipartRequest.getParameter("num")));
//		NoticeDTO.setName(multipartRequest.getParameter("name"));
//		NoticeDTO.setPass(multipartRequest.getParameter("pass"));
//		NoticeDTO.setSubject(multipartRequest.getParameter("subject"));
//		NoticeDTO.setContent(multipartRequest.getParameter("content"));
//		NoticeDTO.setAnswer_num(Integer.parseInt(multipartRequest.getParameter("answer_num")));
//		NoticeDTO.setAnswer_lev(Integer.parseInt(multipartRequest.getParameter("answer_lev")));
//		NoticeDTO.setAnswer_seq(Integer.parseInt(multipartRequest.getParameter("answer_seq")));
//		if (multipartRequest.getFilesystemName((String) multipartRequest.getFileNames().nextElement()) != null) {
//			NoticeDTO.setAttached_file(
//					multipartRequest.getFilesystemName((String) multipartRequest.getFileNames().nextElement()));
//		} else {
//			NoticeDTO.setAttached_file("null");
//		}
//		return NoticeDAO.NoticeReply(NoticeDTO);
//	}
	
	public Model searchListService(Model model) {
		String keyword = null;
		Map map = model.asMap();
		keyword = (String) map.get("keyword");
		String keyfield = null;
		keyfield = (String) map.get("keyfield");
//		NoticeDAO NoticeDAO = new NoticeDAO();
		List<?> searchNoticelist = new ArrayList<Object>();
		int page = 1;
		int limit = 10;
		if (map.get("page") != null) {
			page = Integer.parseInt( map.get("page").toString());
		}
		int searchlistcount = noticeDAO.getSearchListCount(keyword, keyfield);
		searchNoticelist = noticeDAO.getSearchList(keyword, keyfield, page, limit);
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
		model.addAttribute("searchNoticelist", searchNoticelist);
		model.addAttribute("keyword", keyword);
		model.addAttribute("keyfield", keyfield);
		return model;
	}

//	public void replyAddService(Model model) {
//		NoticeDAO NoticeDAO = new NoticeDAO();
//		ReplyDTO replyDTO = new ReplyDTO();
//		Map map = model.asMap();
//		replyDTO.setReply_content((String) map.get("reply_content"));
//		replyDTO.setNotice_no((int) map.get("num"));
//		replyDTO.setReply_writer((String) map.get("reply_writer"));
//		replyDTO.setReply_pass((String) map.get("reply_pass"));
//		NoticeDAO.replyInsert(replyDTO);
//	}
	
//	public Model replyDeleteService(Model model, HttpServletResponse response) throws IOException {
//		NoticeDAO NoticeDAO = new NoticeDAO();
//		ReplyDTO replyDTO = new ReplyDTO();
//		Map map = model.asMap();
//		int num = (int) map.get("num");;
//		String reply_password = ""+ map.get("reply_password");;
//		int replyNum = (int) map.get("replyNum");;
//		boolean usercheck = false;
//		usercheck = NoticeDAO.isReplyWriter(replyNum, reply_password);
//		model.addAttribute("num", num);
//		if (usercheck == false) {
//			response.setContentType("text/html;charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.println("<script>");
//			out.println("alert('占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싹댐옙.');");
//			out.println("location.href='./NoticeDetail?num=" + num + "';");
//			out.println("</script>");
//			out.close();
//			return null;
//		}
//		replyDTO.setReply_no(replyNum);
//		NoticeDAO.replyDelete(replyNum);
//		return model;
//	}

//	public void replyChangeService(String writer, String content, String pass, int no) {
//		NoticeDAO NoticeDAO = new NoticeDAO();
//		Map map = new HashMap();
//		map.put("writer", writer);
//		map.put("content", content);
//		map.put("pass", pass);
//		map.put("no", no);
//		NoticeDAO.replyChange(map);
//		
//	}
//	
//	public boolean isReplyWriter(int no, String pass) {
////		NoticeDAO NoticeDAO = new NoticeDAO();
//		return NoticeDAO.isReplyWriter(no, pass);
//	}
}

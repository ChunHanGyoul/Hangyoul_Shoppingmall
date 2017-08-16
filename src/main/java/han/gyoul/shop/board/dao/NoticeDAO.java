package han.gyoul.shop.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import han.gyoul.shop.board.model.NoticeDTO;

@Repository
public class NoticeDAO {
	SqlSessionTemplate template;
	
	
	public void setTemplate(SqlSessionTemplate template) {
		this.template = template;
	}
	
	public void makeTemplate() {
		ApplicationContext context = new ClassPathXmlApplicationContext("config/BoardBean.xml");
		template = (SqlSessionTemplate) context.getBean("sqlSessionTemplate");
	}
	
//	public int getReplyCount(ReplyDTO replyDTO) {
//		makeTemplate();
//		return template.selectOne("Notice.getReplyCount", replyDTO);
//	}
	
	public int getListCount() {
		makeTemplate();
		return template.selectOne("Notice.getListCount");
	}

	public List<NoticeDTO> getNoticeList(int page, int limit) {
		makeTemplate();
		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + limit - 1;
		Map map = new HashMap();
		map.put("startrow", startrow);
		map.put("endrow", endrow);
		return template.selectList("Notice.getNoticeList", map);
	}

	public int NoticeInsert(NoticeDTO noticeDTO) {
		makeTemplate();
		System.out.println("NoticeDAO.noticeInsert()");
		template.insert("Notice.noticeInsert", noticeDTO);
		
		return template.selectOne("Notice.getMaxCount");
	}

	public NoticeDTO getDetail(NoticeDTO noticeDTO) {
		System.out.println("noticeDAO.getDetail().noticeDTO:" + noticeDTO);
		makeTemplate();
		return template.selectOne("Notice.getDetail", noticeDTO);
	}

	public void setReadCountUpdate(NoticeDTO noticeDTO) {
		makeTemplate();
		template.update("Notice.setReadCountUpdate", noticeDTO);
	}

	public int NoticeModify(NoticeDTO noticeDTO) {
		makeTemplate();
		int result = template.update("Notice.noticeModify", noticeDTO);
		return result;
	}

	public boolean isNoticeWriter(int num, String pass) {
		makeTemplate();
		Map map = new HashMap();
		map.put("num", num);
		map.put("pass", pass);
		NoticeDTO searchNoticeDTO = template.selectOne("Notice.isNoticeWriter", map);
		if (searchNoticeDTO == null) {
			return false;
		}
		return true;
	}

//	public int NoticeReply(NoticeDTO noticeDTO) {
//		makeTemplate();
//		int num = template.selectOne("Notice.getMaxNum");
//		num++;
//		template.update("NoticeReplyUpdate", noticeDTO);
//		noticeDTO.setAnswer_seq(noticeDTO.getAnswer_seq() + 1);
//		noticeDTO.setAnswer_lev(noticeDTO.getAnswer_lev() + 1);
//		noticeDTO.setNum(num);
//		template.insert("NoticeReply", noticeDTO);
//		return template.selectOne("Notice.getMaxCount");
//	}

	public List<NoticeDTO> getSearchList(String keyword, String keyfield, int page, int limit) {
		makeTemplate();
		String searchCall = "";
		System.out.println("2keyword : " + keyword);
		System.out.println("2keyFile : " + keyfield);
		System.out.println("2page : " + page);
		System.out.println("2limit : " + limit);
		if (!"".equals(keyword)) {
			if ("all".equals(keyfield)) {
				searchCall = "(subject like '%' || '" + keyword + "' || '%' ) or ( name like '%' || '" + keyword
						+ "' || '%') or ( content like '%' || '" + keyword + "' || '%')";
			} else if ("subject".equals(keyfield)) {
				searchCall = " subject like '%' || '" + keyword + "' || '%'";
			} else if ("name".equals(keyfield)) {
				searchCall = " name like '%' || '" + keyword + "' || '%'";
			} else if ("content".equals(keyfield)) {
				searchCall = " content like '%' || '" + keyword + "' || '%'";
			}
		}
		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + limit - 1;
		Map map = new HashMap();
		map.put("searchCall", searchCall);
		map.put("startrow", startrow);
		map.put("endrow", endrow);
		return template.selectList("Notice.getSearchList", map);
	}

	public int getSearchListCount(String keyword, String keyfield) {
		makeTemplate();
		String searchCall = "";
		if (!"".equals(keyword)) {
			if ("all".equals(keyfield)) {
				searchCall = "(subject like '%' || '" + keyword + "' || '%' ) or ( name like '%' || '" + keyword
						+ "' || '%') or ( content like '%' || '" + keyword + "' || '%')";
			} else if ("subject".equals(keyfield)) {
				searchCall = " subject like '%' || '" + keyword + "' || '%'";
			} else if ("name".equals(keyfield)) {
				searchCall = " name like '%' || '" + keyword + "' || '%'";
			} else if ("content".equals(keyfield)) {
				searchCall = " content like '%' || '" + keyword + "' || '%'";
			}
		}
		Map map = new HashMap();
		map.put("value", searchCall);
		System.out.println(map.get("value"));
		int count = template.selectOne("Notice.getSearchListCount", map);
		return count;
	}

	public void NoticeDelete(int num) {
		makeTemplate();
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setNum(num);
		template.selectOne("Notice.noticeDelete", noticeDTO);
	}

//	public List<ReplyDTO> getReplyList(NoticeDTO NoticeDTO) {
//		makeTemplate();
//		return template.selectList("Notice.getReplyList", NoticeDTO);
//	}
//
//	public void replyInsert(ReplyDTO replyDTO) {
//		makeTemplate();
//		template.insert("Notice.replyInsert", replyDTO);
//	}
//
//	public boolean isReplyWriter(int replyNum, String reply_password) {
//		makeTemplate();
//		Map map = new HashMap();
//		map.put("replyNum", replyNum);
//		map.put("reply_password", reply_password);
//		ReplyDTO searchReplyDTO = template.selectOne("Notice.isReplyWriter", map);
//		if (searchReplyDTO == null) {
//			return false;
//		}
//		return true;
//	}
//
//	public void replyDelete(int replyNum) {
//		makeTemplate();
//		ReplyDTO replyDTO = new ReplyDTO();
//		replyDTO.setReply_no(replyNum);
//		template.selectOne("Notice.replyDelete", replyDTO);
//		
//	}
//
//	public void replyChange(Map map) {
//		makeTemplate();
//		template.selectOne("Notice.replyChange", map);
//	}
}

package han.gyoul.shop.review.dto;

// import java.util.Date; --> DB오류, 400 Bad Request오류
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class ReviewDTO {
	private int review_no; // 후기번호
	private String review_title; // 후기제목
	private String review_content; // 후기내용
	private String review_image; // 후기사진
	private Date review_date; // 후기등록일
	private String review_hit; // 후기조회수
	private String mb_id; // 회원아이디
	private int item_no; // 상품번호
	
	private int commentcnt; // 후기게시댓글 수
	
	public int getCommentcnt() {
		return commentcnt;
	}
	public void setCommentcnt(int commentcnt) {
		this.commentcnt = commentcnt;
	}
	public int getReview_no() {
		return review_no;
	}
	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}
	public String getReview_title() {
		return review_title;
	}
	public void setReview_title(String review_title) {
		this.review_title = review_title;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public String getReview_image() {
		return review_image;
	}
	public void setReview_image(String review_image) {
		this.review_image = review_image;
	}
	public Date getReview_date() {
		return review_date;
	}
	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}
	public String getReview_hit() {
		return review_hit;
	}
	public void setReview_hit(String review_hit) {
		this.review_hit = review_hit;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public int getItem_no() {
		return item_no;
	}
	public void setItem_no(int item_no) {
		this.item_no = item_no;
	}
	@Override
	public String toString() {
		return "ReviewDTO [review_no=" + review_no + ", review_title=" + review_title + ", review_content="
				+ review_content + ", review_image=" + review_image + ", review_date=" + review_date + ", review_hit="
				+ review_hit + ", mb_id=" + mb_id + ", item_no=" + item_no + ", commentcnt=" + commentcnt + "]";
	}
	
}
	
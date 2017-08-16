package han.gyoul.shop.review.dto;

// import java.util.Date; --> DB����, 400 Bad Request����
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class ReviewDTO {
	private int review_no; // �ı��ȣ
	private String review_title; // �ı�����
	private String review_content; // �ı⳻��
	private String review_image; // �ı����
	private Date review_date; // �ı�����
	private String review_hit; // �ı���ȸ��
	private String mb_id; // ȸ�����̵�
	private int item_no; // ��ǰ��ȣ
	
	private int commentcnt; // �ı�Խô�� ��
	
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
	
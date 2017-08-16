package han.gyoul.shop.review.dto;

import java.sql.Date;

public class CommentDTO {
	private int comment_no;
	private Date comment_date;
	private String comment_content;
	private int review_no;
	private String mb_id;
	public int getComment_no() {
		return comment_no;
	}
	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}
	public Date getComment_date() {
		return comment_date;
	}
	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public int getReview_no() {
		return review_no;
	}
	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	@Override
	public String toString() {
		return "ReviewCommantDTO [comment_no=" + comment_no + ", comment_date=" + comment_date + ", comment_content="
				+ comment_content + ", review_no=" + review_no + ", mb_id=" + mb_id + "]";
	}
	
}

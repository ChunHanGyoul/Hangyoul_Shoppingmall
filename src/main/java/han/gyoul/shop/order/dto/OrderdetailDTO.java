package han.gyoul.shop.order.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderdetailDTO {
	private int orderdetail_no;
	private String receiver_name;
	private String receiver_mobile;
	private String receiver_email;
	private int receiver_zipcode;
	private String receiver_address;
	private String receiver_memo;
	private String pay_method;
	private String card_kind;
	private String bank_kind;
	private String bank_acct;
	private String bank_sender;
	private String delivery_state;
	private int mb_no;
	public int getOrderdetail_no() {
		return orderdetail_no;
	}
	public String getReceiver_name() {
		return receiver_name;
	}
	public String getReceiver_mobile() {
		return receiver_mobile;
	}
	public String getReceiver_email() {
		return receiver_email;
	}
	public int getReceiver_zipcode() {
		return receiver_zipcode;
	}
	public String getReceiver_address() {
		return receiver_address;
	}
	public String getReceiver_memo() {
		return receiver_memo;
	}
	public String getPay_method() {
		return pay_method;
	}
	public String getCard_kind() {
		return card_kind;
	}
	public String getBank_kind() {
		return bank_kind;
	}
	public String getBank_acct() {
		return bank_acct;
	}
	public String getBank_sender() {
		return bank_sender;
	}
	public String getDelivery_state() {
		return delivery_state;
	}
	public void setOrderdetail_no(int orderdetail_no) {
		this.orderdetail_no = orderdetail_no;
	}
	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}
	public void setReceiver_mobile(String receiver_mobile) {
		this.receiver_mobile = receiver_mobile;
	}
	public void setReceiver_email(String receiver_email) {
		this.receiver_email = receiver_email;
	}
	public void setReceiver_zipcode(int receiver_zipcode) {
		this.receiver_zipcode = receiver_zipcode;
	}
	public void setReceiver_address(String receiver_address) {
		this.receiver_address = receiver_address;
	}
	public void setReceiver_memo(String receiver_memo) {
		this.receiver_memo = receiver_memo;
	}
	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}
	public void setCard_kind(String card_kind) {
		this.card_kind = card_kind;
	}
	public void setBank_kind(String bank_kind) {
		this.bank_kind = bank_kind;
	}
	public void setBank_acct(String bank_acct) {
		this.bank_acct = bank_acct;
	}
	public void setBank_sender(String bank_sender) {
		this.bank_sender = bank_sender;
	}
	public void setDelivery_state(String delivery_state) {
		this.delivery_state = delivery_state;
	}
	public int getMb_no() {
		return mb_no;
	}
	public void setMb_no(int mb_no) {
		this.mb_no = mb_no;
	}
	@Override
	public String toString() {
		return "OrderdetailDTO [orderdetail_no=" + orderdetail_no + ", receiver_name=" + receiver_name
				+ ", receiver_mobile=" + receiver_mobile + ", receiver_email=" + receiver_email + ", receiver_zipcode="
				+ receiver_zipcode + ", receiver_address=" + receiver_address + ", receiver_memo=" + receiver_memo
				+ ", pay_method=" + pay_method + ", card_kind=" + card_kind + ", bank_kind=" + bank_kind
				+ ", bank_acct=" + bank_acct + ", bank_sender=" + bank_sender + ", delivery_state=" + delivery_state
				+ ", mb_no=" + mb_no + "]";
	}
	
	
}

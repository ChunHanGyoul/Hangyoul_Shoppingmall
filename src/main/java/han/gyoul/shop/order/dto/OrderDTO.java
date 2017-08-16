package han.gyoul.shop.order.dto;

import org.springframework.stereotype.Repository;

@Repository
public class OrderDTO {
	private int order_no;
	private String order_date;
	private int order_qty;
	private String item_size;
	private String item_color;
	private int tot_price;
	private int mb_no;
	private int item_no;
	private int orderdetail_no;
	private String pay_state;
	public int getOrder_no() {
		return order_no;
	}
	public String getOrder_date() {
		return order_date;
	}
	public int getOrder_qty() {
		return order_qty;
	}
	public String getItem_size() {
		return item_size;
	}
	public String getItem_color() {
		return item_color;
	}
	public int getTot_price() {
		return tot_price;
	}
	public int getMb_no() {
		return mb_no;
	}
	public int getItem_no() {
		return item_no;
	}
	public int getOrderdetail_no() {
		return orderdetail_no;
	}
	public String getPay_state() {
		return pay_state;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public void setOrder_qty(int order_qty) {
		this.order_qty = order_qty;
	}
	public void setItem_size(String item_size) {
		this.item_size = item_size;
	}
	public void setItem_color(String item_color) {
		this.item_color = item_color;
	}
	public void setTot_price(int tot_price) {
		this.tot_price = tot_price;
	}
	public void setMb_no(int mb_no) {
		this.mb_no = mb_no;
	}
	public void setItem_no(int item_no) {
		this.item_no = item_no;
	}
	public void setOrderdetail_no(int orderdetail_no) {
		this.orderdetail_no = orderdetail_no;
	}
	public void setPay_state(String pay_state) {
		this.pay_state = pay_state;
	}
	@Override
	public String toString() {
		return "OrderDTO [order_no=" + order_no + ", order_date=" + order_date + ", order_qty=" + order_qty
				+ ", item_size=" + item_size + ", item_color=" + item_color + ", tot_price=" + tot_price + ", mb_no="
				+ mb_no + ", item_no=" + item_no + ", orderdetail_no=" + orderdetail_no + ", pay_state=" + pay_state
				+ "]";
	}
}

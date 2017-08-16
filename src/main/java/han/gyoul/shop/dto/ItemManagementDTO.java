package han.gyoul.shop.dto;

import java.sql.Date;

public class ItemManagementDTO {
		private int item_no;
		private String item_name;
		private String item_image;
		private int item_price;
		private String item_material;
		private String item_size;
		private String item_color;
		private int item_qty;
		private String item_mfr;
		private String item_country;
		private Date item_mdate;
		private String item_expln;
		private int item_discount;
		private int category_no;
		private int subclass_no;
		private String category_name;
		public int getItem_no() {
			return item_no;
		}
		public void setItem_no(int item_no) {
			this.item_no = item_no;
		}
		public String getItem_name() {
			return item_name;
		}
		public void setItem_name(String item_name) {
			this.item_name = item_name;
		}
		public String getItem_image() {
			return item_image;
		}
		public void setItem_image(String item_image) {
			this.item_image = item_image;
		}
		public int getItem_price() {
			return item_price;
		}
		public void setItem_price(int item_price) {
			this.item_price = item_price;
		}
		public String getItem_material() {
			return item_material;
		}
		public void setItem_material(String item_material) {
			this.item_material = item_material;
		}
		public String getItem_size() {
			return item_size;
		}
		public void setItem_size(String item_size) {
			this.item_size = item_size;
		}
		public String getItem_color() {
			return item_color;
		}
		public void setItem_color(String item_color) {
			this.item_color = item_color;
		}
		public int getItem_qty() {
			return item_qty;
		}
		public void setItem_qty(int item_qty) {
			this.item_qty = item_qty;
		}
		public String getItem_mfr() {
			return item_mfr;
		}
		public void setItem_mfr(String item_mfr) {
			this.item_mfr = item_mfr;
		}
		public String getItem_country() {
			return item_country;
		}
		public void setItem_country(String item_country) {
			this.item_country = item_country;
		}
		public Date getItem_mdate() {
			return item_mdate;
		}
		public void setItem_mdate(Date item_mdate) {
			this.item_mdate = item_mdate;
		}
		public String getItem_expln() {
			return item_expln;
		}
		public void setItem_expln(String item_expln) {
			this.item_expln = item_expln;
		}
		public int getItem_discount() {
			return item_discount;
		}
		public void setItem_discount(int item_discount) {
			this.item_discount = item_discount;
		}
		public int getCategory_no() {
			return category_no;
		}
		public void setCategory_no(int category_no) {
			this.category_no = category_no;
		}
		public int getSubclass_no() {
			return subclass_no;
		}
		public void setSubclass_no(int subclass_no) {
			this.subclass_no = subclass_no;
		}
		public String getCategory_name() {
			return category_name;
		}
		public void setCategory_name(String category_name) {
			this.category_name = category_name;
		}
		//데이터가 제대로 들어갔는지 찍어보기 위해서
		@Override
		public String toString() {
			return "ItemManagementDTO [item_no=" + item_no + ", item_name=" + item_name + ", item_image=" + item_image
					+ ", item_price=" + item_price + ", item_material=" + item_material + ", item_size=" + item_size
					+ ", item_color=" + item_color + ", item_qty=" + item_qty + ", item_mfr=" + item_mfr + ", item_country="
					+ item_country + ", item_mdate=" + item_mdate + ", item_expln=" + item_expln + ", item_discount="
					+ item_discount + ", category_no=" + category_no + ", subclass_no=" + subclass_no + ", category_name="
					+ category_name + "]";
		}
}

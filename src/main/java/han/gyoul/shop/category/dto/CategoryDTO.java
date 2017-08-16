package han.gyoul.shop.category.dto;

import org.springframework.stereotype.Repository;

@Repository
public class CategoryDTO {
	private int category_no;
	private int subclass_no;
	private String category_name;

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
	@Override
	public String toString() {
		return "CategoryDTO [category_no=" + category_no + ", subclass_no=" + subclass_no + ", category_name="
				+ category_name + "]";
	}
	
}

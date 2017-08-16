package han.gyoul.shop.dto;

public class Criteria {
	//페이징 처리를 위해 존재하는 객체임
	//Criteria를 확장해서 item_no 값을 처리하지 않고, item_no를 별도의 파라미터로 사용
	  //이유1: 만약 Criteria를 상속해서 item_no 등을 추가적인 속성으로 사용하게 되면, 숫자가 아닌 문자열을 처리하는 경우 다시 상속해야 하는 문제 발생
	  //이유2: 또한 Criteria는 페이징 처리를 위해서 존재하는 객체이므로, 매번 의미 없는 item_no등을 유지할 필요가 없음
	private int page;	
	private int perPageNum;
	
	public Criteria(){
		this.page = 1;
		this.perPageNum = 10;
	}

	public void setPage(int page){
		
		if(page <= 0){
			this.page = 1;
			return;
		}
		
		this.page = page;
	}
	
	public void setPerPageNum(int perPageNum){
		
		if(perPageNum <= 0 || perPageNum > 100){
			this.perPageNum = 10;
			return;
		}
		
		this.perPageNum = perPageNum;
	}
	
	public int getPage() {
		return page;
	}	
	
	//method for MyBatis SQL Mapper - 
	public int getPageStart() {
		
		return (this.page -1)* perPageNum;
	}
	
	//method for MyBatis SQL Mapper 
	public int getPerPageNum(){
		
		return this.perPageNum;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
}

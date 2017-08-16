package han.gyoul.shop.dto;
//검색에 필요한 데이터와 SearchCriteria
public class SearchCriteria extends Criteria{
	//Criteria를 상속하는 경우 화면에 페이징 처리를 하는 PageMaker에서도 그대로 사용할 수 있기 때문에
	//페이징 처리에 있어서 수월함

	private String searchType;
	private String keyword;
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		return super.toString() + " SearchCriteria "
				+ "[searchType=" + searchType + ", keyword="
				+ keyword + "]";
	}
}



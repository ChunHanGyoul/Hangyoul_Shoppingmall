package han.gyoul.shop.dto;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;



public class PageMaker {

	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int displayPageNum = 10;
	
	private Criteria cri;


	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		calcData();
	}

	private void calcData() {
		
		endPage = (int) (Math.ceil(cri.getPage() / (double)displayPageNum ) * displayPageNum);
		System.out.println("endPage:" + endPage );
		startPage = (endPage - displayPageNum) + 1;
		
		System.out.println("totalCount: " + totalCount);
		int tempEndPage = (int)(Math.ceil(totalCount / (double)cri.getPerPageNum()));
		System.out.println("tempEndPage:" + tempEndPage );
		if(endPage > tempEndPage){
			endPage = tempEndPage;
			System.out.println("endPage2:" + endPage );
		}
		System.out.println("endPage3:" + endPage );
		prev = startPage ==1 ? false : true;
		
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
		
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}
	
	public String makeQuery(int page){
		
		UriComponents uriComponents =
	            UriComponentsBuilder.newInstance()
	            .queryParam("page", page)
	            .queryParam("perPageNum", cri.getPerPageNum())
	            .build();	            
		
		return uriComponents.toUriString();
	}
	 //1) searchType과 keyword 링크 처리
	//jsp에서 처리할 수도 있지만, PageMaker에서 적당한 URL에 사용할 문자열(query string)을 생성해 주면 조금 더 편하게 처리할 수 있음
	public String makeSearch(int page){
		
		UriComponents uriComponents =
	            UriComponentsBuilder.newInstance()
	            .queryParam("page", page)
	            .queryParam("perPageNum", cri.getPerPageNum())
	            .queryParam("searchType", ((SearchCriteria)cri).getSearchType())
	            .queryParam("keyword", ((SearchCriteria)cri).getKeyword())
	            .build();	            
		
		return uriComponents.toUriString();
		//uriComponents를 이용해서 페이징 처리에 필요한 데이터를 생성함
	}	
	
//	@SuppressWarnings("unused")
//	private String encoding(String keyword) {
//		
//		if(keyword == null || keyword.trim().length() == 0) {
//			return "";
//		}
//		
//		try {
//			return URLEncoder.encode(keyword, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			return "";
//		}
//	}
	
	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage="
				+ startPage + ", endPage=" + endPage + ", prev=" + prev
				+ ", next=" + next + ", displayPageNum=" + displayPageNum
				+ ", cri=" + cri + "]";
	}
	
	
	}

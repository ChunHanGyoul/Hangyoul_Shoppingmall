package han.gyoul.shop.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import han.gyoul.shop.dto.Criteria;
import han.gyoul.shop.dto.ItemManagementDTO;
import han.gyoul.shop.dto.SearchCriteria;




@Repository
public class ItemManagementDAOImpl implements ItemManagementDAO {
//반드시 스프링의 빈으로 제대로 등록되었는지 root-context.xml을 선택하고 Beans Graph 탭을 이용해서 확인
	
	@Inject
	 //스프링 프레임워크만 연동할 때 의존성 주입으로 @Autowired 사용
	 //순수 자바와 연결 할 때 @Inject로 자동 의존관계 설정
	 private SqlSession session;
	
	//공통으로 사용한  XML Mapper의 namespace 지정
	private static String namespace
	 ="han.gyoul.shop.mapper.ItemManagementMapper";
	
	@Override
	public void registerItem(ItemManagementDTO itemManagementDTO) throws Exception {
		session.insert(namespace+".registerItem", itemManagementDTO);		
	}

	@Override
	public ItemManagementDTO viewItemDetail(Integer item_no) throws Exception {
		return session.selectOne(namespace+".viewItemDetail", item_no);
	}

	@Override
	public void modifyItem(ItemManagementDTO itemManagementDTO) throws Exception {
		session.update(namespace+".modifyItem", itemManagementDTO);
	}

	@Override
	public void deleteItem(Integer item_no) throws Exception {
		session.delete(namespace+".deleteItem", item_no);
	}

	@Override
	public List<ItemManagementDTO> listAll() throws Exception {
		return session.selectList(namespace+".listAll");
	}

	@Override
	public List<ItemManagementDTO> listPage(int page) throws Exception {
		if(page <= 0){
			page = 1;
		}
		
		page = (page - 1) * 10;
		
		return session.selectList(namespace + ".listPage", page);
	}

	@Override
	public List<ItemManagementDTO> listCriteria(Criteria cri) throws Exception {
		return session.selectList(namespace + ".listCriteria", cri);
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		return session.selectOne(namespace + ".countPaging", cri);
	}

	@Override
	public List<ItemManagementDTO> listSearch(SearchCriteria cri) throws Exception {
		return session.selectList(namespace + ".listSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace + ".listSearchCount", cri);
	}

//	@Override
//	public ItemManagementDTO customerViewItemDetail(Integer item_no) throws Exception {
//		return session.selectOne(namespace+".customerViewItemDetail", item_no);
//	}
//
//	@Override
//	public List<ItemManagementDTO> customerListAll() throws Exception {
//		return session.selectList(namespace+".customerListAll");
//	}

}

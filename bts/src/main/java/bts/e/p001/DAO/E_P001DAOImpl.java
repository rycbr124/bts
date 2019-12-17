package bts.e.p001.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import bts.c.p001.vo.C_P001VO;
import bts.e.p001.VO.E_P001VO;
import bts.e.p001.VO.PagingVO;
import bts.e.p003.VO.E_P003VO_2;

@Repository("e_p001DAO")
public class E_P001DAOImpl implements E_P001DAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<E_P001VO> selectAccompanyList(PagingVO pagingVO, String category, String searchResult) throws DataAccessException {
		Map<String, Object> accSearch = new HashMap<>();
		int start = pagingVO.getStart();
		int end = pagingVO.getEnd();
		accSearch.put("start", start);
		accSearch.put("end", end);
		accSearch.put("category",category);
		accSearch.put("searchResult",searchResult);
		return sqlSession.selectList("mapper.accompany.accList",accSearch);
	}

	@Override
	public Integer pageCount(){
		return sqlSession.selectOne("mapper.accompany.listCount");
	}
	
	
	@Override
	public void updateViewcnt(int article_no) throws Exception {
		sqlSession.update("mapper.accompany.updateViewcnt",article_no);
	}

	@Override
	public E_P001VO accView(int article_no) throws Exception {
		return sqlSession.selectOne("mapper.accompany.accView",article_no);
	}

	@Override
	public List<C_P001VO> inclnView(String member_id) throws Exception {
		return sqlSession.selectList("mapper.accompany.accIncln",member_id);
	}

	@Override
	public List<E_P003VO_2> selectTag(int article_no) throws Exception {
		return sqlSession.selectList("mapper.accompany.tagView",article_no);
	}
	
	

}

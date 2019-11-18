package bts.e.p001.Service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;

import bts.e.p001.VO.PagingVO;
import bts.e.p001.VO.E_P001VO;

public class EventService {
	SqlSession sqlSession;
	 public List selectAllEventList(PagingVO vo) throws DataAccessException {
	      List<E_P001VO> eventList = null;
	      eventList = sqlSession.selectList("mapper.event.selectAllEventList", vo);
	      System.out.println("eventList"+eventList);
	      return eventList;
	   }
	  public int countBoard() {
	      return sqlSession.selectOne("mapper.accompany.countBoard");
	   }
}

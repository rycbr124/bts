package bts.e.p002.Service;

import bts.e.p002.VO.E_P002VO;
import bts.e.p003.VO.E_P003VO;

public interface E_P002Service {
	public void accDelete(int article_no) throws Exception;
	public void accTagDel(int article_no) throws Exception;
	public void accUpdate(E_P003VO e_p003Vo) throws Exception;
	public void accReq(E_P002VO e_p002VO) throws Exception;
}

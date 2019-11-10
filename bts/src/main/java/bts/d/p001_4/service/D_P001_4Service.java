package bts.d.p001_4.service;

import java.util.List;

import org.springframework.stereotype.Service;

import bts.d.p001_4.vo.D_P001_4VO;

@Service("d_p001_4Service")
public interface D_P001_4Service {
	public List<D_P001_4VO> searchArticle() throws Exception;
	public List<D_P001_4VO> contentsArticle(String article_no) throws Exception;

}

package bts.c.p005.service;

import java.util.List;

import bts.g.p001_2.vo.G_P001_2VO;

public interface C_P005Service {
	public List<G_P001_2VO> searchWishlist(String member_id) throws Exception;

}

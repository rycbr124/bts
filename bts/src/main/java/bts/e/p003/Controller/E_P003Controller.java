package bts.e.p003.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import bts.e.p003.VO.E_P003VO;

public interface E_P003Controller {
	public ResponseEntity accInsert(E_P003VO e_p003VO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}

package bts.h.p002.Controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import bts.h.p002.VO.H_P002VO;

public interface H_P002Controller {
	public ResponseEntity resveInsert(H_P002VO h_p002VO, String amount,String member_id
			, String room_no, String lodging_id, String in_date,String out_date,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity resveInsert1(H_P002VO h_p002VO, String amount1,String member_id
			, String room_no, String lodging_id, String in_date1,String out_date1,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity resveInsert2(H_P002VO h_p002VO, String amount2,String member_id
			, String room_no, String lodging_id, String in_date2,String out_date2,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}

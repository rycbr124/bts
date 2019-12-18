package bts.h.p002.Controller;


import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bts.h.p002.Service.H_P002Service;
import bts.h.p002.VO.H_P002VO;

@Controller("h_p002Controller")
@RequestMapping("/reserv")
public class H_P002ControllerImpl implements H_P002Controller{
	@Autowired
	H_P002Service h_p002Service;
	@Autowired
	H_P002VO h_p002VO;
	
	@Override
	@RequestMapping(value="/resveInsert", method= {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity resveInsert(
			@ModelAttribute("h_p002VO") H_P002VO h_p002VO,
			@RequestParam("amount0") String amount, @RequestParam("member_id") String member_id, 
			@RequestParam("room_no") String room_no, @RequestParam("lodging_id")String lodging_id,
			@RequestParam("in_date0") String in_date, 
			@RequestParam("out_date0") String out_date,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		System.out.println("amount: "+amount+" in_dateeeeeeee: "+ in_date + " out_dateeeeeeeeeeeeeeeeee: "+out_date);
		
		h_p002VO.setMember_id(member_id);
		h_p002VO.setRoom_no(room_no);
		h_p002VO.setLodging_id(lodging_id);
		h_p002VO.setAmount(amount);
		h_p002VO.setIn_date(in_date);
		h_p002VO.setOut_date(out_date);	

		System.out.println("member_iddddddddddddddddd:" + member_id);
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		h_p002Service.insertResve(h_p002VO);
		message = "<script>";
		message += "history.go(-1);";
		message += "</script>";
		resEntity = new ResponseEntity(message,responseHeaders,HttpStatus.OK);
		return resEntity;
	}
	
	@Override
	@RequestMapping(value="/resveInsert1", method= {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity resveInsert1(
			@ModelAttribute("h_p002VO") H_P002VO h_p002VO,
			@RequestParam("amount1") String amount1, @RequestParam("member_id") String member_id, 
			@RequestParam("room_no") String room_no, @RequestParam("lodging_id")String lodging_id,
			@RequestParam("in_date1") String in_date1, 
			@RequestParam("out_date1") String out_date1,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
			
		h_p002VO.setMember_id(member_id);
		h_p002VO.setRoom_no(room_no);
		h_p002VO.setLodging_id(lodging_id);
		h_p002VO.setAmount(amount1);
		h_p002VO.setIn_date(in_date1);
		h_p002VO.setOut_date(out_date1);	

		System.out.println("member_iddddddddddddddddd:" + member_id);
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		h_p002Service.insertResve(h_p002VO);
		message = "<script>";
		message += "history.go(-1);";
		message += "</script>";
		resEntity = new ResponseEntity(message,responseHeaders,HttpStatus.OK);
		return resEntity;
	}
	
	@Override
	@RequestMapping(value="/resveInsert2", method= {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity resveInsert2(
			@ModelAttribute("h_p002VO") H_P002VO h_p002VO,
			@RequestParam("amount2") String amount2, @RequestParam("member_id") String member_id, 
			@RequestParam("room_no") String room_no, @RequestParam("lodging_id")String lodging_id,
			@RequestParam("in_date2") String in_date2, 
			@RequestParam("out_date2") String out_date2,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
			
		h_p002VO.setMember_id(member_id);
		h_p002VO.setRoom_no(room_no);
		h_p002VO.setLodging_id(lodging_id);
		h_p002VO.setAmount(amount2);
		h_p002VO.setIn_date(in_date2);
		h_p002VO.setOut_date(out_date2);	

		System.out.println("member_iddddddddddddddddd:" + member_id);
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		h_p002Service.insertResve(h_p002VO);
		message = "<script>";
		message += "history.go(-1);";
		message += "</script>";
		resEntity = new ResponseEntity(message,responseHeaders,HttpStatus.OK);
		return resEntity;
	}
	


}

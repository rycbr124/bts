package bts.e.p002.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bts.e.p002.VO.E_P002VO;
import bts.e.p003.VO.E_P003VO;


public interface E_P002Controller {
	public String accDel(@RequestParam("article_no")int article_no,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity accUpdate(E_P003VO e_p003VO,@RequestParam("article_no")int article_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView accUpdateForm(@RequestParam("article_no")int article_no, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception;
	public void accReq(int article_no,String target_id,E_P002VO e_p002VO, HttpServletRequest request, HttpServletResponse response) throws Exception;
}

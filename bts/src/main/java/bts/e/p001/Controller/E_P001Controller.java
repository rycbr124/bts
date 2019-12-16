package bts.e.p001.Controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bts.e.p001.VO.E_P001VO;
import bts.e.p001.VO.PagingVO;

public interface E_P001Controller {
	public ModelAndView accWrite(HttpServletRequest request, HttpServletResponse response, HttpSession session)throws Exception;

	public ModelAndView selectAccompanyList(PagingVO pagingVO,Map<String,String> result,HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView accView(@RequestParam("article_no") int article_no,@RequestParam("member_id")String member_id ) throws Exception;
}

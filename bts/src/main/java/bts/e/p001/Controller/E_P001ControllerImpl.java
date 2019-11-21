package bts.e.p001.Controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bts.b.p001.VO.B_P001VO;
import bts.e.p001.Service.E_P001Service;
import bts.e.p001.VO.E_P001VO;
import bts.e.p001.VO.PagingVO;

@Controller("e_p001")
@RequestMapping(value = "/accompany")
public class E_P001ControllerImpl implements E_P001Controller {
	@Autowired
	E_P001Service e_p001Service;
	@Autowired
	B_P001VO b_p001VO;
	@Autowired
	E_P001VO e_p001VO;
	

	@Override
	@RequestMapping(value = "/accWrite")
	public ModelAndView accWrite(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return null;
	}

	@Override
	@RequestMapping(value="/accMain")
	public ModelAndView selectAccompanyList(PagingVO pagingVO
			,@RequestParam(value="nowPage", required=false)String nowPage
			,@RequestParam(value="cntPerPage",required=false) String cntPerPage) throws Exception {
		ModelAndView mav = new ModelAndView("/e/p001/d001");
		int total = e_p001Service.listCount();
		if(nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		}else if(nowPage == null){
			nowPage = "1";
		}else if(cntPerPage == null) {
			cntPerPage = "5";
		}
		pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		mav.addObject("paging",pagingVO);
		List<E_P001VO> list = e_p001Service.selectAccompanyList(pagingVO);
		mav.addObject("accList",list);
		return mav;
	}

	@Override
	public ModelAndView accView(@RequestParam int article_no, HttpSession session) throws Exception {
		e_p001Service.listCount();
		ModelAndView mav = new ModelAndView("/e/p001/d002");
		mav.addObject("article_no", e_p001Service.accView(article_no));
		return mav;
	}




}

package bts.c.p002.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bts.c.p002.Service.C_P002Service;
import bts.c.p007.VO.C_P007VO;
import bts.e.p001.VO.PagingVO;
import bts.e.p001.VO.PagingVO2;
import bts.e.p001.VO.PagingVO3;
import bts.f.p001_3.vo.F_P001_3VO;
import bts.i.p002.VO.I_P002VO_1;

@Controller("c_p002Controller")
@RequestMapping("/my")
public class C_P002ControllerImpl implements C_P002Controller{
	
	@Autowired
	C_P002Service c_p002Service;
	
	@Autowired
	F_P001_3VO f_p001_3VO;
	
	@Autowired
	I_P002VO_1 i_p002VO_1;
	
	@Autowired
	C_P007VO c_p007VO;
	
	
	@Override
	@RequestMapping(value="/myBoardList", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView myBoardList(PagingVO pagingVO,
			@RequestParam(value="nowPage", required=false)String nowPage, 
			@RequestParam(value="cntPerPage", required=false)String cntPerPage,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView("/c/p002/d001");
		HttpSession session = request.getSession();
		String member_id = (String) session.getAttribute("member_id");
		System.out.println("member_idddddd:" + member_id);
		int total = c_p002Service.reviewListCount(member_id);
		if(nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		}else if(nowPage == null) {
			nowPage = "1";
		}else if(cntPerPage == null) {
			cntPerPage = "5";
		}		
		pagingVO = new PagingVO(total, Integer.parseInt(nowPage),Integer.parseInt(cntPerPage));
		f_p001_3VO.setMember_id(member_id);
		pagingVO.setMember_id(member_id);
		
		List<F_P001_3VO> reviewList = c_p002Service.reviewList(pagingVO);

		mav.addObject("paging",pagingVO);
		mav.addObject("reviewList",reviewList);
		
		
		return mav;
	}


	@Override
	@RequestMapping(value="/myBoardList2", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView myBoardList2(PagingVO2 pagingVO2, String nowPage2, String cntPerPage2,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/c/p002/d002");
		HttpSession session = request.getSession();
		String member_id = (String) session.getAttribute("member_id");
		int total2 = c_p002Service.accListCount(member_id);
		
		if(nowPage2 == null && cntPerPage2 == null) {
			nowPage2 = "1";
			cntPerPage2 = "5";
		}else if(nowPage2 == null) {
			nowPage2 = "1";
		}else if(cntPerPage2 == null) {
			cntPerPage2 = "5";
		}
		
		pagingVO2 = new PagingVO2(total2, Integer.parseInt(nowPage2),Integer.parseInt(cntPerPage2));
		
		pagingVO2.setMember_id(member_id);
		List<C_P007VO> accList = c_p002Service.accList(pagingVO2);
		c_p007VO.setMember_id(member_id);
		mav.addObject("paging2",pagingVO2);
		mav.addObject("accList",accList);
		
		return mav;
	}


	@Override
	@RequestMapping(value="/myBoardList3", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView myBoardList3(PagingVO3 pagingVO3, String nowPage3, String cntPerPage3,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/c/p002/d003");
		HttpSession session = request.getSession();
		String member_id = (String) session.getAttribute("member_id");
		int total3 = c_p002Service.planListCount(member_id);
		
		if(nowPage3 == null && cntPerPage3 == null) {
			nowPage3 = "1";
			cntPerPage3 = "5";
		}else if(nowPage3 == null) {
			nowPage3 = "1";
		}else if(cntPerPage3 == null) {
			cntPerPage3 = "5";
		}
		pagingVO3 = new PagingVO3(total3, Integer.parseInt(nowPage3),Integer.parseInt(cntPerPage3));
		pagingVO3.setMember_id(member_id);
		i_p002VO_1.setMember_id(member_id);
		List<I_P002VO_1> planList = c_p002Service.planList(pagingVO3);
		mav.addObject("paging3",pagingVO3);
		mav.addObject("planList",planList);
		
		return mav;
	}

}

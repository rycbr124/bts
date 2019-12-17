package bts.e.p001.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import bts.b.p001.VO.B_P001VO;
import bts.c.p001.vo.C_P001VO;
import bts.e.p001.Service.E_P001Service;
import bts.e.p001.VO.E_P001VO;
import bts.e.p001.VO.PagingVO;
import bts.e.p003.VO.E_P003VO_2;

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
	public ModelAndView accWrite(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		session = request.getSession();
		if(session == null) {
			mav.setViewName("/e/p001/d001"); 
		}else{
			mav.setViewName("/e/p002/d001");
		}
		return mav;
	}

	@Override
	@RequestMapping(value="/accMain")
	public ModelAndView selectAccompanyList(PagingVO pagingVO
			,@RequestParam Map<String,String> result,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/e/p001/d001");
		
		String nowPage = result.get("nowPage");
		String cntPerPage = result.get("cntPerPage");
		String category = result.get("category");
		String searchResult = result.get("searchResult");
		
		
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
		List<E_P001VO> list = e_p001Service.selectAccompanyList(pagingVO,category,searchResult);
		mav.addObject("accList",list);
		return mav;
	}

	@Override
	@RequestMapping(value="/accView" , method= RequestMethod.GET)
	public ModelAndView accView(@RequestParam("article_no")int article_no,@RequestParam("member_id")String member_id) throws Exception {
		e_p001Service.updateViewcnt(article_no);
		List<C_P001VO> aclnList = e_p001Service.inclnView(member_id);
		List<E_P003VO_2> tagList = e_p001Service.selectTag(article_no);
		ModelAndView mav = new ModelAndView("/e/p001/d002");
		mav.addObject("tagList",tagList);
		System.out.println("taglist:" +tagList);
		mav.addObject("article_no", article_no);
		mav.addObject("member_id",member_id);
		mav.addObject("inclnView",aclnList);
		mav.addObject("accView", e_p001Service.accView(article_no));
		return mav;
	}
	

}

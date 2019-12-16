  package bts.d.p001_4.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import bts.b.p001.VO.B_P001VO;
import bts.d.p001_4.service.D_P001_4Service;
import bts.d.p001_4.vo.D_P001_4VO;
import bts.d.p001_4.vo.D_P001_4VO_2;
import bts.d.p001_4.vo.D_P001_4VO_5;
import bts.e.p001.VO.PagingVO;



@Controller("d_p001_4")
@RequestMapping(value="/community")
public class D_P001_4ControllerImpl implements D_P001_4Controller{
	
	@Autowired
	D_P001_4Service d_p001_4Service;
	
	@Autowired
	B_P001VO b_p001VO;
	
	@Autowired
	D_P001_4VO d_p001_4VO;
	
	@Autowired
	Provider<D_P001_4VO_2> d_p001_4VO_2;
	
	@Autowired
	Provider<bts.common.PagingVO> pagingProvider;
	
	@Autowired
	Provider<D_P001_4VO_5> ansProvider;
	
	
	private static final String menuName = "plan";
	private static final int comRangeRow=10;
	private static final int comRangePage=5;	
	
	@ModelAttribute("article_cd")
	public String getArticle_cd() throws Exception {
		String article_cd = d_p001_4Service.selectArticleCd(menuName);
		return article_cd;
	}

	@Override
	@RequestMapping(value="/plan_list" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView searchArticle(PagingVO pagingVO
			, @RequestParam Map<String, String> result, HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		String nowPage = result.get("nowPage");
		String cntPerPage = result.get("cntPerPage");
		String category = result.get("category");
		String searchResult = result.get("searchResult");
		
		
		int total = d_p001_4Service.listCount(category, searchResult);
		
		if(nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "6";
		}else if(nowPage == null) {
			nowPage = "1";
		}else if(cntPerPage == null) {
			cntPerPage = "6";			
		}
			HttpSession session = request.getSession();
			b_p001VO = (B_P001VO) session.getAttribute("memberInfo");
			
			ModelAndView mav = new ModelAndView("/d/p001_4/d001");
			pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			mav.addObject("paging", pagingVO);
			List<D_P001_4VO> listArticle = d_p001_4Service.searchArticle(pagingVO, category, searchResult);
			List<String> listThumnail = d_p001_4Service.findContentId();
			
			mav.addObject("listArticle", listArticle);
			mav.addObject("listThumnail", listThumnail);

			if(b_p001VO == null) {
				System.out.println("hi"); 
			}else {
				String member_id = b_p001VO.getMember_id();
				List<D_P001_4VO> myPlan = d_p001_4Service.selectMyplan(member_id);
				mav.addObject("myPlan", myPlan);
			}
			return mav;
		
		
	}

	@Override
	@RequestMapping(value="/plan_contents" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView contentsArticle(@ModelAttribute("article_cd") String article_cd, @RequestParam("plan_no") String plan_no, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<D_P001_4VO> result = d_p001_4Service.contentsArticle(plan_no);
		List<D_P001_4VO_2> detailPlanner = d_p001_4Service.detailPlanner(plan_no);
		d_p001_4Service.increaseCnt(plan_no);
		System.out.println("1111111111 : " + plan_no);
		Map<String,String> searchMap = new HashMap<>();
		searchMap.put("article_no", plan_no);
		searchMap.put("article_cd", article_cd);
		
		
		int totalCount = Integer.parseInt(d_p001_4Service.selectCommentTotal(searchMap));
		
		ModelAndView mav = new ModelAndView("/d/p001_4/d002");
		mav.addObject("result", result);
		mav.addObject("detailPlanner", detailPlanner);
		mav.addObject("initTotal",totalCount);
		mav.addObject("plan_no", plan_no);
	    mav.addObject("reqUrl","/community");
		return mav;
	}
	
	@Override
	@RequestMapping(value="/plan_write" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView writeArticle(@RequestParam("plan_no") String plan_no, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		ModelAndView mav = new ModelAndView("/d/p001_4/d003");
		mav.addObject("plan_no", plan_no);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/load_plan" ,method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String loadPlanner(@RequestParam("plan_no") String plan_no, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<D_P001_4VO_2> detailPlanner = d_p001_4Service.detailPlanner(plan_no);
		//List<D_P001_4VO_3> searchTag = d_p001_4Service.searchTag(plan_no);
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.writeValueAsString(detailPlanner);
		//String jsonTag = mapper.writeValueAsString(searchTag);
		
		System.out.println("플랜넘버 : " + plan_no);
		System.out.println("결과값 : " + jsonResult);
		
		return jsonResult;
	}
	
	
	@Override
	@RequestMapping(value="/plan_save" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView saveArticle(@RequestParam Map<String, String> result, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String size = request.getParameter("length");
		int length = Integer.parseInt(size); 
		System.out.println("사이즈 : " + length);
		
		List<String> desc = new ArrayList<String>();
		List<String> id = new ArrayList<String>();
		List<D_P001_4VO_2> voList = new ArrayList<D_P001_4VO_2>();
		
		for(int i = 0; i < length; i++) {
			desc.add(i, result.get("content" + i));
			id.add(i, result.get("content_id" + i));
		};
		System.out.println("p_no : " + result.get("p_no"));
		
		for(int i = 0; i < desc.size(); i++) {
			D_P001_4VO_2 vo = d_p001_4VO_2.get(); 
			vo.setPlan_no(result.get("p_no"));
			vo.setContent_id(id.get(i));
			vo.setPlan_desc(desc.get(i));
			vo.setTitle(result.get("title"));
			
			voList.add(i, vo);
			System.out.println("vo테스트 : " + vo.getPlan_desc());
		}
		d_p001_4Service.insertContent(voList);
		
		ModelAndView mav = new ModelAndView("/d/p001_4/d004");
		
		return mav;
	}

	@Override
	@RequestMapping(value="/plan_update" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView updateArticle(@RequestParam("plan_no") String plan_no, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("수정");
		List<D_P001_4VO_2> detailPlanner = d_p001_4Service.detailPlanner(plan_no);
		ModelAndView mav = new ModelAndView("/d/p001_4/d005");
		mav.addObject("detailPlanner", detailPlanner);
		mav.addObject("plan_no", plan_no);
		return mav;
	}

	@Override
	@RequestMapping(value="/plan_delete" ,method={RequestMethod.POST,RequestMethod.GET}, produces = "text/html; charset=utf8")
	public @ResponseBody String deleteArticle(@RequestParam("plan_no") String plan_no, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("삭제");
		String message = null;
		d_p001_4Service.deletePlan(plan_no);
		message = "<script>";
		message += "alert('게시글이 삭제되었습니다.');";
		message += "location.href='/bts/community/plan_list'";
		message += "</script>";
		
		return message;
		
	}

	@Override
	@RequestMapping(value="/plan_modify" ,method={RequestMethod.POST,RequestMethod.GET}, produces = "text/html; charset=utf8")
	public ModelAndView modifyArticle(@RequestParam Map<String, String> result, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String size = request.getParameter("length");
		int length = Integer.parseInt(size); 
		System.out.println("22222 : " + length);
		
		List<String> desc = new ArrayList<String>();
		List<String> id = new ArrayList<String>();
		List<D_P001_4VO_2> voList = new ArrayList<D_P001_4VO_2>();
		
		for(int i = 0; i < length; i++) {
			desc.add(i, result.get("content" + i));
			id.add(i, result.get("content_id" + i));
		};
		System.out.println("p_no : " + result.get("p_no"));
		for(int i = 0; i < desc.size(); i++) {
			D_P001_4VO_2 vo = d_p001_4VO_2.get(); 
			vo.setPlan_no(result.get("p_no"));
			vo.setContent_id(id.get(i));
			vo.setPlan_desc(desc.get(i));
			vo.setTitle(result.get("title"));
			
			voList.add(i, vo);
		}
		d_p001_4Service.updateContent(voList);
		
		ModelAndView mav = new ModelAndView("/d/p001_4/d004");
		
		return mav;
	}

	@Override
	@ResponseBody
	@RequestMapping(value="/comment" ,method={RequestMethod.POST,RequestMethod.GET})
	public String commentPaging(@ModelAttribute("article_cd") String article_cd, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String selectPage = request.getParameter("curPage");
		String article_no = request.getParameter("article_no");
		
		Map<String,String> searchMap = new HashMap<>();
		searchMap.put("article_no", article_no);
		searchMap.put("article_cd", article_cd);
		
		int totalCount = Integer.parseInt(d_p001_4Service.selectCommentTotal(searchMap));
		bts.common.PagingVO pvo = pagingProvider.get();
		int curPage = totalCount;
		try {
			if(selectPage!=null) {
				curPage = Integer.parseInt(selectPage);
			}
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		pvo.setPaging(curPage, totalCount, comRangePage, comRangeRow);
		searchMap.put("startRow", Integer.toString(pvo.getStartRow()));
		searchMap.put("endRow", Integer.toString(pvo.getEndRow()));
		
		List<D_P001_4VO_5> comments = d_p001_4Service.selectAnswerList(searchMap);
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("comments", comments);
		resultMap.put("paging", pvo);
		
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(resultMap);
		return result;
	}

	@Override
	@RequestMapping(value="/comment/write" ,method={RequestMethod.POST,RequestMethod.GET})
	public String commentWrite(@ModelAttribute("article_cd") String article_cd, RedirectAttributes redirect, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String comment = request.getParameter("input-comment");
		int nowpage = Integer.parseInt(request.getParameter("article_no"));
		B_P001VO b_p001VO= (B_P001VO) request.getSession().getAttribute("memberInfo");

		D_P001_4VO_5 d_p001_4VO_5 = ansProvider.get();
		d_p001_4VO_5.setAnswer_desc(comment);
		d_p001_4VO_5.setArticle_cd(article_cd);
		d_p001_4VO_5.setArticle_no(nowpage);
		d_p001_4VO_5.setMember_id(b_p001VO.getMember_id());
		d_p001_4Service.insertAnswer(d_p001_4VO_5);
		
		redirect.addAttribute("plan_no",nowpage);
		return "redirect:/community/plan_contents";
	}

	@Override
	@ResponseBody
	@RequestMapping(value="/comment/delete" ,method={RequestMethod.POST})
	public String commentDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String answer_no = request.getParameter("answer_no");
		int result = d_p001_4Service.deleteAnswer(answer_no);
		if(result==1) {
			return "true";						
		}else {
			return "false";			
		}
	}

	@Override
	@ResponseBody
	@RequestMapping(value="/search" ,method={RequestMethod.POST,RequestMethod.GET})
	public String searchPlan(@RequestParam("searchResult") String searchResult, @RequestParam("category") String category, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("search 값 : " + searchResult);
		System.out.println("category 값 : " + category);
		if(category.equals("제목")) {
			d_p001_4Service.searchTitle(searchResult);
			System.out.println("search 결과 값 : " + d_p001_4Service.searchTitle(searchResult));
		}
		
		return null;
	}	



}
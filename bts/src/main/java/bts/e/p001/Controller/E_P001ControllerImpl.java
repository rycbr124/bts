package bts.e.p001.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bts.e.p001.Service.EventService;
import bts.e.p001.VO.PagingVO;

@Controller("e_p001")
@RequestMapping(value = "/accompany")
public class E_P001ControllerImpl implements E_P001Controller {
	@Autowired
	EventService eventService;
	
	@Override
	@RequestMapping(value = "/accMain")
	public ModelAndView e_p001_d001(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		try {
			mav.setViewName("/e/p001/d001");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@Override
	@RequestMapping(value = "/accWrite")
	public ModelAndView accWrite(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return null;
	}

	@Override
	@RequestMapping(value = "/paging", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listEvent(PagingVO vo, @RequestParam(value = "nowPage", required = false) String nowPage,
			@RequestParam(value = "cntPerPage", required = false) String cntPerPage, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = "event";

		int total = eventService.countBoard();
		System.out.println("33333333333333333333333333333333333333333" + total);
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "5";
		}
		vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));

		//List eventList = eventService.listEvent(vo);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("paging", vo);
		//mav.addObject("eventList", eventList);
		return mav;
	}

}

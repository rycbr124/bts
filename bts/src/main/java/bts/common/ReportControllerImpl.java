package bts.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import bts.b.p001.VO.B_P001VO;

@RestController
@RequestMapping(value="/report")
public class ReportControllerImpl implements ReportController {
	@Autowired
	B_P001VO b_p001VO;
	@Autowired
	ReportVO reportVO;
	
	@Override
	@RequestMapping(value="/report", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView report(HttpServletRequest request, HttpServletResponse response)throws Exception{
		HttpSession session = request.getSession();
		b_p001VO = (B_P001VO) session.getAttribute("memberInfo");
		String member_id = b_p001VO.getMember_id();
		reportVO.setMember_id(member_id);
		ModelAndView mav = new ModelAndView("/common/report");
		return mav;
	}
}

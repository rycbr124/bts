package bts.b.p001.Controller;

import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bts.b.p001.Service.B_P001Service;
import bts.b.p001.VO.B_P001VO;

@Controller("b_p001")
@RequestMapping(value = "/signup")
public class B_P001ControllerImpl implements B_P001Controller {
	
	
	@Autowired
	B_P001Service d001Service;
	@Autowired
	B_P001VO d001vo;
	
	@Override
	@RequestMapping(value = "/signup", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView b_p001_d001(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView("/b/p001/d001");
		return mav;
	}

	@Override
	@RequestMapping(value = "/signup2", method = RequestMethod.POST)
	public ResponseEntity addMember(@ModelAttribute("memberVO") B_P001VO member, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		System.out.println("sql : " + member);
		try {
			
			d001Service.addMember(member);
			message = "<script>";
			message += " alert('회원 가입을 마쳤습니다.메인페이지로 이동합니다.');";
			message += " location.href='" + request.getContextPath() + "/main/main';";
			message += " </script>";

		} catch (Exception e) {
			message = "<script>";
			message += " alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요');";
			message += " location.href='" + request.getContextPath() + "/signup/signup';";
			message += " </script>";
			e.printStackTrace();
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}

	@Override
	@RequestMapping(value="/login", method= RequestMethod.POST)
	public ModelAndView login(@RequestParam Map<String, String> loginMap, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		
		d001vo = d001Service.login(loginMap);
		if(d001vo!= null && d001vo.getMember_id()!=null){
			HttpSession session=request.getSession();
			session=request.getSession();
			session.setAttribute("isLogOn", true);
			session.setAttribute("memberInfo",d001vo);
			System.out.println("성공");
			mav.setViewName("/z/p000/d001");
		
		}else{
			String message="아이디나  비밀번호가 틀립니다. 다시 로그인해주세요";
			mav.addObject("message", message);
			System.out.println("실패:" + message);
			mav.setViewName("/z/p000/d001");
		}
		return mav;
	}
	
}

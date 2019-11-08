package bts.b.p003.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface B_P003Controller {
	public ModelAndView b_p003_d001(HttpServletRequest request, HttpServletResponse response)throws Exception;
	public ModelAndView b_p003_d002(HttpServletRequest request, HttpServletResponse response)throws Exception;
	public String find_id(HttpServletResponse response, @RequestParam("email")String email, Model model) throws Exception;
}

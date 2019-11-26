package bts.f.p001_3.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface F_P001_3Controller {
	public ModelAndView searchReview(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView searchArticle(@RequestParam(value="article", required=false) String articleNo,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public String commentPaging(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public String commentWrite(RedirectAttributes redirect,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public String commentDelete(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView write(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView mod(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public String upload(HttpServletRequest request, HttpServletResponse response,@RequestParam MultipartFile upload) throws Exception;
	public String modUpload(RedirectAttributes redirect,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public void endWrite(HttpServletRequest request, HttpServletResponse response) throws Exception;
}

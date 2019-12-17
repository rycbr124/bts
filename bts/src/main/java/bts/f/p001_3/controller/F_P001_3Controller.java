package bts.f.p001_3.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface F_P001_3Controller {
//	public ModelAndView searchReview(String article_cd,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView searchArticle(String article_cd,@RequestParam(value="article", required=false) String articleNo,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public String commentPaging(String article_cd,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public String commentWrite(String article_cd,RedirectAttributes redirect,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public String commentDelete(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView write(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView mod(String article_cd,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public String upload(HttpServletRequest request, HttpServletResponse response,@RequestParam MultipartFile upload) throws Exception;
	public String modUpload(String article_cd,RedirectAttributes redirect,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public void endWrite(String article_cd,HttpServletRequest request, HttpServletResponse response) throws Exception;
}

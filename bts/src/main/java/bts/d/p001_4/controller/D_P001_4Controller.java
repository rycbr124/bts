package bts.d.p001_4.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bts.e.p001.VO.PagingVO;

public interface D_P001_4Controller {
	 public ModelAndView searchArticle(PagingVO pagingVO, @RequestParam(value="nowPage", required=false)String nowPage
				, @RequestParam(value="cntPerPage", required=false)String cntPerPage, HttpServletRequest request, HttpServletResponse response) throws Exception;
	 public ModelAndView contentsArticle(@RequestParam String article_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
	 public ModelAndView writeArticle(HttpServletRequest request, HttpServletResponse response) throws Exception;
	 public @ResponseBody String loadPlanner(@RequestParam("plan_no") String plan_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
	 public ModelAndView saveArticle(@RequestParam Map<String, String> result, HttpServletRequest request, HttpServletResponse response) throws Exception;
	 public ModelAndView updateArticle(@RequestParam("plan_no") String plan_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
	 public @ResponseBody String deleteArticle(@RequestParam("plan_no") String plan_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
	 public ModelAndView modifyArticle(@RequestParam Map<String, String> result, HttpServletRequest request, HttpServletResponse response) throws Exception;
}


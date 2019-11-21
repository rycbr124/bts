package bts.d.p001_4.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bts.d.p001_4.vo.D_P001_4VO_4;

public interface D_P001_4Controller {
	 public ModelAndView searchArticle(HttpServletRequest request, HttpServletResponse response) throws Exception;
	 public ModelAndView contentsArticle(@RequestParam String article_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
	 public ModelAndView writeArticle(HttpServletRequest request, HttpServletResponse response) throws Exception;
	 public @ResponseBody String loadPlanner(@RequestParam("plan_no") String plan_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
	 public ModelAndView saveArticle(@RequestParam Map<String, String> result, HttpServletRequest request, HttpServletResponse response) throws Exception;
	 public ModelAndView updateArticle(@RequestParam("plan_no") String plan_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
	 public @ResponseBody String deleteArticle(@RequestParam("plan_no") String plan_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
	 //public @ResponseBody List<D_P001_4VO_4> commentList(@RequestParam("article_no") String article_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
}


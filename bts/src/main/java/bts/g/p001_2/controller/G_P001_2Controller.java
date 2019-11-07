package bts.g.p001_2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public interface G_P001_2Controller {
   public ModelAndView P001_D001(HttpServletRequest request, HttpServletResponse response) throws Exception;
   public ModelAndView P001_D002(HttpServletRequest request, HttpServletResponse response) throws Exception;
   public ModelAndView P001_D003(@RequestParam String contentid, HttpServletRequest request, HttpServletResponse response) throws Exception;
   public ModelAndView P001_D004(@RequestParam String contentid, HttpServletRequest request, HttpServletResponse response) throws Exception;
   public @ResponseBody String wishList(@RequestParam("contentid") String contentid, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
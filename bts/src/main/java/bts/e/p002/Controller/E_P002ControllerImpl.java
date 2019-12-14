package bts.e.p002.Controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Provider;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import bts.e.p002.Service.E_P002Service;
import bts.e.p002.VO.E_P002VO;
import bts.e.p003.Service.E_P003Service;
import bts.e.p003.VO.E_P003VO;
import bts.e.p003.VO.E_P003VO_2;


@Controller("e_p002Controller")
@RequestMapping("/accompany3")
public class E_P002ControllerImpl implements E_P002Controller{

	@Autowired
	E_P002Service e_p002Service;
	@Autowired
	Provider<E_P003VO> e_p003VO;
	@Autowired
	Provider<E_P003VO_2> e_p003VO_2;
	@Autowired
	E_P003Service e_p003Service;
	
	private static final String imageUrl = "C:\\Users\\bit\\git\\bts\\bts\\src\\main\\webapp\\resources\\image\\board";
	private static final String metaUrl = "C:\\MyProject\\WorkSpace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\bts\\resources\\image\\board";
	private static final String mappingUrl = "/resources/image/board";
	

	@Override
	@RequestMapping(value="/accDel", method=RequestMethod.GET)
	public String accDel(@RequestParam("article_no")int article_no, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		e_p002Service.accDelete(article_no);
		e_p002Service.accTagDel(article_no);		
		return "forward:/accompany/accMain";
	}
	
	@Override
	@RequestMapping("/accUpdateForm")
	public ModelAndView accUpdateForm(@RequestParam("article_no") int article_no, HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		ModelAndView mav = new ModelAndView("/e/p002/d002");

		mav.addObject("article_no",article_no);
		return mav;
	}


	@Override
	@RequestMapping(value="/accUpdate", method= {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity accUpdate(@ModelAttribute("e_p003VO")E_P003VO e_p003VO,@RequestParam("article_no")int article_no, HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		ObjectMapper mapper = new ObjectMapper();
		ArrayList imageList = mapper.readValue(request.getParameter("imageList"), ArrayList.class);
		String thumb = null;
		String member_id = (String) session.getAttribute("member_id");
		e_p003VO.setMember_id(member_id);
		System.out .println("accInsert:" + member_id);
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		if(!imageList.isEmpty()) {
			thumb = (String) imageList.get(0);
			thumb = thumb.substring(request.getContextPath().length());
		}
		OutputStream out = null;
		try {
			for(int i=0; i<imageList.size(); i++) {
				String srcUrl = (String) imageList.get(i);
				srcUrl = srcUrl.substring(srcUrl.indexOf(mappingUrl)+mappingUrl.length());
				srcUrl = srcUrl.replace("/", "\\");
				
				File downloadFile = new File(metaUrl+"\\"+srcUrl);
				File uploadFile = new File(imageUrl+"\\"+srcUrl);
				
				if(!uploadFile.getParentFile().exists()) {
					uploadFile.getParentFile().mkdirs();
				}
				downloadFile.renameTo(uploadFile);
			}
			
			System.out.println("article_nooooooooooo: "+ article_no);
			ArrayList tagList = mapper.readValue(request.getParameter("tagList"),ArrayList.class);
			e_p003VO.setContent(request.getParameter("editor"));
			e_p002Service.accUpdate(e_p003VO);
			e_p002Service.accTagDel(article_no);
			List<E_P003VO_2> tagInsert = new ArrayList<>();
			for(int i=0; i<tagList.size(); i++) {
				E_P003VO_2 tagVO = e_p003VO_2.get();
				System.out.println("123taglist: "+ tagList);							
				tagVO.setArticle_no(e_p003VO.getArticle_no());
				tagVO.setTag_name((String) tagList.get(i));
				tagInsert.add(tagVO);
				System.out.println("tagInsertzzzzz:"+tagInsert);
			}
			if(!tagList.isEmpty()) {
				System.out.println("tagInsertzzzzz:"+tagInsert);
				e_p003Service.insertTag(tagInsert);
			}
			System.out.println("tagInserttttt:" + tagInsert);
			message = "<script>";
			message += "alert('글작성을 마쳤습니다. 목록으로 이동합니다.');";
			message += "location.href='"+request.getContextPath() +"/accompany/accMain';";
			message += "</script>";
		}catch(Exception e) {
			message = "<script>";
			message += "alert('다시 시도해 주세요.');";
			message += "history.go(-1);";
			message += "</script>";
			e.printStackTrace();
		}finally {
			try {
				if(out != null) {
					out.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}

	@Override
	@ResponseBody
	@RequestMapping(value="/accReq", method= {RequestMethod.GET, RequestMethod.POST})
	public void accReq(@RequestParam("article_no")int article_no,@RequestParam("target_id")String target_id, E_P002VO e_p002VO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String member_id = (String)session.getAttribute("member_id");
		e_p002VO.setArticle_no(article_no);
		e_p002VO.setMember_id(member_id);

		e_p002Service.accReq(e_p002VO);

	}
}

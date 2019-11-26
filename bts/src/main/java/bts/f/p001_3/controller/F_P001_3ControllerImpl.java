package bts.f.p001_3.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import bts.b.p001.VO.B_P001VO;
import bts.common.PagingVO;
import bts.f.p001_3.service.F_P001_3Service;
import bts.f.p001_3.vo.F_P001_3VO;
import bts.f.p001_3.vo.F_P001_3VO_2;
import bts.f.p001_3.vo.F_P001_3VO_3;

@Controller("f_p001_3")
@RequestMapping(value="/community/review")
public class F_P001_3ControllerImpl implements F_P001_3Controller{
	@Autowired
	Provider<F_P001_3VO> f_p001_3Provider;

	@Autowired
	Provider<F_P001_3VO_2> tagProvider;	

	@Autowired
	Provider<F_P001_3VO_3> ansProvider;		
	
	@Autowired
	Provider<PagingVO> pagingProvider;		
	
	@Autowired
	F_P001_3Service f_p001_3Service;
	
	private static final String imageUrl = "D:\\git\\bts\\bts\\src\\main\\webapp\\resources\\image\\board";
	private static final String metaUrl = "D:\\project\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\bts\\resources\\image\\board";
	private static final String mappingUrl = "/resources/image/board";
	private static final String article_cd="3";
	private static final int rangeRow = 6;
	private static final int rangePage = 5;
	private static final int comRangeRow=10;
	private static final int comRangePage=5;
	
	@Override
	@RequestMapping(value="/list" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView searchReview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/f/p001_3/d001");
		String inputPage = request.getParameter("curPage");
		
		int curPage = 1;
		try {
			if(inputPage!=null) {
				curPage = Integer.parseInt(inputPage);
			}
		}catch(NumberFormatException e) {
			e.printStackTrace();
			curPage=1;
		}
		int totalCount = Integer.parseInt(f_p001_3Service.selectReviewTotal());
		PagingVO pvo = pagingProvider.get();
		pvo.setPaging(curPage, totalCount, rangePage, rangeRow);

		Map<String,Integer> searchMap = new HashMap<>();
		searchMap.put("startRow", pvo.getStartRow());
		searchMap.put("endRow", pvo.getEndRow());
		
		List<F_P001_3VO> reviewList = new ArrayList<>();
		reviewList = f_p001_3Service.selectReviewList(searchMap);
		
		mav.addObject("paging",pvo);
		mav.addObject("articleList", reviewList);
		return mav;
	}
	
	@RequestMapping(value="/contents" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView searchArticle(@RequestParam(value="article", required=false) String articleNo,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/f/p001_3/d002");
		Map<String,String> searchMap = new HashMap<>();
		searchMap.put("article_no", articleNo);
		searchMap.put("article_cd", article_cd);
		F_P001_3VO resultVO = f_p001_3Service.selectReviewContents(searchMap);

		int totalCount = Integer.parseInt(f_p001_3Service.selectCommentTotal(articleNo));
		PagingVO pvo = pagingProvider.get();
		pvo.setPaging(totalCount, totalCount, comRangePage, comRangeRow);
		searchMap.put("startRow", Integer.toString(pvo.getStartRow()));
		searchMap.put("endRow", Integer.toString(pvo.getEndRow()));
		
		List<F_P001_3VO_3> comments = f_p001_3Service.selectAnswerList(searchMap);
		mav.addObject("result", resultVO);
		mav.addObject("comments", comments);
		mav.addObject("paging", pvo);
		return mav;
	}

	@ResponseBody
	@RequestMapping(value="/comment" ,method={RequestMethod.POST,RequestMethod.GET})
	public String commentPaging(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String selectPage = request.getParameter("curPage");
		String article_no = request.getParameter("article_no");
		
		Map<String,String> searchMap = new HashMap<>();
		searchMap.put("article_no", article_no);
		searchMap.put("article_cd", article_cd);
		
		int totalCount = Integer.parseInt(f_p001_3Service.selectCommentTotal(article_no));
		PagingVO pvo = pagingProvider.get();
		pvo.setPaging(Integer.parseInt(selectPage), totalCount, comRangePage, comRangeRow);
		searchMap.put("startRow", Integer.toString(pvo.getStartRow()));
		searchMap.put("endRow", Integer.toString(pvo.getEndRow()));
		
		List<F_P001_3VO_3> comments = f_p001_3Service.selectAnswerList(searchMap);
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("comments", comments);
		resultMap.put("paging", pvo);
		
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(resultMap);
		return result;
	}
	
	@RequestMapping(value="/comment/write" ,method={RequestMethod.POST,RequestMethod.GET})
	public String commentWrite(RedirectAttributes redirect,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String comment = request.getParameter("input-comment");
		int nowpage = Integer.parseInt(request.getParameter("article_no"));
		B_P001VO b_p001VO= (B_P001VO) request.getSession().getAttribute("memberInfo");

		F_P001_3VO_3 f_p001_3VO_3 = ansProvider.get();
		f_p001_3VO_3.setAnswer_desc(comment);
		f_p001_3VO_3.setArticle_cd(article_cd);
		f_p001_3VO_3.setArticle_no(nowpage);
		f_p001_3VO_3.setMember_id(b_p001VO.getMember_id());
		f_p001_3Service.insertAnswer(f_p001_3VO_3);
		
		redirect.addAttribute("article",nowpage);
		return "redirect:/community/review/contents";
	}	
	
	@ResponseBody
	@RequestMapping(value="/comment/delete" ,method={RequestMethod.POST})
	public String commentDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String answer_no = request.getParameter("answer_no");
		int result = f_p001_3Service.deleteAnswer(answer_no);
		if(result==1) {
			return "true";						
		}else {
			return "false";			
		}
	}	
	
	@RequestMapping(value="/write" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView write(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/f/p001_3/d003");
		mav.addObject("uri",request.getRequestURI());
		return mav;
	}

	@RequestMapping(value="/write/mod" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView mod(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/f/p001_3/d003");
		Map<String,String> searchMap = new HashMap<>();
		searchMap.put("article_no", request.getParameter("article_no"));
		searchMap.put("article_cd", article_cd);
		
		F_P001_3VO resultVO = f_p001_3Service.selectReviewContents(searchMap);
		mav.addObject("contentsMod", resultVO);
		mav.addObject("uri",request.getRequestURI());
		return mav;
	}	
	
	@ResponseBody
	@RequestMapping(value="/image" ,method={RequestMethod.POST,RequestMethod.GET}, produces="application/json;charset=UTF-8")
	public String upload(HttpServletRequest request, HttpServletResponse response,@RequestParam MultipartFile upload) throws Exception {
		
		OutputStream out = null;	
		try {
			String fileName = upload.getOriginalFilename();
			byte[] bytes = upload.getBytes();
			String saveDate=new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
			String randID = UUID.randomUUID().toString();
			
			String metaPath=metaUrl+"\\"+saveDate+"\\"+randID+"_"+fileName;
			File metaFile = new File(metaPath);
			
			if(!metaFile.getParentFile().exists()) {
				metaFile.getParentFile().mkdirs();
			}
			
			out = new FileOutputStream(metaFile);
			out.write(bytes);
			
			String fileUrl = request.getContextPath()+mappingUrl+"/"+saveDate+"/"+randID+"_"+fileName;
			JSONObject obj = new JSONObject();
			obj.put("uploaded", 1);
			obj.put("fileName",fileName);
			obj.put("url",fileUrl);
			return obj.toJSONString();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
	}

	@RequestMapping(value="/upload/mod" ,method={RequestMethod.POST,RequestMethod.GET})
	public String modUpload(RedirectAttributes redirect,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ArrayList imageList = mapper.readValue(request.getParameter("imageList"), ArrayList.class);
		String thumb = null;
		
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
				
				if(downloadFile.exists()) {
					downloadFile.renameTo(uploadFile);
				}
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
		
		F_P001_3VO vo = f_p001_3Provider.get();
		vo.setArticle_cd(article_cd);
		vo.setTitle(request.getParameter("title"));
		vo.setContents(request.getParameter("editor"));
		vo.setThumbnail_img(thumb);
		vo.setArticle_no(Integer.parseInt(request.getParameter("article_no")));
		
		ArrayList tagList = mapper.readValue(request.getParameter("tagList"), ArrayList.class);
		f_p001_3Service.updateArticle(vo);
		List<F_P001_3VO_2> updateTagList = new ArrayList<>();
		for(int i=0; i<tagList.size();i++) {
			F_P001_3VO_2 tagVO = tagProvider.get();
			tagVO.setArticle_no(vo.getArticle_no());
			tagVO.setArticle_cd(article_cd);
			tagVO.setTag_name((String) tagList.get(i));
			updateTagList.add(tagVO);
		}
		if(!updateTagList.isEmpty()) {
			f_p001_3Service.updateTagList(updateTagList,vo.getArticle_no());			
		}
		redirect.addAttribute("article",vo.getArticle_no());
		return "redirect:/community/review/contents";
	}	
	
	@RequestMapping(value="/upload" ,method={RequestMethod.POST,RequestMethod.GET})
	public void endWrite(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ArrayList imageList = mapper.readValue(request.getParameter("imageList"), ArrayList.class);
		String thumb = null;
		
		if(!imageList.isEmpty()) {
			thumb = (String) imageList.get(0);
			thumb = thumb.substring(request.getContextPath().length());
		}
		
		OutputStream out = null;
//		InputStream in = null;
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
				
				/*
				in = new FileInputStream(downloadFile);
				out = new FileOutputStream(uploadFile);
				int data =0;
				
				while((data=in.read())!=-1) {
					out.write(data);
				}				
				 * */
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
		
		F_P001_3VO vo = f_p001_3Provider.get();
		B_P001VO b_p001VO= (B_P001VO) request.getSession().getAttribute("memberInfo");
		vo.setMember_id(b_p001VO.getMember_id());
		vo.setArticle_cd(article_cd);
		vo.setTitle(request.getParameter("title"));
		vo.setContents(request.getParameter("editor"));
		vo.setThumbnail_img(thumb);
		
		ArrayList tagList = mapper.readValue(request.getParameter("tagList"), ArrayList.class);
		f_p001_3Service.insertArticle(vo);
		List<F_P001_3VO_2> insertTagList = new ArrayList<>();
		for(int i=0; i<tagList.size();i++) {
			F_P001_3VO_2 tagVO = tagProvider.get();
			tagVO.setArticle_no(vo.getArticle_no());
			tagVO.setArticle_cd(article_cd);
			tagVO.setTag_name((String) tagList.get(i));
			insertTagList.add(tagVO);
		}
		if(!insertTagList.isEmpty()) {
			f_p001_3Service.insertTagList(insertTagList);			
		}
		response.sendRedirect("/bts/community/review/list");
	}
	
}

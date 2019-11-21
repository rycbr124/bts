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

import com.fasterxml.jackson.databind.ObjectMapper;

import bts.b.p001.VO.B_P001VO;
import bts.common.PagingVO;
import bts.f.p001_3.service.F_P001_3Service;
import bts.f.p001_3.vo.F_P001_3VO;
import bts.f.p001_3.vo.F_P001_3VO_2;

@Controller("f_p001_3")
@RequestMapping(value="/community/review")
public class F_P001_3ControllerImpl implements F_P001_3Controller{
	@Autowired
	Provider<F_P001_3VO> f_p001_3Provider;

	@Autowired
	Provider<F_P001_3VO_2> tagProvider;	

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
		pvo.setRangePage(rangePage);
		pvo.calTotalPage(totalCount, rangeRow);
		
		if(curPage<=0) {
			curPage=1;
		}else if(curPage>pvo.getTotalPage()) {
			curPage=pvo.getTotalPage();			
		}
		
		pvo.setCurPage(curPage);
		pvo.calStartEndPage();
		
		int endRow = curPage*rangeRow;
		int startRow = (endRow-rangeRow)+1;		
		Map<String,Integer> searchMap = new HashMap<>();
		searchMap.put("startRow", startRow);
		searchMap.put("endRow", endRow);
		
		List<F_P001_3VO> reviewList = new ArrayList<>();
		reviewList = f_p001_3Service.selectReviewList(searchMap);
		
		mav.addObject("paging",pvo);
		mav.addObject("articleList", reviewList);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/contents" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView searchArticle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/f/p001_3/d002");
		Map<String,String> searchMap = new HashMap<>();
		String articleNo = request.getParameter("article");
		searchMap.put("article_no", articleNo);
		searchMap.put("article_cd", article_cd);
		F_P001_3VO resultVO = f_p001_3Service.selectReviewContents(searchMap);
		mav.addObject("result", resultVO);
		return mav;
	}
	
	@RequestMapping(value="/write" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView write(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/f/p001_3/d003");
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
				System.out.println(downloadFile.renameTo(uploadFile));
				
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

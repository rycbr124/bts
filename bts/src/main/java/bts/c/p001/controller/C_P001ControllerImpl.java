package bts.c.p001.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import bts.b.p001.VO.B_P001VO;
import bts.c.p001.service.C_P001Service;
import bts.c.p001.vo.C_P001VO;

@Controller("c_p001")
@RequestMapping(value = "/my")
public class C_P001ControllerImpl implements C_P001Controller {
	@Autowired
	B_P001VO b_p001VO;
	@Autowired
	C_P001Service c_p001Service;
	@Autowired
	Provider<C_P001VO> c_p001Provider;
	@Value("${file.myImage}")
	private String uploadPath;
	@Value("${file.myMetaImage}")
	private String metaPath;
	
	private static final Logger logger = LoggerFactory.getLogger(C_P001ControllerImpl.class);
	
	@Override
	@RequestMapping(value = "/profile")
	public ModelAndView pageInit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		List<C_P001VO> result = c_p001Service.selectCheckList();
		List<C_P001VO> result2 = c_p001Service.selectInclnList();
		HttpSession session = request.getSession();
		b_p001VO = (B_P001VO)session.getAttribute("memberInfo");
		
		List<C_P001VO> result5 = c_p001Service.selectCheckBoxList(b_p001VO.getMember_id());
		
		Map<String,List<C_P001VO>> resultMap = new TreeMap<>();
		for(int i=0;i<result.size();i++) {
			List<C_P001VO> inputList = new ArrayList<>();
			String name = result.get(i).getGroup_name();
			resultMap.put(result.get(i).getGroup_desc(),inputList );
			for(int j=0;j<result2.size();j++) {
				if(result2.get(j).getGroup_name().equals(name)) {
					inputList.add(result2.get(j));
				}
			}
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String tagResult = mapper.writeValueAsString(result5);
		
		ModelAndView mav = new ModelAndView("/c/p001/d001");
		mav.addObject("incln",resultMap);
		mav.addObject("selected",tagResult);
		return mav;
	}

	// 회원 정보 및 수정
	@RequestMapping(value = "/update")
	@ResponseBody
	public ResponseEntity updateMember(@ModelAttribute("memberVO") B_P001VO member, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ResponseEntity resEnt = null;
		String message = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			String test = member.getProfile_image();
			HttpSession session = request.getSession();
			String id = ((B_P001VO) session.getAttribute("memberInfo")).getMember_id();
			member.setMember_id(id);
			c_p001Service.updateMember(member);
			B_P001VO modMember = c_p001Service.selectMember(id);
			session.setAttribute("memberInfo", modMember);
			
			List<C_P001VO> searchList = new ArrayList<>();
			List<C_P001VO> groupList = c_p001Service.selectCheckList();
			
			for(int i=0;i<groupList.size();i++) {
				String group_name = request.getParameter(groupList.get(i).getGroup_name());
				if(group_name!=null) {
					C_P001VO vo = c_p001Provider.get();
					vo.setMember_id(id);
					vo.setIncln_cd(group_name);
					searchList.add(vo);
				}
			}
			

			c_p001Service.deleteMemberList(id);
			if(!searchList.isEmpty()) {
				c_p001Service.insertCheckMemberList(searchList);
			}
			
			
			response.sendRedirect("/bts/my/profile");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resEnt;
	}

	// 회원탈퇴 페이지
	@RequestMapping(value = "/exitMain")
	public String secession() {
		return "/c/p001/d002";
	}

	// 회원탈퇴
	@RequestMapping(value = "/exitMem")
	public ModelAndView secessionProc(B_P001VO b_p001VO, HttpSession session) {
		
		b_p001VO = (B_P001VO)session.getAttribute("memberInfo");
		c_p001Service.secession(b_p001VO, session);

		ModelAndView mav = new ModelAndView("/z/p000/d001");
		return mav;
	}

	// 패스워드 체크
	@RequestMapping(value = "/passCheck")
	@ResponseBody
	public String passCheck(String password, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		b_p001VO = (B_P001VO)session.getAttribute("memberInfo");
		
		Map<String,String> searchData = new HashMap<>();
		searchData.put("password", password);
		searchData.put("member_id", b_p001VO.getMember_id());
		
		String result = c_p001Service.passCheck(searchData);
		//
		return result;
	}
	
	@RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
	public String uploadForm(MultipartFile file, Model model) throws Exception {

		logger.info("originalName: " + file.getOriginalFilename());
		logger.info("size: " + file.getSize());
		logger.info("contentType: " + file.getContentType());

		String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());

		model.addAttribute("savedName", savedName);

		return "uploadResult";
	}

	private String uploadFile(String originalName, byte[] fileData) throws Exception {

		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalName;
		File target = new File(uploadPath, savedName);
		FileCopyUtils.copy(fileData, target);
		return savedName;
	}
	
	@ResponseBody
	@RequestMapping(value = "/uploadAjax", method = RequestMethod.POST)
	public String uploadAjax(MultipartFile file, String str, HttpSession session,
			HttpServletRequest request, B_P001VO vo) throws Exception {
			logger.info("originalName: " + file.getOriginalFilename());
			
	         ResponseEntity<String> img_path = new ResponseEntity<>(
	                 UploadUtil.uploadFile(uploadPath, metaPath, file.getOriginalFilename(), file.getBytes()),
	                 HttpStatus.CREATED);
	           String user_imgPath = (String) img_path.getBody();
	           
	           String localhost = "/resources/image/mypage/profileImage";
	           logger.info(user_imgPath);
	           vo.setProfile_image(localhost+user_imgPath);
	           B_P001VO id = (B_P001VO)session.getAttribute("memberInfo");
	           vo.setMember_id(id.getMember_id());
	           logger.info("file name : " + user_imgPath);
	           c_p001Service.updateimage(vo);
	           id.setProfile_image(localhost+user_imgPath);
	           return user_imgPath;
	}
	
}

package bts.c.p001.controller;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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

import bts.b.p001.VO.B_P001VO;
import bts.c.p001.service.C_P001Service;

@Controller("c_p001")
@RequestMapping(value = "/my")
public class C_P001ControllerImpl implements C_P001Controller {
	@Autowired
	B_P001VO b_p001VO;
	@Autowired
	C_P001Service c_p001Service;

	private static final Logger logger = LoggerFactory.getLogger(C_P001ControllerImpl.class);

	@Override
	@RequestMapping(value = "/profile")
	public ModelAndView pageInit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");

		ModelAndView mav = new ModelAndView("/c/p001/d001");
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
			System.out.println("1`11111111111111111111111111111111111  "+test);
			HttpSession session = request.getSession();
			String id = ((B_P001VO) session.getAttribute("memberInfo")).getMember_id();
			member.setMember_id(id);
			c_p001Service.updateMember(member);
			session.setAttribute("memberInfo", member);
			RequestDispatcher dispatch = request.getRequestDispatcher("/my/profile");
			dispatch.forward(request, response);
//			out.print("<script>");
//			out.print("alert('수정이 완료되었습니다')");
//			out.print("history.go(-1)");
//			out.print("</script>");
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
	public String passCheck(String password) {

		String result = c_p001Service.passCheck(password);
		//
		return result;
	}

	// 이미지 저장 경로
//	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) {
//		
//				private static String ARTICLE_IMAGE_REPO = "C:\\image_file";
//				Map <String, String> articleMap = upload(request, response);
//				String imageFileName = articleMap.get("profile_image");
//				B_P001VO.setImageFileName(profile_image)
//				File currentDirPath = new File(ARTICLE_IMAGE_REPO);
//				FileItem fileItem = (FileItem) items.get(i);
//				
//				articleMap.put(fileItem.profile_image(), image_file());
//				if(fileItem.getSize() > 0)
//				{
//					int idx = fileItem.profile_image().lastIndexIf("\\");
//					if(idx == -1)
//					{
//						idx = fileItem.profile_image().lastIndexOf("/");
//					}
//					
//					String fileName = fileItem.profile_image().substring(idx + 1);
//					File uploadFile = new File(currentDirPath + "\\" + fileName);
//					fileItem.write(uploadFile);
//				} 
//		return null;
//	}
	

	@Resource(name = "uploadPath")
	private String uploadPath;

	@RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
	public void uploadForm() throws Exception {
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
	@RequestMapping(value = "/uploadAjax", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String uploadAjax(MultipartFile file, String str, HttpSession session,
			HttpServletRequest request, B_P001VO vo) throws Exception {

		logger.info("originalName: " + file.getOriginalFilename());

			ResponseEntity<String> img_path = new ResponseEntity<>(
					UploadUtil.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()),
					HttpStatus.CREATED);
			String user_imgPath = (String) img_path.getBody();
			
			String localhost = "resources/image/mypage/profileImage";
			logger.info(user_imgPath);
			vo.setProfile_image(localhost+user_imgPath);
			B_P001VO id = (B_P001VO)session.getAttribute("memberInfo");
			vo.setMember_id(id.getMember_id());
			logger.info("file name : " + user_imgPath);
			c_p001Service.updateimage(vo);
			System.out.println("vooooooooooooooooooo:" + vo);
			return user_imgPath;
	}
	
}

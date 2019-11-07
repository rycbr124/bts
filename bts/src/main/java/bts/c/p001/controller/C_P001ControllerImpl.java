package bts.c.p001.controller;



import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.core.internal.resources.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bts.b.p001.VO.B_P001VO;
import bts.c.p001.service.C_P001Service;



@Controller("c_p001")
@RequestMapping(value="/my")
public class C_P001ControllerImpl implements C_P001Controller{
	@Autowired
	B_P001VO b_p001VO;
	@Autowired
	C_P001Service c_p001Service;
	
	@Override
	@RequestMapping(value="/profile")
	public ModelAndView pageInit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		ModelAndView mav = new ModelAndView("/c/p001/d001");
		return mav;
	}
	//회원 정보 및 수정
	@RequestMapping(value="/update")
	@ResponseBody
	public ResponseEntity updateMember(@ModelAttribute("memberVO") B_P001VO member,HttpServletRequest request, HttpServletResponse response) throws Exception{
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			HttpSession session = request.getSession();
			String id = ((B_P001VO) session.getAttribute("memberInfo")).getMember_id();
			member.setMember_id(id);
			c_p001Service.updateMember(member);
			session.setAttribute("memberInfo", member);
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/my/profile");
			dispatch.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resEnt;
	}

//	@Controller
//	public class FileUploadController {
//		@Autowired
//		FileUploadService fileUploadService;
//		
//		@RequestMapping("/form")
//		public Stringform() {
//			return "form";
//		}
//		
//		@RequestMapping("/upload")
//		public String upload(
//				Model model,
//				@RequestParam("email")String email,
//				@RequestParam("file1")MultipartFile file) {
//			
//			String url = fileUploadService.restore(file);
//			model.addAttribute("url", url);
//			return "result";
//		}
//	}
	//이미지 저장 경로
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

}

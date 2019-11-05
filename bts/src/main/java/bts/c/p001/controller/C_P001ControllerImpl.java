package bts.c.p001.controller;



import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
	
	@RequestMapping(value="/update")
	@ResponseBody
	public ResponseEntity updateMember(@ModelAttribute("memberVO") B_P001VO member,HttpServletRequest request, HttpServletResponse response) throws Exception{
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			String id = ((B_P001VO) request.getSession().getAttribute("memberInfo")).getMember_id();
			member.setMember_id(id);
			c_p001Service.updateMember(member);
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/my/profile");
			dispatch.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resEnt;
	}
	/*
	 // 회원 상세정보 조회
    
	
    // 회원 정보 수정 처리
    @RequestMapping("member/update.do")
    public String memberUpdate(@ModelAttribute B_P001VO vo, Model model){
        // 비밀번호 체크
        boolean result = C_P001Service.checkPw(vo.getmemberId(), vo.getpass());
        if(result){ // 비밀번호가 일치하면 수정 처리후, 전체 회원 목록으로 리다이렉트
        	C_P001Service.updateMember(vo);
            return "redirect:/member/list.do";
        } else { // 비밀번호가 일치하지 않는다면, div에 불일치 문구 출력, viwe.jsp로 포워드
            // 가입일자, 수정일자 저장
            B_P001VO vo2 = C_P001Service.viewMember(vo.getUserId());
            vo.setUserRegdate(vo2.getUserRegdate());
            vo.setUserUpdatedate(vo2.getUserUpdatedate());
            model.addAttribute("dto", vo);
            model.addAttribute("message", "비밀번호 불일치");
            return "member/member_view";
        }
        
    }

	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) {
		//이미지 저장 경로
				private static String ARTICLE_IMAGE_REPO = "C:\\image_file";
				Map <String, String> articleMap = upload(request, response);
				String imageFileName = articleMap.get("profile_image");
				B_P001VO.setImageFileName(profile_image)
				File currentDirPath = new File(ARTICLE_IMAGE_REPO);
				FileItem fileItem = (FileItem) items.get(i);
				
				articleMap.put(fileItem.profile_image(), image_file());
				if(fileItem.getSize() > 0)
				{
					int idx = fileItem.profile_image().lastIndexIf("\\");
					if(idx == -1)
					{
						idx = fileItem.profile_image().lastIndexOf("/");
					}
					
					String fileName = fileItem.profile_image().substring(idx + 1);
					File uploadFile = new File(currentDirPath + "\\" + fileName);
					fileItem.write(uploadFile);
				} 
		return null;
	}
	 * */

}

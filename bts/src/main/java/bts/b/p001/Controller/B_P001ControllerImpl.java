package bts.b.p001.Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import bts.b.p001.Naver.NaverLoginBO;
import bts.b.p001.Service.B_P001Service;
import bts.b.p001.VO.B_P001VO;
import bts.b.p001.VO.NaverVO;

@Controller("b_p001")
@RequestMapping(value = "/signup" )
public class B_P001ControllerImpl implements B_P001Controller {
	@Autowired
	B_P001Service d001Service;
	@Autowired
	B_P001VO d001vo;
	
	
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}

	public String getAccessToken(String authorize_code,HttpServletRequest request) {
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			String httpUrl = request.getRequestURL().toString();
			String reqUrl = httpUrl.substring(0,(httpUrl.indexOf(request.getContextPath())+request.getContextPath().length()));
			
			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=6a0602e55acf9e0f00406d7fb1f93b3d");
			sb.append("&redirect_uri="+reqUrl+"/signup/kakaoLogin");
			sb.append("&code=" + authorize_code);
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

			System.out.println("access_token : " + access_Token);
			System.out.println("refresh_token : " + refresh_Token);

			br.close();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return access_Token;
	}
	
	public B_P001VO getUserInfo(String access_Token) throws Exception{

		// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		B_P001VO userInfo = new B_P001VO();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("charset","utf-8");
			
			conn.setRequestMethod("POST");
			
			// 요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			

			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
			
			String id = element.getAsJsonObject().get("id").getAsString();
			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
			String email = kakao_account.getAsJsonObject().get("email").getAsString();
			String profile_image = properties.getAsJsonObject().get("profile_image").getAsString();
			String member_type = "kakao";
			
			userInfo.setMember_id(id);
			userInfo.setNick_name(nickname);
			userInfo.setProfile_image(profile_image);
			userInfo.setEmail(email);
			userInfo.setMember_type(member_type);
			
			
			if(d001Service.overlapped(id).equals("false")) {
				System.out.println("user Info : " + userInfo);
				d001Service.kakaoInsert(userInfo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userInfo;
	}

	@Override
	@RequestMapping(value = "/signup", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView b_p001_d001(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView("/b/p001/d001");
		return mav;
	}
	
	@RequestMapping(value="/loginPopup", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView loginPopup(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView("/z/p000/logininfo");
		return mav;
	}

	@Override
	@RequestMapping(value = "/signup2", method = RequestMethod.POST)
	public ResponseEntity addMember(@ModelAttribute("memberVO") B_P001VO member, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		System.out.println("sql : " + member);
		try {
			d001Service.addMember(member);
			message = "<script>";
			message += " alert('회원 가입을 마쳤습니다.메인페이지로 이동합니다.');";
			message += " location.href='" + request.getContextPath() + "/main/main';";
			message += " </script>";

		} catch (Exception e) {
			message = "<script>";
			message += " alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요');";
			message += " location.href='" + request.getContextPath() + "/signup/signup';";
			message += " </script>";
			e.printStackTrace();
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}

	@Override
	@RequestMapping(value = "/login", method = { RequestMethod.POST, RequestMethod.GET })
	public String login(@RequestParam Map<String, String> loginMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		d001vo = d001Service.login(loginMap);
		if (d001vo != null && d001vo.getMember_id() != null) {
			HttpSession session = request.getSession();
			session = request.getSession();
			session.setMaxInactiveInterval(360*60);
			session.setAttribute("isLogOn", true);
			session.setAttribute("member_id",d001vo.getMember_id());
			session.setAttribute("memberInfo", d001vo);
			FlashMap flash = RequestContextUtils.getOutputFlashMap(request);
			flash.put("member_info", d001vo);
		} else {
			String message = "아이디나  비밀번호가 틀립니다. 다시 로그인해주세요";
			System.out.println("실패:" + message);
			FlashMap fm = RequestContextUtils.getOutputFlashMap(request);
			fm.put("message", message);
		}
		return "redirect:/main/main";

	}

	@RequestMapping(value = "/kakaoLogin")
	public String login(@RequestParam("code") String code, HttpSession session,HttpServletRequest request) throws Exception{
		String access_Token = getAccessToken(code,request);
		B_P001VO userInfo = getUserInfo(access_Token);
		System.out.println("user_idddddddddddddddd: " + userInfo.getMember_id());
		System.out.println("emailllll: " + userInfo.getEmail());
		System.out.println("login Controller : " + userInfo);
		// 클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
		if (userInfo.getEmail() != null) {
			session.setAttribute("isLogOn", true);
			session.setAttribute("memberInfo", userInfo);
			session.setAttribute("member_id", userInfo.getNick_name());
			session.setAttribute("profile_image", userInfo.getProfile_image());
			session.setAttribute("access_Token", access_Token);	
			System.out.println("ccccc: " + userInfo);
		}
		return "/z/p000/d001";
	}

	public void kakaoLogout(String access_Token) {
		String reqURL = "https://kapi.kakao.com/v1/user/logout";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String result = "";
			String line = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		String access_Token = (String) session.getAttribute("access_Token");
		if(access_Token != null) {
		kakaoLogout((String) session.getAttribute("access_Token"));
		session.removeAttribute("access_Token");
		}
		session.removeAttribute("userId");
		session.removeAttribute("isLogOn");
		session.removeAttribute("memberInfo");
		return "/z/p000/d001";
	}
	
	
	
	@RequestMapping(value = "/naverLogin", method = RequestMethod.GET)
	public ModelAndView naverLogin(HttpSession session,HttpServletRequest request) {
		/* 네아로 인증 URL을 생성하기 위하여 getAuthorizationUrl을 호출 */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session,request);
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("naver_url", naverAuthUrl);
		System.out.println("naverrrrrrrr:" +naverAuthUrl);
		return new ModelAndView("redirect:"+ naverAuthUrl);
	}

	// 네이버 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView callback(@RequestParam String code, @RequestParam String state, HttpSession session,HttpServletRequest request) throws Exception {
		OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state,request);
		String apiResult = naverLoginBO.getUserProfile(oauthToken,request);
		System.out.println("apiResult"+ apiResult);
		ModelAndView mav = new ModelAndView();
		NaverVO naverInfo = new NaverVO();
		mav.setViewName("/z/p000/d001");
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(apiResult);
		JsonObject naver_account = element.getAsJsonObject().get("response").getAsJsonObject();
		
		String member_id = naver_account.getAsJsonObject().get("id").getAsString();
		String nickname = naver_account.getAsJsonObject().get("nickname").getAsString();
		String gender = naver_account.getAsJsonObject().get("gender").getAsString();
		String email = naver_account.getAsJsonObject().get("email").getAsString();
		String name = naver_account.getAsJsonObject().get("name").getAsString();
		String birthday = naver_account.getAsJsonObject().get("birthday").getAsString();
		String member_type = "naver";
		
		naverInfo.setMember_id(member_id);
		naverInfo.setNick_name(nickname);
		naverInfo.setGender(gender);
		naverInfo.setEmail(email);
		naverInfo.setName(name);
		naverInfo.setBirth(birthday);
		naverInfo.setMember_type(member_type);
		
		if(d001Service.overlapped(member_id).equals("false")) {
			try {
				d001Service.naverInsert(naverInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		mav.addObject("isLogOn", true);
		mav.addObject("memberInfo");
		B_P001VO userInfo = new B_P001VO();
		userInfo.setMember_id(member_id);
		userInfo.setNick_name(nickname);
		userInfo.setGender(gender);
		userInfo.setEmail(email);
		userInfo.setName(name);
		userInfo.setBirth(birthday);
		userInfo.setMember_type(member_type);
		
		session.setMaxInactiveInterval(30*60);
		session.setAttribute("isLogON", true);
		session.setAttribute("member_id", userInfo.getMember_id());
		session.setAttribute("memberInfo", userInfo);
		System.out.println("naverInfo : " + naverInfo);
		System.out.println("member_id:" + naverInfo.getNick_name());
		return mav;
	}
}

package bts.b.p001.Controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import bts.b.p001.Service.B_P001Service;
import bts.b.p001.VO.B_P001VO;

@Controller("b_p001")
@RequestMapping(value = "/signup")
public class B_P001ControllerImpl implements B_P001Controller {
	
	
	@Autowired
	B_P001Service d001Service;
	@Autowired
	B_P001VO d001vo;
//
//	private final static String K_CLIENT_ID = "6a0602e55acf9e0f00406d7fb1f93b3d";
//	private final static String K_REDIRECT_URI = "http://localhost:8088/bts/signup/kakaoLogin";

//	public static String getAuthorizationUrl(HttpSession session) {
//		String kakaoUrl = "https://kauth.kakao.com/oauth/authorize?" + "client_id=" + K_CLIENT_ID + "&redirect_uri="
//				+ K_REDIRECT_URI + "&response_type=code";
//		return kakaoUrl;
//	}

	public static JsonNode getAccessToken(String autorize_code) {
		final String RequestUrl = "https://kauth.kakao.com/v2/oauth/token";
		final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
		postParams.add(new BasicNameValuePair("client_id", "6a0602e55acf9e0f00406d7fb1f93b3d"));// REST API KEY
		postParams.add(new BasicNameValuePair("redirect_uri", "http://localhost:8088/bts/signup/kakaoLogin"));// redirect uri
		postParams.add(new BasicNameValuePair("code", autorize_code));// 로그인 과정 중 얻은 code값
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);
		JsonNode returnNode = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(postParams));
			final HttpResponse response = client.execute(post);
			// json 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// clear resources
		}
		return returnNode;
	}
	
	public static JsonNode getKakaoUserInfo(JsonNode accessToken) {
		final String RequestUrl = "https://kapi.kakao.com/v1/user/me";
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);
		//add header
		post.addHeader("Autorization","Bearer" + accessToken);
		JsonNode returnNode = null;
		try {
			final HttpResponse response = client.execute(post);
			//JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			//Json형태 반환값 처리
			returnNode = mapper.readTree(response.getEntity().getContent());
		}catch(ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			//clear resources
		}
		return returnNode;
	}
	
	

	@Override
	@RequestMapping(value = "/signup", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView b_p001_d001(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView("/b/p001/d001");
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
	@RequestMapping(value = "/login", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView login(@RequestParam Map<String, String> loginMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		

		d001vo = d001Service.login(loginMap);
		if (d001vo != null && d001vo.getMember_id() != null) {
			HttpSession session = request.getSession();
			session = request.getSession();
			
			session.setAttribute("isLogOn", true);
			session.setAttribute("memberInfo", d001vo);
			System.out.println("성공");
			mav.setViewName("/z/p000/d001");
			
		} else {
			String message = "아이디나  비밀번호가 틀립니다. 다시 로그인해주세요";
			mav.addObject("message", message);
			System.out.println("실패:" + message);
			mav.setViewName("/z/p000/d001");
		}
		return mav;
	}
	
//	@RequestMapping(value = "/loginform",method= RequestMethod.GET)
//	public ModelAndView LoginForm(HttpSession session) {
//		ModelAndView mav = new ModelAndView();
//		String kakaoUrl = getAuthorizationUrl(session);	
//		mav.setViewName("/z/p000/d001");
//		mav.addObject("kakao_url", kakaoUrl);
//		return mav;
//	}
	
	
	
	@RequestMapping(value = "/kakaoLogin", produces = "application/json", method = {
			RequestMethod.GET, RequestMethod.POST})
	public ModelAndView kakaoLogin(@RequestParam("code") String code) {
		ModelAndView mav = new ModelAndView();
		System.out.println("kakaologinCode:"+code);
		JsonNode node = getAccessToken(code);
		System.out.println("node: "+node);
		//노드 안에 access_token 값을 문자열로 변환
		//String token = node.get("access_token").toString();
		mav.setViewName("/z/p000/d001");
		return mav;
	}
}

package bts.common.exception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler {

	@ExceptionHandler(Exception.class)
	public String handleErrorCommon(HttpServletResponse response,Exception e) {
		e.printStackTrace();
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return "/common/exception/errorCommon";
	}
}

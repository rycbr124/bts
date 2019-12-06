package bts.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler {

	@ExceptionHandler(Exception.class)
	public String handleErrorCommon(Exception e) {
		e.printStackTrace();
		return "redirect:/error";
	}
}

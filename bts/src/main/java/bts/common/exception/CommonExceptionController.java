package bts.common.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("commonException")
public class CommonExceptionController {
	
	@RequestMapping(value="/error/404")
	public String notFound() {
		return "/common/exception/error404";
	}
}

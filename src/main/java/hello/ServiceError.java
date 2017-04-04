package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.web.ErrorController;

@RestController
public class ServiceError implements ErrorController{
	
	public static String PATH = "/error";
	
	@RequestMapping("/error")
	public @ResponseBody String GenericError()
	{
		return "404 ERROR : There is nothing here. :^(";
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}

}

package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

	@RequestMapping("/notpage")
	public String NotPage(){
		return "Error/NotPage";
	}
}

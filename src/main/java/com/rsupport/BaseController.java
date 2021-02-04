package com.rsupport;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {
	
	/**
	 * 로그인 화면 redirect	 
	 * @return
	 */
	@RequestMapping(value= {"","/"}, method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("login/index");
		return mav;
	}
}

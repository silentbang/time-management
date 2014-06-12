package com.duke.timemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/welcome")
public class HelloController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView printWelcome() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("template");
		mav.addObject("message", "This is message 2");

		return mav;
	}
}

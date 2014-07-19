package com.duke.passato.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.duke.passato.common.Constant;
import com.duke.passato.common.Message;
import com.duke.passato.common.MessageType;

@Controller
public class GenericController {

	protected void postSingleMessage(RedirectAttributes redirectAttributes, Message message) {
		List<Message> messages = new ArrayList<Message>();
		messages.add(message);
		redirectAttributes.addFlashAttribute(Constant.MESSAGE_LIST, messages);
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest request, Exception exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.addObject("url", request.getRequestURL());
		mav.setViewName("error");

		return mav;
	}
}

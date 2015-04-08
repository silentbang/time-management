package com.duke.passato.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.duke.passato.common.Constant;
import com.duke.passato.model.Project;
import com.duke.passato.service.ProjectService;

public class MenuInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private ProjectService projectService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		List<Project> recentProjects = this.projectService.listRecentProjects(Constant.RECENT_PROJECT_COUNT);
		Double averageProgress = this.projectService.calculateAverageProgress();

		request.setAttribute(Constant.Tag.MENU_RECENTPROJECTS, recentProjects);
		request.setAttribute(Constant.Tag.MENU_AVERAGEPROGRESS, averageProgress);
	}

}

package com.duke.passato.interceptor;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import com.duke.passato.common.Constant;
import com.duke.passato.model.Project;
import com.duke.passato.service.ProjectService;

public class MenuInterceptorTest {

	@InjectMocks
	private MenuInterceptor menuInterceptor;
	// MockHttpServletRequest/Response is supported by Spring
	@Spy
	private MockHttpServletRequest request;
	@Spy
	private MockHttpServletResponse response;
	@Mock
	private Object handler;
	@Mock
	private ModelAndView modelAndView;
	@Mock
	private ProjectService projectService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testPostHandle() throws Exception {
		List<Project> recentProjects = new ArrayList<Project>();
		recentProjects.add(new Project());
		double averageProgress = 20.1;

		Mockito.when(this.projectService.calculateAverageProgress()).thenReturn(averageProgress);
		Mockito.when(this.projectService.listRecentProjects(Constant.RECENT_PROJECT_COUNT)).thenReturn(recentProjects);

		this.menuInterceptor.postHandle(this.request, this.response, this.handler, this.modelAndView);

		assertEquals(recentProjects, this.request.getAttribute(Constant.Tag.MENU_RECENTPROJECTS));
		assertEquals(averageProgress, this.request.getAttribute(Constant.Tag.MENU_AVERAGEPROGRESS));
	}

}

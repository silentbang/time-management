<%@page import="com.duke.passato.dao.ProjectDAO"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@page import="com.duke.passato.dao.ProjectDAOImpl" %>
<%@page import="com.duke.passato.model.Project"%>
<%@page import="java.util.List"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>


<!-- BEGIN MINI-PROFILE -->
<div class="user-info-wrapper">
	<div class="profile-wrapper">
		<img src="<c:url value="/assets/img/profiles/avatar.jpg" />" alt="" data-src="<c:url value="/assets/img/profiles/avatar.jpg" />" width="69" height="69"/>
	</div>
	<div class="user-info">
		<div class="greeting"><spring:message code="menu.welcome"/></div>
		<div class="username">
			<span class="semi-bold"><spring:message code="global.developer.firstName"/></span> <spring:message code="global.developer.lastName"/>
		</div>
		<div class="status"><a href="#"><div class="status-icon green"></div>Online</a>
		</div>
	</div>
</div>
<!-- END MINI-PROFILE -->
<!-- BEGIN SIDEBAR MENU -->
<ul>
	<li class="start active "><a href="${pageContext.request.contextPath}/"><i class="icon-custom-home"></i><span class="title">Dashboard</span><span class="selected"></span></a></li>
	<li class="hidden-lg hidden-md hidden-xs" id="more-widgets"><a href="javascript:;"><i class="fa fa-plus"></i></a>
	<ul class="sub-menu">
		<li class="side-bar-widgets">
			<p class="menu-title">
				FOLDER <span class="pull-right"><a href="#" class="create-folder"><i class="icon-plus"></i></a></span>
			</p>
			<ul class="folders">
				<li><a href="#">
				<div class="status-icon green">
				</div>
				 My quick tasks </a></li>
				<li><a href="#">
				<div class="status-icon red">
				</div>
				 To do list </a></li>
				<li><a href="#">
				<div class="status-icon blue">
				</div>
				 Projects </a></li>
				<li class="folder-input" style="display:none"><input type="text" placeholder="Name of folder" class="no-boarder folder-name" name="" id="folder-name"></li>
			</ul>
		</li>
	</ul>
	</li>
</ul>
<div class="side-bar-widgets">
	<p class="menu-title">RECENT PROJECTS</p>
	<ul class="folders">
		<c:forEach items="${requestScope.recentProjects}" var="recentProject">
			<li>
				<a href="${pageContext.request.contextPath}/tasks/${recentProject.projectId}">
					<div class="status-icon blue">
					</div>
					${recentProject.name}
				</a>
			</li>		
		</c:forEach>
		
		<li class="folder-input" style="display:none"><input type="text" placeholder="Name of folder" class="no-boarder folder-name" name=""></li>
	</ul>
</div>
<a href="#" class="scrollup">Scroll</a>
<div class="clearfix">
</div>
<!-- END SIDEBAR MENU -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- BEGIN TOP NAVIGATION BAR -->
<div class="navbar-inner">
	<div class="header-seperation">
		<ul class="nav pull-left notifcation-center" id="main-menu-toggle-wrapper" style="display:none">
			<li class="dropdown"><a id="main-menu-toggle" href="#main-menu" class="">
			<div class="iconset top-menu-toggle-white">
			</div>
			</a></li>
		</ul>
		<!-- BEGIN LOGO -->
		<a href="${pageContext.request.contextPath}"><img src="<c:url value="/assets/img/logo.png" />" class="logo" alt="" data-src="<c:url value="/assets/img/logo.png" />" data-src-retina="<c:url value="/assets/img/logo2x.png" />" width="106" height="21"/></a>
		<!-- END LOGO -->
		<ul class="nav pull-right notifcation-center">
			<li class="dropdown" id="header_task_bar"><a href="${pageContext.request.contextPath}" class="dropdown-toggle active" data-toggle="">
			<div class="iconset top-home">
			</div>
			</a></li>
			<li class="dropdown" id="header_inbox_bar"><a href="email.html" class="dropdown-toggle">
			<div class="iconset top-messages">
			</div>
			<span class="badge" id="msgs-badge">2</span></a></li>
			<li class="dropdown" id="portrait-chat-toggler" style="display:none"><a href="#sidr" class="chat-menu-toggle">
			<div class="iconset top-chat-white ">
			</div>
			</a></li>
		</ul>
	</div>
	<!-- END RESPONSIVE MENU TOGGLER -->
	<div class="header-quick-nav">
		<!-- BEGIN TOP NAVIGATION MENU -->
		<div class="pull-left">
			<ul class="nav quick-section">
				<li class="quicklinks"><a href="#" class="" id="layout-condensed-toggle">
				<div class="iconset top-menu-toggle-dark">
				</div>
				</a></li>
			</ul>
		</div>
		<!-- END TOP NAVIGATION MENU -->
		<!-- BEGIN CHAT TOGGLER -->
		<div class="pull-right">
			<div class="chat-toggler">
				<div class="profile-pic">
					<img src="<c:url value="/assets/img/profiles/avatar_small.jpg" />" alt="" data-src="<c:url value="/assets/img/profiles/avatar_small.jpg" />" width="35" height="35"/>
				</div>
				<div class="user-details">
					<div class="username">
						&nbsp;&nbsp;<span class="bold"><spring:message code="global.developer.firstName"/></span> <spring:message code="global.developer.lastName"/>
					</div>
				</div>
			</div>
			<ul class="nav quick-section ">
				<li class="quicklinks">
					<a href="<c:url value="/j_spring_security_logout"/>"><i class="fa fa-power-off"></i></a>
				</li>
			</ul>
		</div>
		<!-- END CHAT TOGGLER -->
	</div>
	<!-- END TOP NAVIGATION MENU -->
</div>
<!-- END TOP NAVIGATION BAR -->

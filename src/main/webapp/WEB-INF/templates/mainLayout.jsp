<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html>
	<head>
		<title>
			<c:set var="title"><tiles:getAsString name="title" ignore="true" /></c:set>
			<spring:message code="${title}"/>
		</title>
		<tiles:insertAttribute name="headerResources"/>
	</head>
	<body class="">
		<div class="header navbar navbar-inverse ">
			<tiles:insertAttribute name="header"/>
		</div>
		<div class="page-container row">
			<!-- Sidebar menu -->
			<div class="page-sidebar" id="main-menu">
				<tiles:insertAttribute name="menu"/>
			</div>
			<div class="footer-widget">
				<tiles:insertAttribute name="footer"/>
			</div>
			<!-- Content-->
			<div class="page-content">
				<div class="content">
					<div class="page-title">
						<a onclick="history.back()"><i class="icon-custom-left"></i></a>
						<h3>
							<c:set var="headTitle"><tiles:getAsString name="headTitle" ignore="true"/></c:set>
							<spring:message code="${headTitle}"/>
						</h3>
					</div>
					<div id="container">
						<tiles:insertAttribute name="body"/>
					</div>
				</div>
			</div>
		</div>
		
		<tiles:insertAttribute name="footerResources"/>
		
		<tiles:importAttribute name="jsScripts" />
		<tiles:importAttribute name="styleSheets" />
		<c:forEach var="cssFileName" items="${styleSheets}">  
	    	<link type="text/css" href="<c:url value="${cssFileName}" />" rel="stylesheet" type="text/css"/>  
		</c:forEach>
		<c:forEach var="jsFileName" items="${jsScripts}">  
	    	<script src="<c:url value="${jsFileName}" />"></script>   
		</c:forEach>
	</body>
</html>
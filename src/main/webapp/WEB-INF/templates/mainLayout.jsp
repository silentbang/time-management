<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
	<head>
		<title>
			<tiles:insertAttribute name="title" ignore="true"/>
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
						<h3><tiles:insertAttribute name="headTitle" ignore="true"/></h3>
					</div>
					<div id="container">
						<tiles:insertAttribute name="body"/>
					</div>
				</div>
			</div>
		</div>
		
		<tiles:insertAttribute name="footerResources"/>
	</body>
</html>
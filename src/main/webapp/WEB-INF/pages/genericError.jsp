<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
		<meta charset="utf-8" />
		<title>Webarch - Responsive Admin Dashboard</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<meta content="" name="description" />
		<meta content="" name="author" />
		<!-- BEGIN CORE CSS FRAMEWORK -->
		<link href="<c:url value="/assets/plugins/pace/pace-theme-flash.css" />" rel="stylesheet" type="text/css" media="screen"/>
		<link href="<c:url value="/assets/plugins/boostrapv3/css/bootstrap.min.css" />" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/assets/plugins/boostrapv3/css/bootstrap-theme.min.css" />" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/assets/plugins/font-awesome/css/font-awesome.css" />" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/assets/css/animate.min.css" />" rel="stylesheet" type="text/css"/>
		<!-- END CORE CSS FRAMEWORK -->
		<!-- BEGIN CSS TEMPLATE -->
		<link href="<c:url value="/assets/css/style.css" />" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/assets/css/responsive.css" />" rel="stylesheet" type="text/css"/>
		<link href="<c:url value="/assets/css/custom-icon-set.css" />" rel="stylesheet" type="text/css"/>
		<!-- END CSS TEMPLATE -->
		
		<style>
			.error-main {
				position: unset;
				margin-top: 3%;
			}
		</style>
	</head>
	<body class="error-body no-top">
		<div class="error-wrapper container">
			<div class="row">
				<div class="error-container" >
					<div class="error-main">
					  	<div class="error-number"> 500 </div>
					  	<div class="error-description-mini">${url}</div>
					  	<div class="error-description-mini">${exception.message}</div>
					  	<br>
					</div>
				  	<blockquote class="margin-top-20">
				        <c:forEach items="${exception.stackTrace}" var="stackTrace">    
				        	${stackTrace} 
				    	</c:forEach>
			    	</blockquote>
			    	<button class="btn btn-primary btn-cons" onclick="javascript:history.back()" type="button">Back</button>
				</div>
			</div>
		</div>
	</body>
</html>
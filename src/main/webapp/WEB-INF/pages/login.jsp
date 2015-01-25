<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
	<head>
		<title>
			<c:set var="title"><tiles:getAsString name="title" ignore="true" /></c:set>
			<spring:message code="${title}"/>
		</title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
		<meta content="" name="description"/>
		<meta content="" name="author"/>
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
		<link href="<c:url value="/assets/css/app.css" />" rel="stylesheet" type="text/css"/>
	</head>
	<!-- END HEAD -->
	<!-- BEGIN BODY -->
	<body class="error-body no-top">
		<div class="container">
			<div class="lockscreen-wrapper animated flipInX">
				<div class="row ">
					<div class="col-md-8 col-md-offset-4 col-sm-6 col-sm-offset-4 col-xs-offset-2">
						<div class="profile-wrapper">
							<img width="69" height="69" 
							data-src-retina="<c:url value="/assets/img/profiles/avatar2x.jpg" />" 
							data-src="<c:url value="/assets/img/profiles/avatar.jpg" />" 
							src="<c:url value="/assets/img/profiles/avatar.jpg" />" alt="">
						</div>
						<form class="user-form" action="<c:url value='j_spring_security_check' />" method="post">
							<h2 class="user">
								<span class="semi-bold"><spring:message code="global.developer.firstName"/></span>
								<spring:message code="global.developer.lastName"/>
							</h2>
							<input type="hidden" name="username" value="admin"/> 
							<input type="password" name="password" placeholder="Password">
							<button type="submit" class="btn btn-primary "><i class="fa fa-unlock"></i></button>
						</form>
					</div>
					<c:if test="${not empty invalid}">
						<div class="col-md-4 col-md-offset-5 col-sm-6 col-sm-offset-4 col-xs-offset-2">
							<div class="text-error"><spring:message code="page.login.error.password"/></div>
						</div>
					</c:if>
					<c:if test="${not empty logout}">
						<div class="col-md-4 col-md-offset-5 col-sm-6 col-sm-offset-4 col-xs-offset-2">
							<div class="text-success"><spring:message code="page.login.success.logout"/></div>
						</div>
					</c:if>
				</div>
			</div>
			<div id="push">
			</div>
		</div>
		<!-- END CONTAINER -->
		<!-- BEGIN CORE JS FRAMEWORK-->
		<script src="<c:url value="/assets/plugins/jquery-1.8.3.min.js" />" type="text/javascript"></script>
		<script src="<c:url value="/assets/plugins/bootstrap/js/bootstrap.min.js" />" type="text/javascript"></script>
		<script src="<c:url value="/assets/plugins/jquery-unveil/jquery.unveil.min.js" />" type="text/javascript"></script>
		<script src="<c:url value="/assets/plugins/pace/pace.min.js" />" type="text/javascript"></script>
		<script type="text/javascript">
		$(document).ready(function() {	
			$("img").unveil();
		});	
		</script>
		<!-- BEGIN CORE TEMPLATE JS -->
		<!-- END CORE TEMPLATE JS -->
	</body>
</html>
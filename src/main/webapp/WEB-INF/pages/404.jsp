<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
	<head>
        <meta charset="utf-8">
        <title>Creatica - error</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        
        <link href="<c:url value="/assets/css/appError404.css" />" rel="stylesheet">
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        
		<script src="<c:url value="/assets/plugins/jquery-1.8.3.min.js" /> " type="text/javascript"></script>
        
        <link rel="shortcut icon" href="<c:url value="/assets/img/favicon.ico" />">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<c:url value="/assets/img/apple-touch-icon-144-precomposed.png" />">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<c:url value="/assets/img/apple-touch-icon-114-precomposed.png" />">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<c:url value="/assets/img/apple-touch-icon-72-precomposed.png" />">
        <link rel="apple-touch-icon-precomposed" href="<c:url value="/assets/img/apple-touch-icon-57-precomposed.png" />">
    </head>
    <body class="background-navy">
        <header>
        	<div class="logo"><a href="#"><img src="<c:url value="/assets/img/404/logo.png" />" class="logo" alt="" /></a></div>
        	<div class="ribbon"><img src="<c:url value="/assets/img/404/ribbon.png" />" alt="" /></div>
        </header>
        
        <section data-error="404" class="error">
        	<div class="number">
                <div id="n1"></div>
                <div id="n2"></div>
                <div id="n3"></div>
            </div>
        </section>
        
        <footer>
        	<div class="container">
        	<button class="button" onclick="javascript:history.back()" type="button">Back</button>
        	</div>
        </footer>
        <script src="<c:url value="/assets/js/appError404.js" />" type="text/javascript"></script>
    </body>
</html>
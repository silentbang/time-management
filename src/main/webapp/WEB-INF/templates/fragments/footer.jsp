<%@page import="com.duke.passato.common.Constant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="progress transparent progress-small no-radius no-margin">
	<div class="progress-bar progress-bar-success animate-progress-bar" 
		data-percentage="<%= request.getAttribute(Constant.Tag.MENU_AVERAGEPROGRESS) %>%" 
		style="width: <%= request.getAttribute(Constant.Tag.MENU_AVERAGEPROGRESS) %>%;">
	</div>
</div>
<div class="pull-right">
	<div class="details-status">
		<span class="animate-number" 
			data-value="<fmt:formatNumber maxFractionDigits="2" value="${requestScope.averageProgress}" />" 
			data-animation-duration="560">
			<fmt:formatNumber maxFractionDigits="2" value="${requestScope.averageProgress}" />% 
		</span>%
	</div>
	<a href="<c:url value="/j_spring_security_logout"/>"><i class="fa fa-power-off"></i></a>
</div>
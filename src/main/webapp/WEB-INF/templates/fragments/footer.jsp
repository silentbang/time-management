<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="progress transparent progress-small no-radius no-margin">
	<div class="progress-bar progress-bar-success animate-progress-bar" 
		data-percentage="<%= request.getAttribute("averageProgress") %>%" 
		style="width: <%= request.getAttribute("averageProgress") %>%;">
	</div>
</div>
<div class="pull-right">
	<div class="details-status">
		<span class="animate-number" data-value="<%= request.getAttribute("averageProgress") %>" 
			data-animation-duration="560">
			<%= request.getAttribute("averageProgress") %>
		</span>%
	</div>
	<a href="<c:url value="/j_spring_security_logout"/>"><i class="fa fa-power-off"></i></a>
</div>
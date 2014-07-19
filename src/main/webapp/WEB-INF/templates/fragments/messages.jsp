<%@page import="com.duke.passato.common.MessageType"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:set var="messageTypeNameSuccess" value="<%=MessageType.SUCCESS.getTypeName()%>" />
<c:set var="messageTypeNameInfo" value="<%=MessageType.INFO.getTypeName()%>" />
<c:set var="messageTypeNameWarning" value="<%=MessageType.WARNING.getTypeName()%>" />
<c:set var="messageTypeNameError" value="<%=MessageType.ERROR.getTypeName()%>" />

<c:forEach items="${messages}" var="message">
	<c:choose>
		<c:when test="${message.type.typeName eq messageTypeNameSuccess}">
			<c:set var="messageCssClass">alert-success</c:set>
		</c:when>
		<c:when test="${message.type.typeName eq messageTypeNameInfo}">
			<c:set var="messageCssClass">alert-info</c:set>
		</c:when>
		<c:when test="${message.type.typeName eq messageTypeNameWarning}">
			<c:set var="messageCssClass"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="messageCssClass">alert-error</c:set>
		</c:otherwise>
	</c:choose>

	<div class="alert ${messageCssClass}">
		<button class="close" data-dismiss="alert"></button>
		<spring:message code="${message.key}" arguments="${message.param}"/>
	</div>
</c:forEach>
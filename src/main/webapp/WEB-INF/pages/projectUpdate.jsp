<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<div class="row">
	<div class="col-md-6">
		<div class="grid simple">
			<div class="grid-title no-border">
				<h4></h4>
				<div class="tools">
					<a href="javascript:;" class="collapse"></a><a href="#grid-config" data-toggle="modal" class="config"></a><a href="javascript:;" class="reload"></a><a href="javascript:;" class="remove"></a>
				</div>
			</div>
			<div class="grid-body no-border">
				<form:form method="POST" commandName="project" action="${pageContext.request.contextPath}/projects/update/${project.projectId}">
					<spring:bind path = "*">
					    <c:if test="${status.error}"> 
					    	<div class="alert alert-error">
								<button class="close" data-dismiss="alert"></button>
								<form:errors path="*" element="div" />
							</div>
					    </c:if> 
					</spring:bind>
				
					<form:hidden path="projectId" value="${project.projectId}"/>
					<div class="form-group">
						<form:label path="name" cssClass="form-label"><spring:message code="label.project.name"/></form:label>
						<span class="requiredSymbol">*</span>
						<span class="help"><spring:message code="hint.task.name"/></span>
						<div class="input-with-icon right">
							<i class=""></i>
							<form:input path="name" value="${project.name}" cssClass="form-control"/>
						</div>
					</div>
					<div class="form-actions">
						<div class="pull-right">
							<button type="submit" class="btn btn-small btn-success btn-cons">
								<i class="icon-ok">
								</i> <spring:message code="crud.command.save"/>
							</button>
							<a href="javascript:deleteEntity('${pageContext.request.contextPath}/projects/delete/${project.projectId}');" class="btn btn-small btn-danger btn-cons">
								<i class="fa fa-file-text-o"></i> <spring:message code="crud.delete"/>
							</a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
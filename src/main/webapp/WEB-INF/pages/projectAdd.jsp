<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<div class="row">
	<div class="col-md-6">
		<div class="grid simple">
			<div class="grid-title no-border">
				<h4>Traditional <span class="semi-bold">Validation</span></h4>
				<div class="tools">
					<a href="javascript:;" class="collapse"></a><a href="#grid-config" data-toggle="modal" class="config"></a><a href="javascript:;" class="reload"></a><a href="javascript:;" class="remove"></a>
				</div>
			</div>
			<div class="grid-body no-border">
				<form:form method="POST" commandName="project" action="/TimeManagement/projects/save">
					<form:hidden path="projectId" value="${project.projectId}"/>
					<div class="form-group">
						<form:label path="name" cssClass="form-label">Project name</form:label>
						<span class="help">e.g. "2014/08"</span>
						<div class="input-with-icon right">
							<i class=""></i>
							<form:input path="name" value="${project.name}" cssClass="form-control"/>
						</div>
					</div>
					<div class="form-actions">
						<div class="pull-right">
							<button type="submit" class="btn btn-success btn-cons"><i class="icon-ok"></i> Save</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
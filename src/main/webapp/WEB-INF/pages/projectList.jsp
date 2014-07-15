<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row-fluid">
	<div class="span12">
		<div class="grid simple ">
			<div class="grid-title">
				<a class="btn btn-small btn-info btn-cons" href="/Passato/projects/add">
					<i class="fa fa-paste"></i> <spring:message code="crud.add" />
				</a>
				<div class="tools">
					<a href="javascript:;" class="collapse"></a><a href="#grid-config" data-toggle="modal" class="config"></a><a href="javascript:;" class="reload"></a><a href="javascript:;" class="remove"></a>
				</div>
			</div>
			<div class="grid-body ">
				<c:if test="${!empty projects}">
					<table class="table table-hover table-condensed" id="example">
						<thead>
							<tr>
								<th style="width: 1%">
									<div class="checkbox check-default" style="margin-right: auto; margin-left: auto;">
										<input type="checkbox" value="1" id="checkbox1"><label for="checkbox1"></label>
									</div>
								</th>
								<th style="width: 10%">
									<spring:message code="label.project.name"/>
								</th>
								<th style="width: 5%" data-hide="phone,tablet">
									<spring:message code="label.project.createdDate"/>
								</th>
								<th style="width: 5%" data-hide="phone,tablet">
									<spring:message code="label.project.estimatedOverActual"/>
								</th>
								<th style="width: 10%" data-hide="phone,tablet">
									<spring:message code="label.project.completedPercentage"/>
								</th>
								<th style="width: 18%" data-hide="phone,tablet"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${projects}" var="project">
								<tr>
									<td>
										<div class="checkbox check-default">
											<input type="checkbox" value="3" id="checkbox3"><label for="checkbox3"></label>
										</div>
									</td>
									<td>${project.name}</td>
									<td>${project.createdDate}</td>
									<td>${project.totalEstimatedDuration}h / ${project.totalActualDuration}h</td>
									<td>
										<div class="progress">
											<div data-percentage="${project.averageProgress}%" class="progress-bar progress-bar-primary animate-progress-bar">
											</div>
										</div>
									</td>
									<td>
										<a href="/Passato/projects/update/${project.projectId}" class="btn btn-small btn-primary btn-xs btn-mini" ><i class="fa fa-paste"></i> Update</a>
										<a href="javascript:deleteEntity('/Passato/projects/delete/${project.projectId}');" class="btn btn-small btn-danger btn-xs btn-mini">
											<i class="fa fa-file-text-o"></i> Delete
										</a>
										<a href="/Passato/tasks/${project.projectId}" class="btn btn-small btn-warning btn-xs btn-mini" ><i class="fa fa-paste"></i> Manage tasks</a>
										<a href="/Passato/tasks/plan/${project.projectId}" class="btn btn-small btn-warning btn-xs btn-mini" ><i class="fa fa-paste"></i> Planning</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
	</div>
</div>
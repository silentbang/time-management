<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.duke.timemanagement.common.TaskType"%>
<spring:message code="label.task.name" var="labelTaskName"/>
<spring:message code="label.task.estimatedDuration" var="labelTaskEstimatedDuration"/>
<spring:message code="label.task.actualDuration" var="labelTaskActualDuration"/>
<spring:message code="label.task.deadline" var="labelTaskDeadline"/>
<spring:message code="label.task.note" var="labelTaskNote"/>
<spring:message code="label.task.completedPercentage" var="labelTaskCompletedPercentage"/>
<spring:message code="label.task.isFinished" var="labelTaskIsFinished"/>

<div class="row">
	<div class="col-md-8 spacing-bottom">
		<c:forEach items="${taskTypes}" var="taskType" varStatus="counter">
			<c:if test="${counter.count % 2 != 0 }">
				<div class="row">
			</c:if>
			
			<div class="col-md-6 col-sm-6 spacing-bottom">
				<div class="tiles ${taskType.color} weather-widget ">
					<div class="tiles-body">
						<div class="controller">
							<a href="javascript:;" class="reload"></a>
							<a href="javascript:;" class="remove"></a>
						</div>
						<div class="tiles-title">
							 ${taskType.typeName}
						</div>
						<br />
<!-- 						<div class="heading"> -->
<!-- 							<div class="pull-left"> -->
<!-- 								 Tuesday -->
<!-- 							</div> -->
<!-- 							<div class="pull-right"> -->
<!-- 								 55 -->
<!-- 							</div> -->
<!-- 							<div class="clearfix"> -->
<!-- 							</div> -->
<!-- 						</div> -->
						<div class="widget-body">
							<div class="pull-left ">
								<button id="btnAddTask_${taskType.value}" class="btn btn-dark btn-small" type="button"><i class="fa fa-plus"></i></button>
							</div>
							<c:forEach items="${tasks}" var="task">
								<c:if test="${task.taskTypeId == taskType.value }">
									<div id="taskInfo_${task.taskId}" class="notification-messages info task-info">
										<div class="message-wrapper">
											<c:set var="taskHeaderStyle"></c:set>
											<c:if test="${task.isFinished == true}">
												<c:set var="taskHeaderStyle">taskDone</c:set>
											</c:if>
											<div class="heading ${taskHeaderStyle}">${task.name}</div>
											<div class="description">${task.deadline}</div>
										</div>
										<div class="date pull-right">${task.estimatedDuration}h</div>
										<span class="badge" onClick="deleteEntity('/TimeManagement/tasks/delete/${task.taskId}');">x</span>
									</div>
								</c:if>
							</c:forEach>
						</div>
						<div class="clearfix">
						</div>
					</div>
					<div class="tile-footer">
						<div class="pull-left">
							<canvas id="wind" width="32" height="32"></canvas>
							<span class="text-white small-text-description">Total duration:</span>
						</div>
						<div class="pull-right">
							<canvas id="rain" width="32" height="32"></canvas>
							<span class="text-white small-text-description">30h</span>
						</div>
						<div class="clearfix">
						</div>
					</div>
				</div>
			</div>
			
			<c:if test="${counter.count % 2 == 0 }">
				</div>
			</c:if>
		</c:forEach>
		
	</div>
	
	<div class="col-md-4">
		<div class="row">
			<div class="col-md-12 col-sm-6 spacing-bottom">
				<div id="formDialog" class="widget">
					<div class="widget-title dark">
						 Task: Create full product demo
						<div class="controller">
							<a href="javascript:;" class="reload"></a>
							<a href="javascript:;" class="remove"></a>
						</div>
					</div>
					<div class="widget-body">
						<form:form method="POST" commandName="task" action="/TimeManagement/tasks/save/${project.projectId}">
							<form:hidden path="projectId" value="${task.projectId}"/>
							<form:hidden path="taskId" value="${task.taskId}"/>
							<form:hidden path="taskTypeId" value="${task.taskTypeId}"/>
							<div class="col-md-12">
								<form:input path="name" value="${task.name}" cssClass="form-control" placeholder="${labelTaskName}"/>
							</div>
							<br>
							<div class="col-md-12">
								<form:input path="estimatedDuration" value="${task.estimatedDuration}" cssClass="form-control" placeholder="${labelTaskEstimatedDuration}" />
							</div>
							<br>
							<div class="col-md-12">
								<form:input path="actualDuration" value="${task.actualDuration}" cssClass="form-control" placeholder="${labelTaskActualDuration}" />
							</div>
							<br>
							<div class="col-md-12">
								<form:input path="deadline" value="${task.deadline}" cssClass="form-control" placeholder="${labelTaskDeadline}" />
							</div>
							<br>
							<div class="col-md-12">
								<form:input path="note" value="${task.note}" cssClass="form-control" placeholder="${labelTaskNote}" />
							</div>
							<br>
							<div class="col-md-12">
								<form:input path="completedPercentage" value="${task.completedPercentage}" cssClass="form-control" placeholder="${labelTaskCompletedPercentage}" />
							</div>
							<br>
							<div class="form-actions">
								<div class="pull-right">
									<button type="submit" class="btn btn-success btn-cons">
										<i class="icon-ok">
										</i> <spring:message code="crud.command.save"/>
									</button>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
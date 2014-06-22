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
						 <spring:message code="page.taskMatrix.form.title" />
						<div class="controller">
							<a href="javascript:;" class="reload"></a>
							<a href="javascript:;" class="remove"></a>
						</div>
					</div>
					<div class="widget-body">
						<form:form method="POST" commandName="task" action="/TimeManagement/tasks/save">
							<form:hidden path="projectId" value="${task.projectId}"/>
							<form:hidden path="taskId" value="${task.taskId}"/>
							<form:hidden path="taskTypeId" value="${task.taskTypeId}"/>
							<div class="col-md-12">
								<form:input path="name" value="${task.name}" cssClass="form-control" placeholder="${labelTaskName}"/>
							</div>
							<br>
							<div class="slider primary col-md-12">
								<form:label path="estimatedDuration" cssClass="form-label"><spring:message code="label.task.estimatedDuration"/></form:label>
								<form:input path="estimatedDuration" value="${task.estimatedDuration}" cssClass="slider-element form-control" 
									data-slider-min="0" data-slider-max="8" data-slider-step="0.25" data-slider-value="${task.estimatedDuration}" data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="show"/>
							</div>
							<br>
							<div class="slider primary col-md-12">
								<form:label path="actualDuration" cssClass="form-label"><spring:message code="label.task.actualDuration"/></form:label>
								<form:input path="actualDuration" value="${task.actualDuration}" cssClass="slider-element form-control" 
									data-slider-min="0" data-slider-max="8" data-slider-step="0.25" data-slider-value="${task.actualDuration}" data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="show"/>
							</div>
							<br>
							<div class="col-md-12">
								<form:label path="deadlineDate" cssClass="form-label"><spring:message code="label.task.deadline"/></form:label>
							</div>
							<div class="col-md-12">
								<div class="input-append success date">
									<form:input path="deadlineDate" id="sandbox-advance" value="${task.deadlineDate}" cssClass="form-control" />
			                      	<span class="add-on"><span class="arrow"></span><i class="fa fa-th"></i></span>
								</div>
							</div>
							<div class="col-md-12">
								<div class="input-append bootstrap-timepicker-component">
									<form:input path="deadlineTime" value="${task.deadlineTime}" cssClass="timepicker-24 span12" />
									<span class="add-on"><span class="arrow"></span><i class="fa fa-clock-o"></i></span>
								</div>
							</div>
							<br>
							<div class="col-md-12">
								<form:input path="note" value="${task.note}" cssClass="form-control" placeholder="${labelTaskNote}" />
							</div>
							<br>
							<div class="col-md-12">
								<form:label path="completedPercentage" cssClass="form-label"><spring:message code="label.task.completedPercentage"/></form:label>
								<div class="slider primary col-md-8">
									<form:input path="completedPercentage" value="${task.completedPercentage}" cssClass="slider-element form-control" placeholder="${labelTaskCompletedPercentage}" 
										data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="${task.completedPercentage}" data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="show"/>
								</div>
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
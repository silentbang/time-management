<%@page import="com.duke.passato.common.Constant"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.duke.passato.common.TaskType"%>
<spring:message code="label.task.name" var="labelTaskName"/>
<spring:message code="label.task.estimatedDuration" var="labelTaskEstimatedDuration"/>
<spring:message code="label.task.actualDuration" var="labelTaskActualDuration"/>
<spring:message code="label.task.deadline" var="labelTaskDeadline"/>
<spring:message code="label.task.note" var="labelTaskNote"/>
<spring:message code="label.task.completedPercentage" var="labelTaskCompletedPercentage"/>
<spring:message code="label.task.isFinished" var="labelTaskIsFinished"/>

<div class="row spacing-bottom">
	<div class="col-md-12">
		<div class="pull-left">
			<span class="label label-far-future"><spring:message code="page.taskMatrix.label.farFuture"/></span>
			<span class="label label-3-days"><spring:message code="page.taskMatrix.label.next3Days"/></span>
			<span class="label label-today"><spring:message code="page.taskMatrix.label.today"/></span>
			<span class="label label-past"><spring:message code="page.taskMatrix.label.past"/></span>
		</div>
		<div class="pull-right">
		 	<div class="btn-group" data-toggle="buttons-radio">
		    	<button id="hideExpired" class="btn btn-primary active tip" title='<spring:message code="page.taskMatrix.tooltip.hideExpiredTasks" />' data-toggle="tooltip" type="button"><i class="fa fa-th-list"></i></button>
		    	<button id="showExpired" class="btn btn-primary tip" title='<spring:message code="page.taskMatrix.tooltip.showExpiredTasks" />' data-toggle="tooltip" type="button"><i class="fa fa-th-large"></i></button>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-8 spacing-bottom">
		<c:forEach items="${taskTypes}" var="taskType" varStatus="counter">
			<c:if test="${counter.count % 2 != 0 }">
				<div class="row">
			</c:if>
			
			<div class="col-md-6 col-sm-6 spacing-bottom">
				<div class="tiles ${taskType.color} weather-widget ">
					<div class="tiles-body task-list-body">
						<div class="controller">
							<a href="javascript:;" class="reload"></a>
							<a href="javascript:;" class="remove"></a>
						</div>
						<div class="tiles-title">
							<spring:message code="${taskType.displayName }"/>
						</div>
						<br />
						<div class="widget-body">
							<c:forEach items="${tasks}" var="task">
								<c:if test="${task.taskTypeId == taskType.value }">
									<!-- Choose CSS style -->
									<c:set var="taskInfoStyle"></c:set>
									<c:set var="taskHeaderStyle"></c:set>
									<c:set var="taskStyleByTime"></c:set>
									<c:choose>
										<c:when test="${task.isToday == true}">
											<c:set var="taskStyleByTime">taskToday</c:set>
										</c:when>
										<c:when test="${task.isWithin3Days == true}">
											<c:set var="taskStyleByTime">taskIn3Days</c:set>
										</c:when>
										<c:when test="${task.isFinished == true}">
											<c:set var="taskInfoStyle">taskInfoDone</c:set>
											<c:set var="taskStyleByTime">taskPast</c:set>
										</c:when>
									</c:choose>
									
									<div id="taskInfo_${task.taskId}" class="notification-messages info task-info task ${taskInfoStyle} ${taskStyleByTime}">
										<div class="date pull-right"><span class="badge" onClick="deleteEntity('${pageContext.request.contextPath}/tasks/delete/${task.taskId}');"><i class="fa fa-trash-o"></i></span></div>
										<div class="date pull-right">${task.estimatedDuration}h &nbsp;</div>
										<div class="message-wrapper">
											<div class="heading tip ${taskStyleByTime}" title="${task.name}" data-toggle="tooltip">${task.name}</div>
											<div class="description"><fmt:formatDate value="${task.deadline}" pattern="<%=Constant.FORMAT_DATE_TIME %>" /></div>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>
						<div class="clearfix">
						</div>
					</div>
					<div class="tile-footer">
						<div class="pull-left">
							<button id="btnAddTask_${taskType.value}" class="btn btn-dark btn-medium btnAddTask" type="button"><i class="fa fa-plus"></i></button>
						</div>
						<div class="pull-right">
							<canvas id="wind" width="32" height="32"></canvas>
							<span class="text-white small-text-description">
								<c:set var="counterText">${counter.count}</c:set>
								<spring:message code="page.taskMatrix.label.totalEstimatedDuration" /> 
								${hoursByType[counterText]}h
							</span>
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
	
	<!-- Form -->
	<div id="taskForm" class="app-hidden col-md-4">
		<div class="row">
			<div class="col-md-12 col-sm-6 spacing-bottom">
				<div id="formDialog" class="widget">
					<div class="widget-title dark">
						 <spring:message code="page.taskMatrix.form.title" />
						<div class="controller">
							<a id="formReload" href="javascript:;" class="reload"></a>
							<a href="javascript:;" class="remove"></a>
						</div>
					</div>
					<div class="widget-body">
						<form:form method="POST" commandName="task" action="${pageContext.request.contextPath}/tasks/save">
							<div class="col-md-12">
								<spring:bind path = "*">
								    <c:if test="${status.error}"> 
								    	<div class="alert alert-error">
											<button class="close" data-dismiss="alert"></button>
											<form:errors path="*" element="div" id="formErrorsDiv"/>
										</div>
								    </c:if> 
								</spring:bind>
							</div>
						
							<form:hidden path="projectId" value="${task.projectId}"/>
							<form:hidden path="taskId" value="${task.taskId}"/>
							<form:hidden path="taskTypeId" value="${task.taskTypeId}"/>
							<div class="col-md-12">
								&nbsp;<span class="requiredSymbol">*</span>
								<form:input path="name" value="${task.name}" cssClass="form-control" placeholder="${labelTaskName}"/>
							</div>
							<br>
							<div class="slider primary col-md-12">
								<form:label path="estimatedDuration" cssClass="form-label"><spring:message code="label.task.estimatedDuration"/>
									&nbsp;<span class="requiredSymbol">*</span>
									<span id="estimatedDurationText" class="badge badge-info"></span><spring:message code="symbol.hour"/>
								</form:label>
								<form:input path="estimatedDuration" value="${task.estimatedDuration}" cssClass="slider-element form-control" 
									data-slider-min="0" data-slider-max="8" data-slider-step="0.25" data-slider-value="${task.estimatedDuration}" data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="show"/>
							</div>
							<br>
							<div class="slider primary col-md-12">
								<form:label path="actualDuration" cssClass="form-label"><spring:message code="label.task.actualDuration"/>
									<span id="actualDurationText" class="badge badge-info"></span><spring:message code="symbol.hour"/>
								</form:label>
								<form:input path="actualDuration" value="${task.actualDuration}" cssClass="slider-element form-control" 
									data-slider-min="0" data-slider-max="8" data-slider-step="0.25" data-slider-value="${task.actualDuration}" data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="show"/>
							</div>
							<br>
							<div class="col-md-12">
								<form:label path="deadlineDate" cssClass="form-label"><spring:message code="label.task.deadline"/>&nbsp;<span class="requiredSymbol">*</span></form:label>
							</div>
							<div class="col-md-12">
								<div class="input-append success date">
									<fmt:formatDate value="${task.deadlineDate}" var="deadlineDateText" pattern="<%=Constant.FORMAT_DATE %>" />
									<form:input path="deadlineDate" value="${deadlineDateText}" cssClass="form-control" />
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
							<div class="slider primary col-md-12">
								<form:label path="completedPercentage" cssClass="form-label"><spring:message code="label.task.completedPercentage"/>
									<span id="completedPercentageText" class="badge badge-info"></span><spring:message code="symbol.percentage"/>
								</form:label>
								<form:input path="completedPercentage" value="${task.completedPercentage}" cssClass="slider-element form-control" placeholder="${labelTaskCompletedPercentage}" 
									data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="${task.completedPercentage}" data-slider-orientation="horizontal" data-slider-selection="after" data-slider-tooltip="show"/>
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
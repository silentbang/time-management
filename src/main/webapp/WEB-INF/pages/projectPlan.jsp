<%@page import="com.duke.passato.common.Constant"%>
<%@page import="com.duke.passato.common.UIUtils"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="col-md-12">
		<div class="grid simple ">
			<div class="grid-title no-border">
				<h4>${project.name} - 
					<span class="semi-bold"><fmt:formatNumber value="${project.averageProgress}" maxFractionDigits="2"/>%</span>
				</h4>
				<div class="tools">
					<a href="javascript:;" class="collapse"></a>
					<a href="#grid-config" data-toggle="modal" class="config"></a>
					<a href="javascript:;" class="reload"></a>
					<a href="javascript:;" class="remove"></a>
				</div>
			</div>
			<div class="grid-body no-border">
				<table class="table no-more-tables">
					<thead>
						<tr>
							<th style="width:1%">
								<div class="checkbox check-default">
									<input id="checkbox10" type="checkbox" value="1" class="checkall">
									<label for="checkbox10"></label>
								</div>
							</th>
							<th style="width:19%">
								<spring:message code="label.task.name"/>
							</th>
							<th style="width:2%">
								<spring:message code="label.task.estimatedDuration"/>
							</th>
							<th style="width:2%">
								<spring:message code="label.task.actualDuration"/>
							</th>
							<th style="width:5%">
								<spring:message code="label.task.deadline"/>
							</th>
							<th style="width:9%">
								<spring:message code="label.task.note"/>
							</th>
							<th style="width:10%">
								<spring:message code="label.task.completedPercentage"/>
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${plan.tasksByDays}" var="entry">
							<c:set var="dateText" value="${entry.key}"/>
							<c:set var="tasksByDate" value="${entry.value.tasks}"/>
							
							<tr class="tableHead">
								<th style="width:1%">
									<div class="checkbox check-default">
										<input id="checkbox10" type="checkbox" value="1" class="checkall">
										<label for="checkbox10"></label>
									</div>
								</th>
								<th style="width:19%"></th>
								<th style="width:2%">
									${entry.value.totalEstimatedDuration}
								</th>
								<th style="width:2%">
									${entry.value.totalActualDuration}
								</th>
								<th style="width:5%"><span class="label label-inverse">${dateText}</span></th>
								<th style="width:9%">&nbsp;</th>
								<th style="width:10%">
									<fmt:formatNumber value="${entry.value.averageProgress}" maxFractionDigits="2"/>%
								</th>
							</tr>
							<c:forEach items="${tasksByDate}" var="task">
								<tr style="border-color: red;">
									<td class="v-align-middle">
										<div class="checkbox check-default">
											<input id="checkbox11" type="checkbox" value="1">
											<label for="checkbox11"></label>
										</div>
									</td>
									<td class="v-align-middle">
										${task.name}
									</td>
									<td class="v-align-middle">
										${task.estimatedDuration}
									</td>
									<td>
										<!-- Highlight when differences happen -->
										<c:choose>
											<c:when test="${task.actualDuration < task.estimatedDuration}">
												<span class="badge badge-success">${task.actualDuration}</span>
											</c:when>
											<c:when test="${task.actualDuration > task.estimatedDuration }">
												<span class="badge badge-important">${task.actualDuration}</span>
											</c:when>
											<c:otherwise>
												${task.actualDuration}
											</c:otherwise>
										</c:choose>
									</td>
									<td>
										<fmt:formatDate value="${task.deadline}" pattern="<%=Constant.FORMAT_DATE_TIME %>" />
									</td>
									<td>
										<i>${task.note}</i>
									</td>
									<td class="v-align-middle">
										<div class="progress">
											<c:set var="progressCSSClass" value='${uiUtils.computeProgressBarCSSClass(task.completedPercentage)}'/>
											<div data-percentage="${task.completedPercentage}%" class="progress-bar ${progressCSSClass}  animate-progress-bar">
												<fmt:formatNumber maxFractionDigits="2" value="${task.completedPercentage}" />%
											</div>
										</div>
									</td>
								</tr>
							</c:forEach>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
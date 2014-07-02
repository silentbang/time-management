<%@page import="com.duke.timemanagement.common.UIUtils"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="col-md-12">
		<div class="grid simple ">
			<div class="grid-title no-border">
				<h4>${project.name} - <span class="semi-bold">50%</span></h4>
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
					<th style="width:9%">
						<spring:message code="label.task.name"/>
					</th>
					<th style="width:9%">
						<spring:message code="label.task.estimatedDuration"/>
					</th>
					<th style="width:9%">
						<spring:message code="label.task.actualDuration"/>
					</th>
					<th style="width:10%">
						<spring:message code="label.task.deadline"/>
					</th>
					<th style="width:10%">
						<spring:message code="label.task.completedPercentage"/>
					</th>
				</tr>
				</thead>
				</table>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<div class="grid simple ">
			<div class="grid-title no-border">
				<h4>${project.name} - <span class="semi-bold">50%</span></h4>
				<div class="tools">
					<a href="javascript:;" class="collapse"></a>
					<a href="#grid-config" data-toggle="modal" class="config"></a>
					<a href="javascript:;" class="reload"></a>
					<a href="javascript:;" class="remove"></a>
				</div>
			</div>
			<div class="grid-body no-border">
				<h3>Basic <span class="semi-bold">Table</span></h3>
				<table class="table no-more-tables">
				<thead>
				<tr>
					<th style="width:1%">
						<div class="checkbox check-default">
							<input id="checkbox10" type="checkbox" value="1" class="checkall">
							<label for="checkbox10"></label>
						</div>
					</th>
					<th style="width:9%">
						<spring:message code="label.task.name"/>
					</th>
					<th style="width:9%">
						<spring:message code="label.task.estimatedDuration"/>
					</th>
					<th style="width:9%">
						<spring:message code="label.task.actualDuration"/>
					</th>
					<th style="width:10%">
						<spring:message code="label.task.deadline"/>
					</th>
					<th style="width:10%">
						<spring:message code="label.task.completedPercentage"/>
					</th>
				</tr>
				</thead>
				<tbody>
					<c:forEach items="${tasks}" var="task">
						<tr>
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
								<span class="muted">${task.estimatedDuration}</span>
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
										<span class="muted">${task.actualDuration}</span>
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<span class="muted">${task.deadline}</span>
							</td>
							<td class="v-align-middle">
								<div class="progress">
									<c:set var="progressCSSClass" value='${uiUtils.computeProgressBarCSSClass(task.completedPercentage)}'/>
									<div data-percentage="${task.completedPercentage}%" class="progress-bar ${progressCSSClass}  animate-progress-bar">
									</div>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
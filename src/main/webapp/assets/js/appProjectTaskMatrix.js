$(document).ready(function() {
	// Update on demand
	$(".task-info").click(function() {
		var form = $("#formDialog");

		// Extract info
		var divId = $(this).prop("id");
		var taskId = extractNumber(divId);

		// Show task form
		showTaskForm();

		// Block UI
		triggerFormReloadBlock(form);
		// Call web service
		$.ajax({
			type : "POST",
			url : "/Passato/tasks/update/" + taskId,
			taskIdValue : taskId,
			success : onFetchTaskSuccess,
			error : onFetchTaskError
		});
	});
	
	// Show task form when Add buttons clicked
	$(".btnAddTask").click(function() {
		var btnId = $(this).prop("id");
		var taskTypeId = extractNumber(btnId);

		resetTaskForm();
		showTaskFormAndUpdateTaskTypeId(taskTypeId);
	});
});

//Block form
function triggerFormReloadBlock(el) {
	$(el).block({
		message : '<div class="loading-animator"></div>',
		css : {
			border : 'none',
			padding : '2px',
			backgroundColor : 'none'
		},
		overlayCSS : {
			backgroundColor : '#fff',
			opacity : 0.3,
			cursor : 'wait'
		}
	});
}

function showTaskFormAndUpdateTaskTypeId(order) {
	$("#taskForm").visible();
	$("#taskTypeId").val(order);
}

function showTaskForm() {
	$("#taskForm").visible();
}

function resetTaskForm(){
	$("#taskId").val(null);
	$("#taskTypeId").val(null);
	$("#name").val(null);
	$("#estimatedDuration").slider("setValue", 0);
	$("#actualDuration").slider("setValue", 0);
	$("#deadlineDate").val(null);
	$("#deadlineTime").val("00:00:00");
	$("#note").val(null);
	$("#completedPercentage").slider("setValue", 0);
	
	focusOnForm();
}

function onFetchTaskSuccess(data, statuss) {
	// Update task form
	$("#projectId").val(data["projectId"]);
	$("#taskId").val(data["taskId"]);
	$("#taskTypeId").val(data["taskTypeId"]);
	$("#name").val(data["name"]);
	$("#estimatedDuration").val(data["estimatedDuration"]);
	$("#actualDuration").val(data["actualDuration"]);
	$("#estimatedDuration").slider("setValue", data["estimatedDuration"]);
	$("#actualDuration").slider("setValue", data["actualDuration"]);
	$("#deadlineDate").val(data["deadlineDateText"]);
	$("#deadlineTime").val(data["deadlineTimeText"]);
	$("#note").val(data["note"]);
	$("#completedPercentage").val(data["completedPercentage"]);
	$("#completedPercentage").slider("setValue", data["completedPercentage"]);
	// Release block
	triggerFormReloadRelease($("#formDialog"));
	
	focusOnForm();
}

function onFetchTaskError(request, status, error) {
	console.log(status + " - " + error);
	triggerFormReloadRelease($("#formDialog"));
	
	focusOnForm();
}

function focusOnForm(){
	$("#name").focus();
}

function triggerFormReloadRelease(el) {
	// Await 0.2 sec before unlock
	window.setTimeout(function() {
		$(el).unblock();
	}, 200);
}
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
			url : "/TimeManagement/tasks/update/" + taskId,
			taskIdValue : taskId,
			success : onFetchTaskSuccess,
			error : onFetchTaskError
		});
	});

	// Show task form when Add buttons clicked
	$(".btnAddTask").click(function() {
		var btnId = $(this).prop("id");
		var taskTypeId = extractNumber(btnId);

		// TODO RESET FORM
		resetTaskForm();
		
		showTaskFormAndUpdateTaskTypeId(taskTypeId);
	});

	// Date picker
	$('#deadlineDate').datepicker({
		format : "yyyy-mm-dd",
		startView : 1,
		autoclose : true,
		todayHighlight : true,
		altField : '#deadlineDate'
	});

	// Time pickers
	$('.timepicker-24').timepicker({
		minuteStep : 1,
		showSeconds : true,
		showMeridian : false
	});

	// Block form
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

});

function go(url) {
	window.location = url;
}

function deleteEntity(url) {
	var isOk = confirm("Are you sure to delete ?");
	if (isOk) {
		go(url);
	}
}

// Extract number from string
function extractNumber(text) {
	return text.match(/\d+/)[0];
}

function showTaskFormAndUpdateTaskTypeId(order) {
	$("#taskForm").visible();
	$("#taskTypeId").val(order);
}

function showTaskForm() {
	$("#taskForm").visible();
}

/* Plugin */

// Visibility for input
jQuery.fn.visible = function() {
	return this.css('visibility', 'visible');
};

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
	// Release block
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
}

function onFetchTaskError(request, status, error) {
	console.log(status + " - " + error);
	triggerFormReloadRelease($("#formDialog"));

}

function triggerFormReloadRelease(el) {
	// Await 0.2 sec before unlock
	window.setTimeout(function() {
		$(el).unblock();
	}, 200);
}

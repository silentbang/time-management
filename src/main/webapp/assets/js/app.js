$(document).ready(function() {
	// Update on demand
	$(".task-info").click(function(){
		var form = $("#formDialog");
//		if (form.is(':visible')){
//			form.fadeOut(1000);
//		}
		
		// Extract info
		var divId = $(this).prop("id");
		var taskId = extractNumber(divId);
		
		// Call web service
		$.ajax({
			type: "POST",
			url: "/TimeManagement/tasks/update/" + taskId,
			taskIdValue: taskId,
			success: onFetchTaskSuccess,
			error: onFetchTaskError
		});
		
		// Update form content
//		form.sshow(1000);
	});
	
	// Show task form when Add buttons clicked
	$(".btnAddTask").click(function(){
		var btnId = $(this).prop("id");
		var taskTypeId = extractNumber(btnId);
		
		// TODO RESET FORM
		
		showTaskForm(taskTypeId);
	});
	
	
	// Date picker
	$('#deadlineDate').datepicker({
		format: "yyyy-mm-dd",
		startView: 1,
		autoclose: true,
		todayHighlight: true,
		altField: '#deadlineDate'
	});
	
	//Time pickers
    $('.timepicker-24').timepicker({
                minuteStep: 1,
                showSeconds: true,
                showMeridian: false
     });
});

function go(url){
	window.location = url;
}

function deleteEntity(url){
	var isOk = confirm("Are you sure to delete ?");
	if (isOk){
		go(url);
	}
}

//Extract number from string
function extractNumber(text) {
	return text.match(/\d+/)[0];
}

function showTaskForm(order) {
	$("#taskForm").visible();
	
	// TODO UPDATE TASKTYPEID
	$("#taskTypeId").val(order);
}

/* Plugin */

//Visibility for input
jQuery.fn.visible = function() {
	return this.css('visibility', 'visible');
};

function onFetchTaskSuccess(data, statuss){
	console.log(data);
	console.log(data["name"]);
	console.log(this.taskIdValue);
	
	// Show task form
	showTaskForm(data["taskTypeId"]);
	
	// Update task form
	$("#projectId").val(data["projectId"]);
	$("#taskId").val(data["taskId"]);
	$("#taskTypeId").val(data["taskTypeId"]);
	$("#name").val(data["name"]);
	$("#estimatedDuration").slider("setValue", data["estimatedDuration"]);
	$("#actualDuration").slider("setValue", data["actualDuration"]);
	$("#deadlineDate").val(data["deadlineDateText"]);
	$("#deadlineTime").val(data["deadlineTimeText"]);
	$("#note").val(data["note"]);
	$("#completedPercentage").slider("setValue", data["completedPercentage"]);
}

function onFetchTaskError(request, status, error){
	console.log(status + error);
}

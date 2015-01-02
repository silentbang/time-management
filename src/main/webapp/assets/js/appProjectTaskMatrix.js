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
			url : contextPath + "/tasks/update/" + taskId,
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
		focusOnForm();
	});
	
	// If errors exist then show form
	var formErrorsDiv = $("#formErrorsDiv");
	if (formErrorsDiv.text().trim().length){
		showTaskForm();
	}
	
	// Toggle display for expired tasks
	$("#hideExpired").click(function(){
		// Set active button
		$(this).addClass("active");
		$("#removeLineThrough").removeClass("active");
		
		// Hide task infos which are done
		$(".taskInfoDone").css("display", "none");
	});
	$("#showExpired").click(function(){
		$(this).addClass("active");
		$("#addLineThrough").removeClass("active");
		
		$(".taskInfoDone").css("display", "block");
	});
	
	// Slider text automatically changes on slide
	var estimatedDurationSlider = $("#estimatedDuration");
	estimatedDurationSlider.on('slide', function(){
		$("#estimatedDurationText").text(estimatedDurationSlider.val());
	});
	var actualDurationSlider = $("#actualDuration");
	actualDurationSlider.on('slide', function(){
		$("#actualDurationText").text(actualDurationSlider.val());
	});
	var completedPercentageSlider = $("#completedPercentage");
	completedPercentageSlider.on('slide', function(){
		$("#completedPercentageText").text(completedPercentageSlider.val());
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
	$("#estimatedDurationText").text(0);
	$("#actualDuration").slider("setValue", 0);
	$("#actualDurationText").text(0);
	$("#deadlineDate").val(null);
	$("#deadlineTime").val("00:00:00");
	$("#note").val(null);
	$("#completedPercentage").slider("setValue", 0);
	$("#completedPercentageText").text(0);
}

function onFetchTaskSuccess(data, status) {
	// Update task form
	$("#projectId").val(data["projectId"]);
	$("#taskId").val(data["taskId"]);
	$("#taskTypeId").val(data["taskTypeId"]);
	$("#name").val(data["name"]);
	$("#estimatedDuration").val(data["estimatedDuration"]);
	$("#estimatedDurationText").text(data["estimatedDuration"]);
	$("#estimatedDuration").slider("setValue", data["estimatedDuration"]);
	$("#actualDuration").val(data["actualDuration"]);
	$("#actualDurationText").text( (data["actualDuration"] == null)?0:data["actualDuration"] );
	$("#actualDuration").slider("setValue", data["actualDuration"]);
	$("#deadlineDate").val(data["deadlineDateText"]);
	$("#deadlineTime").val(data["deadlineTimeText"]);
	$("#note").val(data["note"]);
	$("#completedPercentage").val(data["completedPercentage"]);
	$("#completedPercentageText").text( (data["completedPercentage"] == null)?0:data["completedPercentage"] );
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

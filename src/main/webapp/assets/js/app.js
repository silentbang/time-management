$(document).ready(function() {
	$(".task-info").click(function(){
		var form = $("#formDialog");
		if (form.is(':visible')){
			form.fadeOut(1000);
		}
		
		// Update form content
		form	.show(1000);
	});
	
	// Date picker
	$('#sandbox-advance').datepicker({
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


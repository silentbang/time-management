$(document).ready(function() {

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

/* Plugin */

// Visibility for input
jQuery.fn.visible = function() {
	return this.css('visibility', 'visible');
};

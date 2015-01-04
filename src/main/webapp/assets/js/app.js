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

// Cookie functions
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
    }
    return "";
}

/* Plugin */

// Visibility for input
jQuery.fn.visible = function() {
	return this.css('visibility', 'visible');
};

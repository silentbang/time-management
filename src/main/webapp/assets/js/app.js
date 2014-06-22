$(document).ready(function() {
	$(".task-info").click(function(){
		var form = $("#formDialog");
		if (form.is(':visible')){
			form.fadeOut(1000);
		}
		
		// Update form content
		form	.show(1000);
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
function go(url){
	window.location = url;
}

function deleteEntity(url){
	var isOk = confirm("Are you sure to delete ?");
	if (isOk){
		go(url);
	}
		
}
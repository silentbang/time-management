$(document).ready(function() {
	
	$.get("statistics/duration", function(data, status){
        new Morris.Line({
  		  element: 'durationChart',
  		  data: data,
  		  xkey: 'projectName',
  		  ykeys: ['estimatedDuration', 'actualDuration'],
  		  labels: ['Estimated duration', 'Actual duration'],
  		  lineColors:['#d1dade', '#0aa699'],
  		  postUnits: 'h',
  		});
    });

	$.get("statistics/averagePercentage", function(data, status){
        new Morris.Line({
  		  element: 'averagePercentageChart',
  		  data: data,
  		  xkey: 'projectName',
  		  ykeys: ['averagePercentage'],
  		  labels: ['Average percentage'],
  		  lineColors:['#4DA74D'],
  		  postUnits: '%',
  		});
    });
});
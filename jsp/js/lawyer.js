/**
 * Author: Stevan Gostojic
 * Date: 2011-02-24
 * 
 */

$(document).ready(function() {
	$(".datepicker").datepicker({ 
		changeMonth: true, 
		changeYear: true,
		minDate: "-200Y",
		maxDate: "-1D",
		dateFormat: 'dd/mm/yy',
		yearRange: 'c-140:c+140',
		autoSize: false
	});
	
	$("button, input:submit, input:reset").button();
	
	jQuery.ajaxSetup({
		async: false,
		cache: false
	});
});


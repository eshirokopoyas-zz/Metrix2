$(document).ready(function(){
	if ($('body').is('.dynamic'))
		{
			var $jumbotron = $('.jumbotron');
			$jumbotron.stop();
			$jumbotron.slideUp(0);
			$jumbotron.slideDown(4000);
		}
var $div_li = $('div div div div, li');  
$div_li.hover(function(){
	$(this).fadeTo('fast',1);
}, function(){
	$(this).fadeTo('fast',0.75);
});
$('.pull-me').click(function(){
	$('.nav').toggle();
});
});

function post(path, params, method)
{
	method = method || "post"; 

	var form = document.createElement("form");
	form.setAttribute("method", method);
	form.setAttribute("action", path);

	for ( var key in params)
	{
		if (params.hasOwnProperty(key))
		{
			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", key);
			hiddenField.setAttribute("value", params[key]);

			form.appendChild(hiddenField);
		}
	}

	document.body.appendChild(form);
	form.submit();
}
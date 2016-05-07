$(document).ready(function(){
	if ($('body').is('.dynamic'))
		{
			var $jumbotron = $('.jumbotron');
			$jumbotron.slideUp(0);
			$jumbotron.slideDown(4000);
		}
$('div div div div, li').hover(function(){
	$(this).fadeTo('fast',1);
}, function(){
	$(this).fadeTo('fast',0.75);
});
$('.pull-me').click(function(){
	$('.nav').toggle();
});
});
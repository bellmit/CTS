$(document).ready(function() {
$("#sidebar-collapse").on('click',function(){
$('body').toggleClass("menu-min");
});
if($('.navbar-toggle').is(':visible') || $('.mobile-detected').length >0)
{
 $navFn();
}


}); 
 
 $navFn = function(){
 var nav = $('nav li');
    $(document).on('click', 'nav a[href="#"]', function(e) {
		if ($(this).parent().hasClass('active')){
		$(this).parent().removeClass('active');
		return false;
		}
		nav.removeClass('active');
		if($(this).parent().find('ul').length >0){
		$(this).parent().addClass('active');
		return false;
		}
    });

 }
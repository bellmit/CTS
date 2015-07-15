/*
	 * APP DOM REFERENCES
	 * Description: Obj DOM reference, please try to avoid changing these
	 */	
	$.root_ = $('body');
	$.navToggle = $(".navbar-toggle")
	$.window = $(window);
	//Wrapper
	$.wrapper = $('#wrapper');
	$.bread_crumb = $('.breadcrum');
    // desktop or mobile
    $.device = null;
	//Cookie
	$.cookieFn = null
	//quickLinks
	$.quickLinks = null;
/*
	 * DETECT MOBILE DEVICES
	 * Description: Detects mobile device - if any of the listed device is detected
	 * a class is inserted to $.root_ and the variable $.device is decleard. 
	 */	
	
	/* so far this is covering most hand held devices */
	var ismobile = (/iphone|ipad|ipod|android|blackberry|mini|windows\sce|palm/i.test(navigator.userAgent.toLowerCase()));

	if (!ismobile) {
		// Desktop
		$.root_.addClass("desktop-detected");
		$.device = "desktop";
	} else {
		// Mobile
		$.root_.addClass("mobile-detected");
		$.device = "mobile";
		
		// Removes the tap delay in idevices
		// dependency: js/plugin/fastclick/fastclick.js 
		//FastClick.attach(document.body);
	}

/* ~ END: CHECK MOBILE DEVICE */

$.checkMblview = function() {
	/* Detects mobile view */
	if ($.window.width() < 979) {
		$.root_.addClass('mobile-view')
	} else if ($.root_.hasClass('mobile-view')) {
		$.root_.removeClass('mobile-view');
	}
	/* Detects tablet landscape */
	if($.window.width() > 980 && $.window.width() < 1008)
	{
	$.root_.addClass('tablet-view')
	}else if ($.root_.hasClass('tablet-view')) {
		$.root_.removeClass('tablet-view');
	}
}

//Quicklinks mobile function
$.quickLinks = function(cls)
{
	var $quickLink = $('.'+cls);
	var $mblquick = null;
	if($('.mblquick').length === 0 && $('.mobile-view').length > 0)
	{
		$.wrapper.append('<div class="mblquick"><a href="#" class="quickClose"><i class="glyphicon glyphicon-remove"></a></i></div>');
		$mblquick = $('.mblquick');
		$mblquick.append($quickLink.html());
		$quickLink.find('h3').on('click',function(){
			$('body').addClass('ql-open');
		});
		$mblquick.find('.quickClose').on('click',function(){
			$('body').removeClass('ql-open');
		});
	}
}
 
 
	/* window resize function*/	
$.window.resize(function() {
        if(this.resizeMe) clearTimeout(this.resizeMe);
        this.resizeMe = setTimeout(function() {
		$.checkMblview();
        }, 500);
});	
/* window resize function Ends*/	

	/* Mask Overlay function*/	
$.maskOverlay = function()
{
var $mask = $('.mask');
var html = null;
if($mask.length<=0){
	html = $('<div class="mask"></div>');
	$('body').append(html);
}
	if(html.hasClass('show'))
	{
		html.removeClass('show');
	}
	else
	{
		html.addClass('show');
	}
}


$(document).ready(function() {
// Menu Open and Collapse
$.navToggle.on('click',function(){
  $.root_.toggleClass("menu-open");
  //$.maskOverlay();

});
$.checkMblview();
$.quickLinks('quick-links');
 });
  
$(document).on('click', '#top-nav button', function(e) {
	    e.preventDefault();
	    //var $this = $(e.currentTarget);
		$(this).parent().parent().toggleClass('open');
	   
		console.log(1);

    });
	
	$(document).on('click', 'nav a[href!="#"]', function(e) {
	    //e.preventDefault();
	    
	    var $this = $(e.currentTarget);

	    
    });
	
      // fire links with targets on different window
    $(document).on('click', 'nav a[target="_blank"]', function(e) {
	    e.preventDefault();
	    var $this = $(e.currentTarget);
	    console.log(3);
	    window.open($this.attr('href'));
    });

    // fire links with targets on same window
    $(document).on('click', 'nav a[target="_top"]', function(e) {
	    e.preventDefault();
	    var $this = $(e.currentTarget);
	    console.log(4);
	    window.location = ($this.attr('href'));
    });
 // all links with hash tags are ignored

	
// UPDATE BREADCRUMB
function drawBreadCrumb() {
	var nav_elems = $('nav li.active > a'), count = nav_elems.length;
	
	//console.log("breadcrumb")
	$.bread_crumb.empty();
	$.bread_crumb.append($("<li>Home</li>"));
	nav_elems.each(function() {
		$.bread_crumb.append($("<li></li>").html($.trim($(this).clone().end().text())));
		// update title when breadcrumb is finished...
		if (!--count) document.title = $.bread_crumb.find("li:last-child").text();
	});

} 
// Cookie function
$.cookieFn = {
//set Cookie function
	set:function(c_name,value){
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + 365);
		var c_value = escape(value) + "; expires=" + exdate.toUTCString()+"; path=/";
		document.cookie = c_name + "=" + c_value;
	},
//get Cookie function	
	get:function(name){
	var cookiename;
		if(document.cookie)
		{
			index = document.cookie.indexOf(name);
			if (index != -1)
			{
				namestart = (document.cookie.indexOf("=", index) + 1);
				nameend = document.cookie.indexOf(";", index);
			if (nameend == -1) {nameend = document.cookie.length;}
				cookiename = document.cookie.substring(namestart, nameend);
				return cookiename;
			}
		}
	},
//remove Cookie function
	remove:function(name){
	document.cookie = name + '='+';expires=Thu, 01-Jan-1970 00:00:01 GMT; path=/';
	}
}
// CHECK TO SEE IF URL EXISTS

 // LOAD AJAX PAGES


 
 
 
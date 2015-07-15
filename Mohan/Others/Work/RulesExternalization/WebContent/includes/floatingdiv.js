//define universal reference to "staticcontent"

var crossobj= document.getElementById("showhide_floatDiv")
var theDiv = document.getElementById("exceptionDiv")  // used when having to reference second top parameter
var theDivTop = crossobj.style.top

//define reference to the body object in IE
var iebody=(document.compatMode && document.compatMode != "BackCompat")? document.documentElement : document.body


function positionit(){
	//define universal dsoc left point
	var dsocleft=document.all? iebody.scrollLeft : pageXOffset
	
	//define universal dsoc top point
	var dsoctop=document.all? iebody.scrollTop : pageYOffset
	
	// distance from window top to div top
	var divTop=theDiv.offsetTop 

	if(divTop > dsoctop){
		crossobj.style.right=parseInt(dsocleft)+0+"px"
		crossobj.style.top=0+"px"
	}
	else{
		crossobj.style.right=parseInt(dsocleft)+0+"px"
		var theTop = dsoctop - divTop;
		crossobj.style.top=theTop+0+"px"
	}

	//if the user is using IE 4+ or Firefox/ NS6+
	//if (document.all||document.getElementById){
	/*if(r)
		
	else 
		crossobj.style.right=parseInt(dsocleft)+22+"px"
	if(t)
		crossobj.style.top=dsoctop+t+"px"
	else
		crossobj.style.top=dsoctop+0+"px"*/
	crossobj.style.zIndex=20000
	//}
}
setInterval("positionit()",100)
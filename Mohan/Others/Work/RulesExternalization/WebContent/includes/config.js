//To change the header area for a new state, enter the Abbreviation for that state here
//As of 09/2012 the only options are "ACS" and "MT"
var state = "MT";

switch (state){
	case "ACS":
		var appName = "ACS Health Enterprise Portal";
		var appLogo = "acslogonew.gif";
		break;
	case "MT":
		var appName = "Montana Health Enterprise Portal";
		var appLogo = "logo_mt.gif";
		break;
	default:
		var appName = "ACS Health Enterprise Portal";
		var appLogo = "logo_nh.gif";
}

function writeHead(){
	head = '<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />';
	head = '<meta http-equiv="cache-control" content="no-cache"/>';
	head = head + '<title>' + appName + '</title>';
	head = head + '<link rel="shortcut icon" href="' + path + 'images/favicon.ico" type="image/x-icon" />';
	head = head + '<link rel="stylesheet" type="text/css" href="' + path + 'style/portal.css" />';
	if (isSelfService == 1){
		head = head + '<link rel="stylesheet" type="text/css" href="' + path + 'style/selfservice.css" />';
	}
	head = head + '<link rel="stylesheet" type="text/css" href="' + path + 'style/print.css" media="print" />';
	head = head + '<script type="text/javascript" src="' + path + 'includes/udm-resources/horizontal-absolute-top-left.js"></script>';
	head = head + '<script type="text/javascript" src="' + path + 'includes/udm-resources/udm-control.js"></script>';
	head = head + '<script type="text/javascript" src="' + path + 'includes/general.js"></script>';
	head = head + '<script type="text/javascript" src="' + path + 'includes/inc_portlet.js"></script>';
	head = head + '<script type="text/javascript" src="' + path + 'includes/inc_topNav.js"></script>';
	head = head + '<script type="text/javascript" src="' + path + 'includes/inc_footer.js"></script>';
	document.write(head);
}

function writePageBegin(){
	begin = '<script type="text/javascript" src="' + path + 'includes/udm-resources/udm-dom.js"></script>';
	begin = begin + '<script type="text/javascript" src="' + path + 'includes/udm-resources/udm-mod-keyboard.js"></script>';
	begin = begin + '<div id="wrapper">';
	begin = begin + '<script>writeTopNav(\''+ area +'\')</script>';
	begin = begin + '<div id="content">';
	document.write(begin);
}

function writePageEnd(){
	end = 		'</div>' +
			'</div>' +
			'<div><img src="' + path + 'images/x.gif" border="0" width="1" height="5" alt="" /></div>' +
			'<script>writeFooter(path, \''+ area +'\');</script>';
	document.write(end);
}
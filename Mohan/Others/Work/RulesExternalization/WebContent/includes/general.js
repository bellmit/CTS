	// basic Document

// Get today's Date
function makeArray() {
     for (i = 0; i<makeArray.arguments.length; i++)
          this[i + 1] = makeArray.arguments[i];
}

var months = new makeArray('January','February','March',
    'April','May','June','July','August','September',
    'October','November','December');

var date = new Date();
var day  = date.getDate();
var month = date.getMonth() + 1;
var yy = date.getYear();
var year = (yy < 1000) ? yy + 1900 : yy;

today = months[month] + " " + day + ", " + year;
//

//
// Function to get parameters from html "querystring"
var locate = window.location
var action = locate.toString()

function delineate(str){
	theleft = str.indexOf("=") + 1;
	theright = str.lastIndexOf("&");
	return(str.substring(theleft, theright));
}

//

function openClose(hidden,id){
	var el = document.getElementById(hidden);
	var elLink = document.getElementById(id);
	if(elLink != null && el != null && elLink.className == 'minus'){
		el.style.display = 'none';
		elLink.className = 'plus';
	}
	else if( elLink != null && el != null && elLink.className == 'plus'){
		el.style.display = 'block';
		elLink.className = 'minus';
	}
}

function autotab(original,destination){
	if (original.getAttribute&&original.value.length==original.getAttribute("maxlength"))
		destination.focus()
}

function radioToggle(hidden,state) {
	var el = document.getElementById(hidden);
	if (state == 1) {
		el.style.display = 'block';
	}
	else if (state == 0) {
	    //alert('hiden is WHAT=>' + hidden + "]");
		el.style.display = 'none';
	}
}


// for use when setting element to a block element disturbs display
function radioToggleNoBlock(hidden,state) {
//alert(hidden);
	var el = document.getElementById(hidden);
	if (state == 1) {
		el.style.display = '';
	}
	else if (state == 0) {
		el.style.display = 'none';
	}
}

function hideMe(hide, exempt) {
	recurse(document.documentElement);

	function recurse(node) {
		if (node != null) {
			if (node.childNodes != null) {
				for (var i = 0; i < node.childNodes.length; ++i) {
					recurse(node.childNodes[i]);
				}
			}
			var isHidden = node.id != null && node.id.match(hide);
			var isExempt = node.id != null && exempt != null && node.id.match(exempt);
			if (!isExempt && isHidden) {
				node.style.display = "none";
			}
		}
	}
}

function showAll(show) {
	recurseShow(document.documentElement);

	function recurseShow(node) {
		if (node != null) {
			if (node.childNodes != null) {
				for (var i = 0; i < node.childNodes.length; ++i) {
					recurseShow(node.childNodes[i]);
				}
			}
			var isHidden = node.id != null && node.id.match(show);
			if (isHidden) {
				node.style.display = "block";
			}
		}
	}
}

function showMe(area,hideVar) {
	if (!document.getElementById) return null;
	showArea = document.getElementById(area);
	showArea.style.display = "block";

	if (!hideVar){
		hideMe('showhide_',area);
	}
	else{
		hideMe(hideVar,area);
	}
}

function hideTabs(exempt,tab,parentId){
	if (!document.getElementsByTagName) return null;
	if (!exempt) exempt = "";
	if (!tab) tab = "";
	if(parentId){
		var parentCont = document.getElementById(parentId);
		var divs = parentCont.getElementsByTagName("div");
		var tabs = parentCont.getElementsByTagName("a");
	}
	else{
		var divs = document.getElementsByTagName("div");
		var tabs = document.getElementsByTagName("a");
	}
	for(var i=0; i < divs.length; i++){
		var div = divs[i];
		var id = div.id;
		//alert(id)
		if ((id.indexOf("tab_") != -1) && (id != exempt)){
			div.style.display = "none";
		}
	}
	for(var j=0; j < tabs.length; j++){
		var a = tabs[j];
		var aid = a.id;
		//alert(aid)
		if ((aid.indexOf("tablink_") != -1) && (aid != tab)){
			a.className = "";
		}
	}
}

function showTabs(what,tab,parentId) {
	if (!document.getElementById) return null;
	showWhat = document.getElementById(what);
	showTab = document.getElementById(tab);
	showWhat.style.display = "block";
	showTab.className = "active";
	hideTabs(what,tab,parentId);
}

//Check all function for checkboxes
var checkflag = "false";
function check(field) {
if (checkflag == "false") {
for (i = 0; i < field.length; i++) {
field[i].checked = true;}
checkflag = "true";
return "Uncheck All"; }
else {
for (i = 0; i < field.length; i++) {
field[i].checked = false; }
checkflag = "false";
return "Check All"; }
}

//display remaining characters for textarea
function textCounter(field,cntfield,maxlimit) {
	var vLength = field.value.length
	if (vLength > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
	// otherwise, update 'characters left' counter
	else
		cntfield.value = maxlimit - vLength;
}

function textCounter2(field,cntfield,maxlimit) {
       var el = document.getElementById(field);
       var elRem = document.getElementById(cntfield);
       var vLength = el.value.length;
       if (vLength > maxlimit) // if too long...trim it!
               el.value = el.value.substring(0, maxlimit);
       // otherwise, update 'characters left' counter
       else
               elRem.value = maxlimit - vLength;
}



//Move select box items to another
var NS4 = (navigator.appName == "Netscape" && parseInt(navigator.appVersion) < 5);

function addOption(theSel, theText, theValue)
{
  var newOpt = new Option(theText, theValue);
  var selLength = theSel.length;
  theSel.options[selLength] = newOpt;
}

function deleteOption(theSel, theIndex)
{
  var selLength = theSel.length;
  if(selLength>0)
  {
	theSel.options[theIndex] = null;
  }
}

function moveOptions(theSelFrom, theSelTo)
{

  var selLength = theSelFrom.length;
  var selectedText = new Array();
  var selectedValues = new Array();
  var selectedCount = 0;

  var i;

  // Find the selected Options in reverse order
  // and delete them from the 'from' Select.
  for(i=selLength-1; i>=0; i--)
  {
	if(theSelFrom.options[i].selected)
	{
	  selectedText[selectedCount] = theSelFrom.options[i].text;
	  selectedValues[selectedCount] = theSelFrom.options[i].value;
	  deleteOption(theSelFrom, i);
	  selectedCount++;
	}
  }

  // Add the selected text/values in reverse order.
  // This will add the Options to the 'to' Select
  // in the same order as they were in the 'from' Select.
  for(i=selectedCount-1; i>=0; i--)
  {
	addOption(theSelTo, selectedText[i], selectedValues[i]);
  }

  if(NS4) history.go(0);
}

//write out pod menus
function getLink( name, href, currentPage ){
	if( currentPage != href ){
		return '<li><a href="' + href +'">' + name +'</a></li>';
	}
	else{
		return '<li class="here">' + name + '</li>';
	}
}




function resetForm(formName,results) {
	document.forms[formName].reset()
	if (results){
		hideMe(results);
	}
}

// Title: tigra tables
// URL: http://www.softcomplex.com/products/tigra_tables/
// Version: 1.1
// Date: 08/15/2005
// Notes: Permission given to use this script in any kind of applications if
//    header lines are left unchanged.

function tigra_tables (
		str_tableid, // table id (req.)
		num_header_offset, // how many rows to skip before applying effects at the begining (opt.)
		num_footer_offset, // how many rows to skip at the bottom of the table (opt.)
		str_odd_color, // background color for odd rows (opt.)
		str_even_color, // background color for even rows (opt.)
		str_mover_color, // background color for rows with mouse over (opt.)
		str_onclick_color // background color for marked rows (opt.)
	) {

	// validate required parameters
	if (!str_tableid) return alert ("No table(s) ID specified in parameters");
	var obj_tables = (document.all ? document.all[str_tableid] : document.getElementById(str_tableid));
	if (!obj_tables) return alert ("Can't find table(s) with specified ID (" + str_tableid + ")");

	// set defaults for optional parameters
	var col_config = [];
	col_config.header_offset = (num_header_offset ? num_header_offset : 0);
	col_config.footer_offset = (num_footer_offset ? num_footer_offset : 0);
	col_config.odd_color = (str_odd_color ? str_odd_color : '#ffffff');
	col_config.even_color = (str_even_color ? str_even_color : '#dbeaf5');
	col_config.mover_color = (str_mover_color ? str_mover_color : '#6699cc');
	col_config.onclick_color = (str_onclick_color ? str_onclick_color : '#4C7DAB');

	// init multiple tables with same ID
	if (obj_tables.length)
		for (var i = 0; i < obj_tables.length; i++)
			tt_init_table(obj_tables[i], col_config);
	// init single table
	else
		tt_init_table(obj_tables, col_config);
}

function tt_init_table (obj_table, col_config) {
	var col_lconfig = [],
		col_trs = obj_table.rows;
	if (!col_trs) return;
	for (var i = col_config.header_offset; i < col_trs.length - col_config.footer_offset; i++) {
		col_trs[i].config = col_config;
		col_trs[i].lconfig = col_lconfig;
		col_trs[i].set_color = tt_set_color;
		col_trs[i].onmouseover = tt_mover;
		col_trs[i].onmouseout = tt_mout;
		col_trs[i].onmousedown = tt_onclick;
		col_trs[i].order = (i - col_config.header_offset) % 2;
		col_trs[i].onmouseout();
	}
}
function tt_set_color(str_color) {
	this.className = str_color;
}

// event handlers
function tt_mover () {
	if (this.lconfig.clicked != this)
		this.set_color(this.config.mover_color);
}
function tt_mout () {
	if (this.lconfig.clicked != this)
		this.set_color(this.order ? this.config.odd_color : this.config.even_color);
}
function tt_onclick () {
	if (this.lconfig.clicked == this) {
		this.lconfig.clicked = null;
		this.onmouseover();
	}
	else {
		var last_clicked = this.lconfig.clicked;
		this.lconfig.clicked = this;
		if (last_clicked) last_clicked.onmouseout();
		this.set_color(this.config.onclick_color);
	}
}

function getUrlParameterArray() {
  input = decodeURI(location.search).substring(1);
  return input.length > 0 ? input.split("&") : new Array();
}

/**
 * Get the parameters passed in the URL as an object.
 * The parameter has a property for each parameter.
 * If the parameter has a value, it is used, otherwise it is set to true.
 */
function getUrlParameters() {
  parameters = new Object();
  array = getUrlParameterArray();
  for (i = 0; i < array.length; ++i) {
    index = array[i].indexOf("=");
    attribute = index == -1 ? array[i] : array[i].substring(0, index);
    value = index == -1 ? true : array[i].substring(index + 1);
    parameters[attribute] = value;
  }
  return parameters;
}

function getProperties(object) {
  string = "";
  for (i in object) {
    if (i.match(/[0-9]+/)) continue;
    if (string != "") string += ", ";
    string += i + "=" + object[i];
  }
  return string;
}

function toString(object) {
  return object + "  {" + getProperties(object) + "}";
}

function setDisableInputTagsOf(element, disabledValue) {
  tags = ["INPUT", "SELECT", "TEXTAREA"];
  for (i = 0; i < tags.length; ++i) {
    elements = element.getElementsByTagName(tags[i]);

    if (elements == null || elements == undefined) continue;
    for (j = 0; j < elements.length; ++j) {
      var el = elements[j];

      if((el.type == "text") ||
      	(el.type == "select-one") ||
      	(el.type == "select-multiple") ||
      	(el.type == "textarea"))

      	{
      		el.disabled = disabledValue;
      		if(disabledValue == true)
     			{
					el.className = "disabled";
				}
				else
				{
					el.className = ""
				}
      	}
      else if((el.type == "checkbox") ||
      			(el.type == "radio"))
      			{
      				el.disabled = disabledValue;
						//el.className = "noBorder";
      			}
		}
  }
}


function showDate(){
	var aceDate=new Date();
	var aceYear=aceDate.getYear();
	if (aceYear < 1000){
		aceYear+=1900;
	}
	var aceDay=aceDate.getDay();
	var aceMonth=aceDate.getMonth()+1;
	if (aceMonth<10){
		aceMonth="0"+aceMonth;
	}
	var aceDayMonth=aceDate.getDate();
	if (aceDayMonth<10){
		aceDayMonth="0"+aceDayMonth;
	}
	return aceMonth+"/"+aceDayMonth+"/"+aceYear;
}


// shows progress bar when button is pushed
// buttId is id of button triggering progress bar

function showHideProgressBar(buttId){

	var theBox;
	var ind;

	var iebody=(document.compatMode && document.compatMode != "BackCompat")? document.documentElement : document.body
	var dsocleft=document.all? iebody.scrollLeft : pageXOffset
	var dsoctop=document.all? iebody.scrollTop : pageYOffset

	showProgress(buttId);

	function showProgress(buttId){

		var ind = 0;
		theBox = document.getElementById('waiting')
		theBox.style.left=parseInt(dsocleft)+500+"px"
		theBox.style.top=dsoctop+150+"px"
		theBox.style.display = 'block';
		buttonWorks(buttId,'active')
		intervalID = setInterval(hideProgress, 4000);
	}

	function hideProgress(){
	 	theBox.style.display = 'none';
	 	buttonWorks(buttId,'dis')
	 	clearInterval(intervalID);
	}
}

function buttonWorks(id,action){
	theButt = document.getElementById(id);
	if(action == 'active'){
		theButt.disabled = true;
		theButt.className = 'formBtn disabledButton';
	}
	else if(action == 'dis'	){
		theButt.disabled = false;
		theButt.className = 'formBtn';
	}
}

function newWindow(page){
	window.open(page,null,"height=500,width=700,status=yes,toolbar=no,menubar=no,location=no");
}

function writeProgressInd(str){
	if(!str) str = 'claim'
   box =
   	'<div  id="waiting" class="progressCont">' +
   		'<div id="progress" class="inprogress">' +
   			'<div class="inprogressinner">' +
					'<div>Please wait while your ' + str + ' is processing...</div>' +
					'<div><img src="../../images/x.gif" border="0" width="1" height="10" alt="" /></div>' +
					'<img src="../../images/progressbar2.gif" />' +
				'</div>' +
			'</div>' +

			'<iframe id="frame" src="javascript:" class="progressframe"></iframe>' +

		'</div>';
	document.write(box);
}
function writeProgressInd1(str){
	if(!str) str = 'Application'
   box =
   	'<div  id="waiting" class="progressCont">' +
   		'<div id="progress" class="inprogress">' +
   			'<div class="inprogressinner">' +
					'<div>Please wait while your ' + str + ' is processing...</div>' +
					'<div><img src="../../../images/x.gif" border="0" width="1" height="10" alt="" /></div>' +
					'<img src="../../../images/progressbar2.gif" />' +
				'</div>' +
			'</div>' +

			'<iframe id="frame" src="javascript:" class="progressframe"></iframe>' +

		'</div>';
	document.write(box);
}

// claim form functions

function addClaim(){

}

function saveClaim(theField, val){
 	var theVal = parseInt(val)
 	var newVal = theVal + 1;
 	var strVal = newVal.toString();
	if(strVal.length < 2){
 		theField.value = newVal;
 		//alert('less 2: '+ theField.value)
 	}
 	else if(strVal.length >= 2){
 		theField.value =  newVal;
 		//alert('more 2: '+ theField.value)
 	}
}


/*
	currently in use for cosxt acuity tables
	works with array of sections defined on html page
*/
function openAll(){
	var xLength = sectionArray.length-1
	for(x in sectionArray){
		var linkId = 'plusMinus_' + sectionArray[x]
		var el = document.getElementById(sectionArray[x]);
		var elLink = document.getElementById(linkId);
		el.style.display = '';
		elLink.className = 'minus';
		openCloseGroup(el,'entry_','show')
		openCloseGroup(el,'total_','show')
	}
 }

function closeAll(){
	for(x in sectionArray){
		var linkId = 'plusMinus_' + sectionArray[x]
		var el = document.getElementById(sectionArray[x]);
		var elLink = document.getElementById(linkId);
		el.style.display = 'none';
		elLink.className = 'plus';
		//openCloseGroup(el,'entry_','hide')
		//openCloseGroup(el,'total_','hide')
	}
}
// currently not using
function openCloseGroup(el,group,action){
	var divs = el.getElementsByTagName('div')
	var dLength = divs.length-1;
	for(var d=0; d<=dLength; d++){
		if(divs[d].id.match(group)){
			var linkId = 'plusMinus_' + divs[d].id
			var divLink = document.getElementById(linkId);
			if(action == 'hide'){
				divs[d].style.display = 'none';
				divLink.className = 'plus';
			}
			else if(action == 'show'){
				divs[d].style.display = '';
				divLink.className = 'minus';
			}
		}
	}
}

function rowColor(id){
	var tab = document.getElementById(id);
	var rowArray = tab.getElementsByTagName('tr');
	rowArray = findShowRows(rowArray)
	var l=rowArray.length
	for (i=2;i<l;i++){
		if (i%2 == 0){
			rowArray[i].className = 'row_alt';
		}
		else {
			rowArray[i].className = 'row';
		}
	}
}

function popContextHelp(pWhere){
	window.open(pWhere +"help.html","help","resizable=1,width=650,height=450");
}

function ckField(fieldName) {
	var theField = document.getElementById(fieldName);
	var theValue = theField.value

	if (theValue != ""){
		alert("Save it")
	}
}

function deleteConfirm(where, str) {
if(!str)str = '';
	if (confirm("Are you sure you want to Delete?"))	{
		deleteMsg(where);
		hideMe('table'+ str + '_');
	}
}
function deleteConfirm1(where, str) {
if(!str)str = '';
	if (confirm("This will permanently delete all rows of data in your user defined table.  Are you sure that you want to proceed?"))	{
		deleteMsg(where);
		hideMe('table'+ str + '_');
	}
}
function voidConfirm(where, str) {
if(!str)str = '';
	if (confirm("Are you sure you want to Void?"))	{
		voidMsg(where);
		hideMe('table'+ str + '_');
	}
}

function deleteRow(msg,str)  {
	if (confirm("Are you sure you want to Delete?"))  {
   		deleteMsg(msg);
        radioToggle(str,0);
	}
}

function detachRow(msg,str)  {
	if (confirm("Are you sure you want to Detach?"))  {
   		detachMsg(msg);
        radioToggle(str,0);
	}
}

function toRow(msg,str)  {
	if (alert("You cannot have a CC when multiple To recipients are identified."))  {
   		voidMsg(msg);
        radioToggle(str,0);
	}
}

function msgSent(msg,str)  {
	if (alert("A copy of this request has been sent to your inbox."))  {
   		voidMsg(msg);
        radioToggle(str,0);
	}
}
function voidRow(msg,str)  {
	if (confirm("Are you sure you want to Void?"))  {
   		voidMsg(msg);
        radioToggle(str,0);
	}
}

function hideRow(msg,str)  {
	if (confirm("Are you sure you want to Exit?"))  {
   		 radioToggle(str,0);
	}
}

function deleteToPage(page)  {
	if (confirm("Are you sure you want to Delete?"))  {
   		window.location = page;
	}
}

function reassignRow(msg,str)  {
	if (confirm("Are you certain that you want to Reassign?"))  {
   		reassignMsg(msg);
        radioToggle(str,0);
	}
}


function copy(msg,str)  {
	if (confirm("Do you want to apply unsaved changes to the existing Capitation / Case Management Record and copy to a new entry?"))  {
   		copyMsg(msg);
        radioToggle(str,0);
	}
}

//Specific to Acuity MQIP Approval
function mqipApproval(where, str) {
if(!str)str = '';
	if (confirm("Are you sure you want to Approve this version? This process cannot be reversed."))	{
		window.location = page;
	}
}

//Specific to Provider Org Security
function orgNewPin(where, str) {
if(!str)str = '';
	if (confirm("Confirm that you are requesting a New PIN for this Organization Administrator by clicking on the OK button below. To exit this function without assigning a new PIN, click on the Cancel button below."))	{
		window.location = page;
	}
}
//Specific to Member-Find Provider
function mbrFindProv(where, str) {
if(!str)str = '';
	if (confirm("You must complete the tutorial before choosing a provider."))	{
		document.location.href="../members/findPassportProvider.html";
	}
}
//Specific to Provider Maint Security
function prvNewPswd(where, str) {
if(!str)str = '';
	if (confirm("Confirm that you are requesting a new single-use password for this Provider or Trading Partner by clicking on the OK button below. To exit this function without requesting a new password, click on the Cancel button below."))	{
		document.location.href="../../../security/resetpass.html";
	}
}
//Specific to Member Detail
function mbrNewPin(where, str) {
if(!str)str = '';
	if (confirm("Confirm that you are requesting a New PIN for this Member by clicking on the OK button below. To exit this function without assigning a new PIN, click on the Cancel button below."))	{
		window.location = page;
	}
}

function mbrUnlockWeb(where, str) {
if(!str)str = '';
	if (confirm("Confirm that you are requesting to reset this Member's PIN status by clicking on the OK button below. To exit this function without resetting the the Member's PIN status, click on the Cancel button below."))	{
		window.location = page;
	}
}
//end Member Detail

//Specific to SwipeCard
function mbrSwipeRepl(where, str) {
if(!str)str = '';
	if (confirm("Confirm that you want to create a MA ID Card Replacement request by clicking on the OK button below.  To exit this function without creating a MA ID Card Replacement request, click on the Cancel button below."))	{
		window.location = page;
	}
}
function mbrSwipeDeact(where, str) {
if(!str)str = '';
	if (confirm("Confirm that you want to deactivate this active MA ID Card by clicking on the OK button below.  To exit this function without deactivating the active MA ID Card, click on the Cancel button below."))	{
		window.location = page;
	}
}
function mbrSwipeDelete(where, str) {
if(!str)str = '';
	if (confirm("Confirm that you want to delete this Pending Card Request by clicking on the OK button below.  To exit this function without deleting the Pending Card Request, click on the Cancel button below."))	{
		window.location = page;
	}
}
//end SwipeCard

// Function for saving a page
function success(str){
	if(!str) str = '';
	el = document.getElementById('msgBox'+str);
	el.innerHTML = "Success"
	var theMsgBox = 'message_success' + str
	showMe(theMsgBox,'message_');
}
function approve(str){
	if(!str) str = '';
	el = document.getElementById('msgBox'+str);
	el.innerHTML = "Approved"
	var theMsgBox = 'message_success' + str
	showMe(theMsgBox,'message_');
}
function reject(str){
	if(!str) str = '';
	el = document.getElementById('msgBox'+str);
	el.innerHTML = "Rejected"
	var theMsgBox = 'message_success' + str
	showMe(theMsgBox,'message_');
}

// function for displaying a delete message
function deleteMsg(str){
	if(!str) str = '';
	el = document.getElementById('msgDeleteBox'+str);
	el.innerHTML = "Successfully Deleted";
	var theMsgBox = 'message_delete' + str
	showMe(theMsgBox,'message_');
}

function detachMsg(str){
	if(!str) str = '';
	el = document.getElementById('msgDeleteBox'+str);
	el.innerHTML = "Successfully Detached";
	var theMsgBox = 'message_delete' + str
	showMe(theMsgBox,'message_');
}

// function for displaying a void message
function voidMsg(str){
	if(!str) str = '';
	el = document.getElementById('msgVoidBox'+str);
	el.innerHTML = "Successfully Voided";
	var theMsgBox = 'message_void' + str
	showMe(theMsgBox,'message_');
}

// function for displaying a reassign message
function reassignMsg(str){
	if(!str) str = '';
	el = document.getElementById('msgDeleteBox'+str);
	el.innerHTML = "Successfully Reassigned";
	var theMsgBox = 'message_delete' + str
	showMe(theMsgBox,'message_');
}


//Function for
function showDup(str){
	if(!str) str = '';
	el = document.getElementById('msgBox'+str);
	el.innerHTML = "A Potential Duplicate Carrier has been found."
	var theMsgBox = 'message_duplicate_member' + str
	showMe(theMsgBox,'message_');
}


//Specific to TPL Policy where Group does not yet belong to Carrier
function tplGroupUpdate(where, str) {
if(!str)str = '';
	if (confirm("The Group is not found. Would you like to add this Group ID to the Carrier before saving the Policy?"))	{
		window.open("editcarrier.html");
	}
}

// Used for Alt ID
function altid(str){
	if(!str) str = '';
	el = document.getElementById('msgBox'+str);
	el.innerHTML = "The Alternate ID has been successfully transferred to the Target Member. TCN Specific Claims Adjustment request(s) have been generated successfully for the transferred Alternate ID"
	var theMsgBox = 'message_altid' + str
	showMe(theMsgBox,'message_');
}


// Unmerge Claims Transfer
function unmergeClaims(str){
	if(!str) str = '';
	el = document.getElementById('msgUnmergeClaimsBox'+str);
	el.innerHTML = "TCN Specific Claims Adjustment request(s) generated successfully for selected claims."
	var theMsgBox = 'message_unmerge_claims' + str
	showMe(theMsgBox,'message_');
}



// NEW FUNCTION BEING USED IN COMMON TILES  /////////////////////////////////////////////

function deleteMsg2(type, id, hide){
	 if (confirm("Are you sure you want to Delete?")) {
	 writeMsg(type, id);
       radioToggle(hide,0);
	}
}

function declineArgeement(){
  if(confirm("We regret that you have declined the Medicaid Provider Enrollment Agreement.  You may not enroll as a Medicaid Provider unless you accept the agreement.  Please contact ACS by phone or fax to assist you and answer questions you may have about the agreement.  Phone (999) 999-9999 Fax (999) 999-9999")){
  	location.href="../start.html";
  }
}

function reassign(type, id, hide){
	 if (confirm("Are you sure you want to Reassign the selected records?")) {
	 writeMsg(type, id);
       radioToggle(hide,0);
	}
}



function confirmExit(){
	if(confirm("Do you wish to exit this application?\n All unsaved data will be lost,\nand the page cannot be recalled for printing.")){
		document.location.href="../start.html";
	}
}

function confirmExitMT(){
	if(confirm("Confirm that you want to exit the enrollment form without saving by clicking on the 'OK' button below. To save the enrollment form or continue working, select the 'Cancel' button below.")){
		document.location.href="../start.html";
	}
}

function confirmPCPchangeMT(){
	if(confirm("Confirm that you want to send a request to the MT Health Program to select this as your Primary Care Provider by clicking on the 'OK' button below. To make a different selection or keep your existing Primary Care Provider, select the 'Cancel' button below.")){
		document.location.href="index2.html";
	}
}



function writeAuditSection(path, str, w)	{
	if(!w) w = '50';
	if(!str) str = '';
	section = '<fieldset class="expand">' +
							'<legend><a href="javascript:void(0)" onClick="openClose(\'moreContact' + str + '\',this.id);" id="plusMinus_moreContact' + str + '" class="plus">Audit</a></legend>' +
							'<div id="moreContact' + str + '" class="hide">' +
                    '<table cellspacing="0" width="98%" class="dataTable" id="audit_table_line">' +
                        '<tr>' +
                            '<td class="tableTitle">Audit Detail</td>' +
                        '</tr>' +
                        '<tr>' +
                            '<th>Timestamp</th>' +
                            '<th>User</th>     ' +
                            '<th>Activity</th>' +
                            '<th>Column</th>' +
                            '<th>Before</th>' +
                            '<th>After</th>' +
                        '</tr>' +
                        '<tr class="row">' +
                            '<td>08/25/2009 06:44:11AM</td>' +
                            '<td>Kitteredge, Gloria-GKitteredge</td>' +
                            '<td>UO</td>' +
                            '<td>Quantity</td>' +
                            '<td>500000</td>' +
                            '<td>250000</td>' +
                        '</tr>' +
                        '<tr class="row_alt">' +
                            '<td>08/25/2009 06:44:11AM</td>' +
                            '<td>Kitteredge, Gloria-GKitteredge</td>' +
                            '<td>UO</td>' +
                            '<td>Generic ID</td>' +
                            '<td>12-00087</td>' +
                            '<td>55-19009</td>' +
                        '</tr>' +
                        '<tr class="row">' +
                            '<td>08/25/2009 06:44:11AM</td>' +
                            '<td>Kitteredge, Gloria-GKitteredge</td>' +
                            '<td>UO</td>' +
                            '<td>Price</td>' +
                            '<td>59.99</td>' +
                            '<td>75.00</td>' +
                        '</tr>' +
                        '<tr class="row_alt">' +
                            '<td>06/11/2009 08:10:25AM</td>' +
                            '<td>Thomas, Rob-ACS0001</td>' +
                            '<td>UO</td>' +
                            '<td>Quantity</td>' +
                            '<td>100000</td>' +
                            '<td>500000</td>' +
                        '</tr>' +
                        '<tr class="row">' +
                            '<td>06/11/2009 08:10:25AM</td>' +
                            '<td>Thomas, Rob-ACS0001</td>' +
                            '<td>UO</td>' +
                            '<td>Generic ID</td>' +
                            '<td>12-00099</td>' +
                            '<td>12-00087</td>' +
                        '</tr>' +
                        '<tr class="row_alt">' +
                            '<td>06/11/2009 08:10:25AM</td>' +
                            '<td>Thomas, Rob-ACS0001</td>' +
                            '<td>UO</td>' +
                            '<td>Price</td>' +
                            '<td>999.99</td>' +
                            '<td>59.99</td>' +
                        '</tr>' +
                        '<tr class="row">' +
                            '<td>10/15/2008 11:03:01AM</td>' +
                            '<td>Hutchens, Celina-CHutch999</td>' +
                            '<td>UO</td>' +
                            '<td>Price</td>' +
                            '<td>1023.44</td>' +
                            '<td>999.99</td>' +
                        '</tr>' +
                        '<tr class="row_alt">' +
                            '<td>01/22/2007 04:22:36PM</td>' +
                            '<td>Hutchens, Celina-CHutch999</td>' +
                            '<td>I</td>' +
                            '<td>Quantity</td>' +
                            '<td></td>' +
                            '<td>100000</td>' +
                        '</tr>' +
                        '<tr class="row">' +
                            '<td>01/22/2007 04:22:36PM</td>' +
                            '<td>Hutchens, Celina-CHutch999</td>' +
                            '<td>I</td>' +
                            '<td>Generic ID</td>' +
                            '<td></td>' +
                            '<td>12-00099</td>' +
                        '</tr>' +
                        '<tr class="row_alt">' +
                            '<td>01/22/2007 04:22:36PM</td>' +
                            '<td>Hutchens, Celina-CHutch999</td>' +
                            '<td>I</td>' +
                            '<td>Price</td>' +
                            '<td></td>' +
                            '<td>1023.44</td>' +
                        '</tr>' +
                        '</table>' +
							'</div>' +
						'</fieldset>';
	document.write(section);
}// end of function writeAuditSection





function writeCBAuditSection(path, str, w)	{
	if(!w) w = '50';
	if(!str) str = '';
	section = '<fieldset class="expand">' +
			'<legend><a href="javascript:void(0)" onClick="openClose(\'moreContact' + str + '\',this.id);" id="plusMinus_moreContact' + str + '" class="plus">Audit</a></legend>' +
			'<div id="moreContact' + str + '" class="hide">' +
				'<table cellspacing="0" width="' + w + '%">' +
					'<tr style="cursor:pointer">' +
						'<td class="dataLabel">Last Update Date / Time:</td>' +
						'<td><a href="javascript:radioToggle(\'log_detail' + str + '\',1)">06/30/2006 05:17:25PM</a></td>' +
					'</tr>' +
					'<tr>' +
						'<td class="dataLabel">Last Update User ID:</td>' +
						'<td>County Billing System<br /><br /></td>' +
					'</tr>' +
					'<tr>' +
						'<td class="dataLabel">Original Entry Date / Time:</td>' +
						'<td>06/15/2006 03:45:09PM</td>' +
					'</tr>' +
					'<tr>' +
						'<td class="dataLabel">Original Entry User ID:</td>' +
						'<td>County Billing System</td>' +
					'</tr>' +
					'<tr>' +
						'<td colspan="2">' +
							'<div><img src="' + path + 'images/x.gif" border="0" width="1" height="10" alt="" /></div>' +
						'</td>' +
					'</tr>' +
				'</table>' +
				'<div id="log_detail' + str + '" class="hide">' +
					'<table cellspacing="0" width="' + w + '%" class="dataTable">' +
						'<tr>' +
							'<th>Field Name</th>' +
							'<th>Before</th>' +
							'<th>After</th>' +
						'</tr>' +
						'<tr class="row">' +
							'<td>Sample Field 1</td>' +
							'<td>002</td>' +
							'<td>000</td>' +
						'</tr>' +
						'<tr class="row_alt">' +
							'<td>Sample Field 2</td>' +
							'<td>002</td>' +
							'<td>000</td>' +
						'</tr>' +
					'</table>' +
				'</div>' +
			'</div>' +
		'</fieldset>';
	document.write(section);
}// end of function writeCBAuditSection for County Billing




function DisableEnableForm(xForm,xHow){
  objElems = xForm.elements;
  for(i=0;i<objElems.length;i++){
    objElems[i].disabled = xHow;
  }
}






// author jhillmann....not implemented yet
function toggleNav(){
	var nav = document.getElementById('mainHeader')

	if(nav.style.display == 'none')
	{
		nav.style.display = 'block'
	}
	else
	{
		nav.style.display = 'none'
	}
}

function updateClaimCheckBox(){
	if(document.getElementById("allclaims").checked == true)
	{
		document.getElementById("selectclaim1").checked = true;
		document.getElementById("selectclaim2").checked = true;
		document.getElementById("selectclaim3").checked = true;
		document.getElementById("selectclaim4").checked = true;
		document.getElementById("selectclaim5").checked = true;
	}
	else
	{
		document.getElementById("selectclaim1").checked = false;
		document.getElementById("selectclaim2").checked = false;
		document.getElementById("selectclaim3").checked = false;
		document.getElementById("selectclaim4").checked = false;
		document.getElementById("selectclaim5").checked = false;
	}
}
function updateClaimLineCheckBox(){
	if(document.getElementById("allclaimlines").checked == true)
	{
		document.getElementById("selectclaimline1").checked = true;
		document.getElementById("selectclaimline2").checked = true;
		document.getElementById("selectclaimline3").checked = true;
	}
	else
	{
		document.getElementById("selectclaimline1").checked = false;
		document.getElementById("selectclaimline2").checked = false;
		document.getElementById("selectclaimline3").checked = false;
	}
}

/*
	openCloseDom() - open and close plus/minus sections using DOM instead of element ids

*/

function openCloseDom(theLink){
	var tbParent = theLink.parentNode.parentNode;
	var oDiv = tbParent.getElementsByTagName('div')
	var tb = oDiv[0];

	if(theLink.className == 'plus')
	{
		tb.className = '';
		theLink.className = 'minus';
	}
	else if(theLink.className == 'minus')
	{
		tb.className = 'hide';
		theLink.className = 'plus';
	}
	else
	{
		return
	}
}


// ***********************

function openCloseNear(hidden, id, root){
	var el = findClosestById(hidden, root);
	var elLink = findClosestById(id, root);
	if (elLink.className == 'minus'){
		el.style.display = 'none';
		elLink.className = 'plus';
	}
	else if (elLink.className == 'plus'){
		el.style.display = 'block';
		elLink.className = 'minus';
	}
}

function radioToggleNear(hidden, state, root) {
	var el = findClosestById(hidden, root);
	if (state == 1) {
		el.style.display = 'block';
	}
	else if (state == 0) {
	    //alert('hiden is WHAT=>' + hidden + "]");
		el.style.display = 'none';
	}
}

// Similar to document.getElementById() except that you can pass in a root to start your search from
// this is quite useful when the id may exist more than once in a document: a common occurance when
// using the tiles system.
function findById( id, root )
{
  if( root == null )
  {
    root = document;
  }
  if( typeof( root ) == "string" )
  {
    root = document.getElementById( root );
  }
  if( root.id == id )
  {
    return root;
  }
  for( var inx = 0; inx < root.childNodes.length; inx++ )
  {
    var element = findById( id, root.childNodes[inx] );
    if( element != null )
    {
      return element;
    }
  }
  return null;
}

function findClosestById( id, element )
{
	if( element == document )
	{
		return document.getElementById( id );
	}
  else
	{
		var target = findById( id, element );
		if( target != null )
		{
			return target;
		}
	  else
		{
			return findClosestById( id, element.parentNode );
		}
	}
}

/*
	- relPath() will loop through a section that has been added by ajax and
	  replace image paths with the correct relative path

	- based on page 'path' var

	- On the page calling this function you need to define and pass an array
	  whose elements contain ids of divs receiving ajax code.

	- Call relPath() after your ajax tiles have been loaded to the page
*/

function relPath(oArr){
	try
	{
		var a;
		for(a in oArr)
		{
			var box = document.getElementById(oArr[a])
			var oImgs = box.getElementsByTagName('img')
			var iLength = oImgs.length-1
			try
			{
				for(x=0; x<=iLength; x++)
				{
					oSrc = oImgs[x].src.split('images');
					var newSrc = path + 'images' + oSrc[1];
					oImgs[x].src = newSrc;
				}
			}
			catch(e)
			{
				alert('There is an error in the oImgs loop')
			}
		}
	}
	catch(e)
	{
		alert('You need to define an array containing the ids of divs receiving ajax code.')
	}
}

function openHelpWindow(path){
	window.open(path + "help.html","Help","menubar=1,resizable=1,width=550,height=250");
}


function popOpenHelp(linkto){
	window.open(linkto,'Help','scrollbars=yes,menubar=no,height=600,width=800,resizable=yes,toolbar=no,location=no,status=no')
}
function openSearchWindow(){
	window.open(path + "search.html","Help","scrollbars=yes,menubar=1,resizable=1,width=1000,height=700");
}
function changeReason(){
	window.open(path + "changeReason.html","Help","scrollbars=no,menubar=0,resizable=1,width=250,height=100");
}

function portalPrint(){
	var body= document.getElementById('content');
	//alert(body.innerHTML)
	myWindow = window.open("", "tinyWindow","scrollbars=yes,menubar=1,resizable=1,width=900,height=500")
	 myWindow.document.write("<html><head>")
	 myWindow.document.write("<link rel=\"stylesheet\" href=\""+ path +"style/portal.css\">")
	 myWindow.document.write("<link rel=\"stylesheet\" href=\""+ path +"style/print.css\">")
	 myWindow.document.write("<p>&nbsp;</p><div><input type=\"button\" value=\"Print\" onclick=\"window.print()\" />&nbsp;&nbsp;<input type=\"button\" value=\"Close\" onclick=\"window.close()\" /></div>")
	myWindow.document.write("</head><body>")
	 myWindow.document.write(body.innerHTML)

	 myWindow.document.write("</body></html>")

//used for common address popup when GeoStan validation fails
function badAddressPopup(){
		window.open("../../internal/ContactManagement/addressPopup.html","Address Verification Failed","menubar=0,resizable=0,width=850,height=150");
	}
}
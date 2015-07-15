    //You need an anonymous function to wrap around your function to avoid conflict
    (function($){
        //Attach this new method to jQuery
        $.fn.extend({
            
            //This is where you write your plugin's name
            jdataTable: function(options) {
			
			//Set the default values, use comma to separate the settings, example:
            var defaults = {
				jsondata : {},
				gHead:'Header',
				header:{
				by:[],
				sort:[],
				titles:[],
				width:[]
				},
				dataDisplay:[],
				fixedCol:true,
				fixedDiv:'<div class="staticCol"></div>',
				tableContainerCls:'tblcontainer',
				$thead:'thead',
				$tbody:'tbody',
				Theader:true,
				rowSelect:'selected',
				itemsPerPage:10,
				currentPage:1,
				numberOfPages:1,
				numberOfItems:0,
				target:null,
				pagination:true,
				$tfoot:'tfooter',
				prevCls:'prev',
				nextCls:'next',
				Alignpagination:'',
				pageActive:'active',
				pageDisable:'disabled',
				windowWidth:748,
				staticCol:2
            }
			var options =  $.extend(defaults, options);
			var currentPage = 1,
				numberOfPages=1,
				numberOfItems=0,
				sortRow = 0;
			var parentObj = $(this);
			parentObj.initGrid(options);
			if(options.Theader){parentObj.initHeader(options);}
			parentObj.jdataPagination(options);
			if($(window).width() < 992){$(this).getScroll(options);}
                //Iterate over the current set of matched elements
            return this.each(function() {
					var opt = options;
					var obj = $(this);  
					//obj.pageEvtbind(opt);
					//Header Click functionality
					obj.find('th').on('click',function(evt){
					evt.preventDefault();
					var id = $(this).attr('data-by');
					var type = $(this).attr('data-sort');
					 if(type === '')return false;
					var asc = (!$(this).attr('data-asc')); // switch the order, true if not set
					obj.find('th').removeAttr('data-asc');
					sortRow = $(this).index();
					if(sortRow === 0 || sortRow === 1)
					{
						if(asc){
						$('.staticCol',obj).find('th').eq(sortRow).attr('data-asc', 'A');
						$('.'+opt.tableContainerCls,obj).find('th').eq(sortRow).attr('data-asc', 'A');}
						else{
						$('.staticCol',obj).find('th').eq(sortRow).removeAttr('data-asc');
						$('.'+opt.tableContainerCls,obj).find('th').eq(sortRow).removeAttr('data-asc');
						}
					}
					if (asc){ 
					$(this).attr('data-asc', 'A');
					}
					else {
					}
					obj.jSortResults(opt,id,type,asc);
					});
					
					//Select PageSize functionality
					$( '.selectPgsize' ).on( 'click','.dropdown-menu li', function( event ) {
						var $target = $( event.currentTarget );
						$target.closest( '.btn-group' )
						.find( '[data-bind="label"]' ).text( $target.text() )
						.end()
						.children( '.dropdown-toggle' ).dropdown( 'toggle' );
						opt.itemsPerPage = parseInt($target.text());
						parentObj.jdataPagination(opt);
						return false;
						}); 
					
						$('a.deleteRow',obj).on('click',function(){
						var parent = $(this).parent().parent();
						var ind = parent.index()
						$('.staticCol tbody tr').eq(ind).remove();
						parent.remove();
						
						return false;
						});	

						/*$('tbody > tr',obj).hover(function(){
						$(this).addClass('hover');
						},function(){
						$(this).removeClass('hover');
						});*/
									
						
					console.log(parentObj.length);
					//Window Resize functionality
					jQuery(window).resize(function() {
						if(this.resizeTO) clearTimeout(this.resizeTO);
						this.resizeTO = setTimeout(function() {
						$(this).doResizing(options);
						}, 500);
						});	

				}); // Returns End
				
				
				
			
			
		},
	
		// Grid Initialization
		initGrid:function(options){
		var jg = $.extend ({},options);
			$(this).append(jg.fixedDiv);
			var that = $(this);
			$('.staticCol',that).append($('<table/>',{class:'table table-bordered table-striped'}));
			$('.staticCol',that).find('table').append('<'+jg.$thead+'/>'+'<'+jg.$tbody+'/>');
			
		},
		
		
		
		//Pag Bind Events
		pageEvtbind:function(options){
		var opt = $.extend ({},options);
		var obj = $(this);
		var pagination = $('.'+opt.$tfoot+'> ul',obj);
					var pg = pagination.find('li');
					var pgClick = $('> a.page',pg);
					var prevClk = pagination.find('li.prev');
					var nextClk = pagination.find('li.next');
					
					// Page Button click
					pgClick.on('click',function(event){
					event.preventDefault();
					var val = parseInt($(this).text(),10);
					target = obj.jactivePg(opt);
					if(target === val) return false;
					obj.jshowData(options,val);
					});
					
					// Previous Button click
					prevClk.on('click',function(event){
					//alert(2);
					target = obj.jactivePg(opt);
					if ( target === 1 ) 
						{ 
							//o.target = lastElem;
							return false;
						}
						else 
						{ 
							target = target-1;
						
						}
						obj.jshowData(opt,target);
						return false;
					
					
					});
					// Next Button click
					nextClk.on('click',function(event){
					target = obj.jactivePg(opt);
					lastElem = obj.jnumPages(opt);
					if ( target === lastElem ) 
						{ 
						 //o.target = 0;
						 return false;
						}
						else 
						{ 
							target = target+1;
						}
						obj.jshowData(opt,target);
						return false;
					});
					
		},
		
		// Grid Header Build
		initHeader:function(options){
		var jh = $.extend ({},options);
		var html = "";
		var hasHeader = false;
		var tdwidths;
		var that = $(this);
		 html += '<tr>';
		 for(i=0;i<jh.header.sort.length;i++)
			{
				var person = jh.jsondata[i];
				 for (var prop in person) {
					  if (person.hasOwnProperty(prop)) {
						// insert the header once
						if (hasHeader === false) {
						  jh.header.by.push(prop);
						  }
					  }
					}
				hasHeader = true;
				if(typeof jh.header.width === 'undefined'){
				tdwidths = ''
				}
				else
				{
				tdwidths = (typeof jh.header.width[i] != undefined) ? "width="+jh.header.width[i]+"%" :'';
				}
				if(jh.header.titles.length > 0){
				html += '<th data-by="'+jh.header.by[i]+'" tabindex="0"  data-sort="'+jh.header.sort[i]+'" '+tdwidths+' >'+jh.header.titles[i]+'</th>';
				}
				else{
				html += '<th data-by="'+jh.header.by[i]+'" tabindex="0" data-sort="'+jh.header.sort[i]+'" '+tdwidths+'>'+jh.header.by[i]+'</th>';
				}
			}
			html += '</tr>';
			$('.'+jh.tableContainerCls,that).find(jh.$thead).append(html);
			
			//$(jh.$thead,'.'+jh.tableContainerCls).append(html);
		},
		
		// Grid Data Display
		jdataDisplay:function(options,results,page){
		var jd = $.extend ({},options);
		var that = $(this);
		results = results;
		$(jd.$tbody,that).empty();

		var html = "";
		for (var i=0; i<results.length; i++) {
			var person = results[i];
			
			html += '<tr>';
			
			for (var prop in person) 
			{	
				if (person.hasOwnProperty(prop)) {
				// insert the header once
					
					if(prop === 'Action'){
					html += '<td align="center"><a href="#" tabindex="0" class="glyphicon glyphicon-trash deleteRow">Delete</a></td>';
					}
					else if(prop === 'Priority'){
					html += '<td class='+person[prop].replace( /\s/g, "")+'>'+person[prop]+'</td>';
					}
					else if(prop === 'AuthorizationID'){
					html += '<td><a href="#">'+person[prop]+'</a></td>';
					}
					/*else if(prop === 'Subject'){
					html += '<td><span>'+person[prop]+'</span></td>';
					}*/
					else
					{
						html += '<td>'+person[prop]+'</td>';
					}
				}
			}
			html += '</tr>';
		
		}
		$('.'+jd.tableContainerCls,that).find(jd.$tbody).append(html);
		
		$(this).jStatiColumn(options);
		$(this).jPageAction(options,page);
		$(this).alernative(options);
		$(this).getScroll(options);
		
		},
		// Static Column
		jStatiColumn:function(options)
		{
		var jsp = $.extend ({},options);
		var that = $(this);
		var staticHead = $('.staticCol table>'+jsp.$thead, that);
		var staticBody = $('.staticCol table>'+jsp.$tbody, that);
		staticBody.empty();
		var headOnce = false;
		if(!headOnce && staticHead.find('tr').length === 0 ){
		var hCol = $('.'+jsp.tableContainerCls,that).find("th").eq(0).clone();
		if(jsp.staticCol === 2){
		var hCol1 = $('.'+jsp.tableContainerCls,that).find("th").eq(1).clone();
		}
		var thRow = $('<tr/>').append(hCol,hCol1);
		$('.staticCol table >'+jsp.$thead, that).append(thRow);
		staticHead.find('th').removeAttr('width');
		headOnce = true;
		}
		$('.'+jsp.tableContainerCls+' table > tbody tr',that).each(function(){
			
			var stCol = $(this).find("td").eq(0).clone();
			if(jsp.staticCol === 2){
			var stCol1 = $(this).find("td").eq(1).clone();
			}
			var trow = $('<tr/>').append(stCol,stCol1);
			$('.staticCol table >'+jsp.$tbody, that).append(trow);
			});
		},
		
		
		// Get Page Account
		jnumPages:function(options){
		var jnp = $.extend ({},options);
		jnp.numberOfItems = jnp.jsondata.length;
		return Math.ceil(jnp.numberOfItems / jnp.itemsPerPage);
		},
		
		
		// Get Active Page Index Number
		jactivePg:function(options){
		var jpg = $.extend ({},options);
		var pagination = $('.'+jpg.$tfoot+'> ul');
		var pg = pagination.find('li');
		active = pagination.find("."+jpg.pageActive);
		return pg.index(active);
		
		},
		
		// Pagination Build
		jdataPagination:function(options){
		var jdp = $.extend ({},options);
		var that = $(this);
		if(jdp.pagination){
		jdp.numberOfPages = $(this).jnumPages(options);
		var tfooter = $('.'+jdp.$tfoot,that);
		if($('.pagination',that).length>0)$('.pagination').remove();
		tfooter.append('<ul class="pagination pull-right"/>');
		var tUl = $('.'+jdp.$tfoot+'> ul',that);
		for(var i = 1;i<=jdp.numberOfPages;i++)
		{
		var $li = $("<li>").appendTo(tUl);
			$("<a/>",{
			href:'#',
			text:i,
			class:'page'
			}).appendTo($li);
		}
		var $prev = $("<li>",{class:jdp.prevCls}).prependTo(tUl);
			$("<a/>",{
			href:'#'}).html('<i class="glyphicon glyphicon-chevron-left"><i>').appendTo($prev);
		var $next = $("<li>",{class:jdp.nextCls}).appendTo(tUl);
		$("<a/>",{
			href:'#'}).html('<i class="glyphicon glyphicon-chevron-right"><i>').appendTo($next);
			$(this).pageEvtbind(options);
		}
		$(this).jshowData(options,jdp.currentPage);
		},
		
		
		// Number of data should be shown
		jshowData:function(options,page){
		var jsd = $.extend ({},options);
		jsd.currentPage = page;
            startPage = (jsd.currentPage - 1) * jsd.itemsPerPage;
            endPage = startPage + jsd.itemsPerPage;
			var results = jsd.jsondata;
      results = results.slice(startPage, endPage);
	  $(this).jdataDisplay(options,results,page);
		},
		
		// Pagination Active/Disable class added
		jPageAction:function(options,cpage){
		var jac = $.extend ({},options);
		var that = $(this);
		var pagination = $('.'+jac.$tfoot+'> ul', that);
		var pg = pagination.find('li');
		var prev = pagination.find('li.'+jac.prevCls);
		var next = pagination.find('li.'+jac.nextCls);
		jac.numberOfPages = $(this).jnumPages(options);
		pg.removeClass(jac.pageActive).eq(cpage).addClass(jac.pageActive);
		var selected = pagination.find("."+jac.pageActive);
		jac.target = pg.index(selected);
		if(jac.target === 1)
		{
		prev.addClass('disabled');
		next.removeClass('disabled');
		}
		else if(jac.target===jac.numberOfPages)
		{
		next.addClass('disabled');
		prev.removeClass('disabled');
		}
		else
		{
		prev.removeClass('disabled');
		next.removeClass('disabled');
		}
		},
		
		// Sorting the results functionality
		jSortResults:function(options,prop,type,asc){
		var jsr = $.extend ({},options);
		var data = jsr.jsondata;
		data = data.sort(function(a, b) {
			if (asc){
		if(type === 'number')
		{
		return b[prop] - a[prop];
		}
		else if(type === 'date')
		{
		 return new Date(a[prop]).getTime() - new Date(b[prop]).getTime();
		}
		else
		{
		return (a[prop] > b[prop])
		}
		
}
else
{
if(type === 'number')
		{
		return a[prop] - b[prop];
		}
		else if(type === 'date')
		{
		 return new Date(b[prop]).getTime() - new Date(a[prop]).getTime();
		}
		else
		{
		return (b[prop] > a[prop])
		}
}
		
    });
	$(this).jshowData(options,jsr.currentPage)
	
		},
		
		
        });// Extends end
		
		
		
		
	
		/* Tablet orientation function */
			$.fn.tabletPortrait = function (options) {
					var tp = $.extend({}, options);
					var height = jQuery(window).height();
					var width = jQuery(window).width();
					if ( jQuery(window).width() > tp.windowWidth) {
						return orientation = ((width>height) ? 0 : 1);
					
						}
						else
						{
						return orientation = 2;
						}
				}
		
		$.fn.getScroll = function(options)
		{
		var gs = $.extend({}, options);
		var that = $(this);
		var mLeft = $('.staticCol',that);
		var clone = mLeft.clone();
		clone.css("visibility","hidden");
		 $('body').append(clone);
		var width = clone.outerWidth();
		clone.remove();
		var orient = $(this).tabletPortrait(options);
		if(orient === 0){
		$('.'+gs.tableContainerCls).removeAttr('style');
		}
		else
		{
		$('.'+gs.tableContainerCls,that).css('margin-left',width-1);
		
		}
		
		$.fn.doResizing = function(options)
		{
		var allTable = $('.responsivetbl');
		allTable.each(function(){
		$(this).getScroll(options);
		});
		}
		
		
		}
		$.fn.alernative = function(options)
		{
		var alt = $.extend({}, options);
		var that = $(this);
		var staticCol = $('.staticCol',that);
		var totTh = $('.'+alt.tableContainerCls+' table th',that);
		var staTh = $('th',staticCol);
		var sort = $('th[data-asc="A"]');
		var satSort = $('th[data-asc="A"]',staticCol);
		var ind = totTh.index(sort);
		var staInd = staTh.index(satSort);
if(ind >= 0 || staInd >=0 ){
	if(ind === -1) ind = staInd;
		$('.'+alt.tableContainerCls+' table > tbody tr',that).each(function(){
			var stCol = $(this).find("td").eq(ind).toggleClass('sorted');
		});
		if(staInd != -1){
		$('tbody tr',staticCol).each(function(){
			var stCol = $(this).find("td").eq(staInd).toggleClass('sorted');
		});
		}
	}	
		}

		
		
		

		
    })(jQuery);
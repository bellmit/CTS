<div id='wrapper'>
<div id="inner-Container">
            <!-- Inner Content
               ================================================== -->
            <div class='innerContent claimDetails'>
				<div class="xs-white">
				   <ul class="breadcrumb arrowBreadCrumb">
					  <li><a href="#">Home</a></li>
					  <li><a href="members_landing.html">Summary of care</a></li>
					  <li class="active activeArrowBreadCrumb">Display Claims</li>
				   </ul>
				   <div class="h1TextCon claims">
					  <!--<span>Claim Detail</span>-->
					  <h1>Display Claims</h1>
					  <span>Search claims submitted on your behalf</span>
					  <a href="#" data-toggle="modal" data-target="#myModal">Advanced Search</a>
				   </div>
			   </div>
               <div class="display-claims-search hidden-xs hidden-sm shadow-btm">
                 <form id="claimSearch" name="claimSearch"> 
                  <div class="col-xs-6 col-md-3 col-sm-4 form-group">
                     <label  for="rg-from">From Date </label>
					 <em class="help-icon" data-toggle="tooltip" data-placement="right" title="Tooltip"></em>
                     <div class='input-group date' id='datetimepicker5'>
                        <input type='text' id= "strFromDate" name="strFromDate" class="form-control" />
                        <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span>
                        </span>
                     </div>
                  </div>
                  <div class="col-xs-6 col-md-3 col-sm-4 form-group">
                     <label  for="rg-from">To Date </label>
					 <em class="help-icon" data-toggle="tooltip" data-placement="right" title="Tooltip"></em>
                     <div class='input-group date' id='datetimepicker6'>
                        <input type='text' id="strToDate" name="strToDate" class="form-control" />
                        <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span>
                        </span>
                     </div>
                  </div>
                  <div class="col-xs-6 col-md-3 col-sm-4 form-group">
                     <label  for="name">Claim Type </label>
                  	 <select class="selectpicker span8" id="claimType" name="claimType">                       
                     </select>
				  </div>
                  <div class="col-xs-6 col-md-3 col-sm-3 form-group">
                     <input type="submit" value=" Search " id="searchButton" class="btn xbtn-primary submit searchButton showGrid">
                  </div>
                  </form>
               </div>
			   <div class="searchtabs hideGrid">
				  <h2>Search Results</h2>
				  <p id="message" class="inl-block">Claims </p>
				  
				  <div class="visible-lg visible-md grid-icons pull-right">
					<i class="glyphicon glyphicon-share-alt"></i>
					 <i class="glyphicon glyphicon-print"></i>
				 </div>
			   </div>
			   <!-- tabs -->
			   <div class="visible-xs visible-sm">
				   <ul class="nav nav-tabs tab-new">
						<li class="active"><a href="#tabViewAll" data-toggle="tab">View All</a></li>
						<li><a href="#tabDate" data-toggle="tab">Date</a></li>
						<li><a href="#tabClaimType" data-toggle="tab">Claim Type</a></li>
				   </ul>
				   <div class="tab-content tab-new">
						<div class="tab-pane active" id="tabViewAll"></div>
						<div class="tab-pane" id="tabDate">content3</div>
						<div class="tab-pane grey-back" id="tabClaimType">
							<h3>Claim Type</h3>
							<div class="row">
								<label class="ui-check checkbox-inline col-xs-6"><input type="checkbox" id="selectAll"> <span>All</span> </label>
								<label class="ui-check checkbox-inline col-xs-6"><input type="checkbox"> <span>Dental</span> </label>
								<label class="ui-check checkbox-inline col-xs-6"><input type="checkbox"> <span>Medical</span> </label>
								<label class="ui-check checkbox-inline col-xs-6"><input type="checkbox"> <span>Pharmacy</span> </label>
							</div>
							<div class="text-right">
								<input type="submit"  class="btn xbtn-primary showGrid" value=" Done ">
							</div>
						</div>
				   </div>
			   </div>
			   
               <!-- Grid -->
               <div id='claimResults' class='responsivetbl hidetwocols hideGrid'>
                  <div class='tblcontainer'>
                     <table class="table table-bordered table-striped">
                        <thead>
                        </thead>
                        <tbody>
                        </tbody>
                     </table>
                  </div>
				  <div class="tfooter clearfix"></div>
               </div>			   
         </div>
       </div>
      </div>  
		<!-- Modal --> 
        <div class="modal fade modal-popup" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
         <div class="modal-dialog">
            <div class="modal-content">
               <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                  <h4 class="modal-title" id="myModalLabel">Display Claims</h4>
                  <h6>Advanced Search</h6>
               </div>
               <div class="modal-body">
               <form id="advanceClaimSearch" name="advanceClaimSearch">
                  <div class="form-inputs-con">
                     <div class="row">
                        <div class="col-md-12">
                           <h3> Provider Information </h3>
                        </div>
                     </div>
                     <div class="row">
                        <div class="col-md-6">
                           <label> First Name </label>
                           <input type="text" id="firstName" name="firstName" class="form-control search"/>
                        </div>
                        <div class="col-md-6"> 
                           <label> Last Name</label>
                           <input type="text" id="lastName" name="lastName" class="form-control search"/>
                        </div>
                     </div>
                     <div class="row">
                        <div class="col-md-6">
                           <label  for="rg-from">From Date </label>
						   <em class="help-icon" data-toggle="tooltip" data-placement="right" title="Tooltip"></em>
                           <div class='input-group date' id='datetimepickerp1'>
                              <input type='text' id= "strFromDate" name="strFromDate" class="form-control" />
                              <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span>
                              </span>
                           </div>
                        </div>
                        <div class="col-md-6">
                           <label  for="rg-from">To Date </label>
						   <em class="help-icon" data-toggle="tooltip" data-placement="right" title="Tooltip"></em>
                           <div class='input-group date' id='datetimepickerp2'>
                              <input type='text' id="strToDate" name="strToDate" class="form-control" />
                              <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span>
                              </span>
                           </div>
                        </div>
                     </div>
                     <div class="row">
                        <div class="col-md-6">
                           <label  for="name">Claim Type </label> <br />
                            <select class="selectpicker" id="claimType_dialog" name="claimType_dialog">                     
                           </select>
                        </div>
                     </div>
					</div>
                  <div class="row">
                     <div class="col-md-12">
						<div class="text-right actionBtn">
                        <input type="submit" value=" Search " id="advancedSearch" class="btn xbtn-primary submit searchButton showGrid">
						</div>
                     </div>
                  </div></form>
               </div>
            </div>
         </div>
      </div>
     <script src="js/app/member/member.js"></script>    		 
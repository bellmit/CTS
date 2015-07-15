	
<!-- Content Section
   ================================================== -->
<div id="inner-Container">
   <!-- Inner Content
      ================================================== -->
   <div class='innerContent'>
      <div class='claimDetails'>
         <div class="xs-white">
            <ul class="breadcrumb arrowBreadCrumb">
               <li><a href="memberEligibility">Home</a></li>
               <li><a href="memberEligibility">Provider</a></li>
               <li class="active activeArrowBreadCrumb">Check Eligibility</li>
            </ul>
            <div class="h1TextCon">
               <!--<span>Claim Detail</span>-->
               <h1>Check Eligibility</h1>
            </div>
         </div>
         <div class="h2TextCon">
            <h2>Requestor</h2>
         </div>
         <div class="cDetail shadow-btm">
            <div class="col-xs-12 col-md-6">
               <dl class="dl-horizontal ud-dl-horizontal">
                  <dt>Provider ID</dt>
                  <dd>34567890</dd>
                  <dt>Provider Name</dt>
                  <dd>Webber, MD, Ernest F.</dd>
               </dl>
            </div>
            <div class="col-xs-12 col-md-6 claimDetailsSeparator">
               <dl class="dl-horizontal ud-dl-horizontal">
                  <dt>Payer ID</dt>
                  <dd>123456789</dd>
                  <dt>Payer Name</dt>
                  <dd>ND Medicaid</dd>
               </dl>
            </div>
         </div>
         <div class="h2TextCon">
            <h2>Member Information</h2>
         </div>
         <div class="display-claims-search shadow-btm">
         	<form id="membersearch" name="membersearch">
	            <div class="col-md-12">
	               <h3><strong>Inquiry Type 30 </strong></h3>
	            </div>
	            <div class="col-xs-6 col-md-2 col-sm-4 form-group">
	               <label for="rg-from">Member ID</label>
	               <input type="text" name="memberId" class="form-control">     
	            </div>
	            
	            <div class="col-xs-6 col-md-2 col-sm-4 form-group">
	               <label for="rg-from">Last Name</label>
	               <input type="text" name="lastName" class="form-control">
	            </div>
	            <div class="col-xs-6 col-md-2 col-sm-4 form-group">
	               <label for="rg-from">First Name</label>
	               <input type="text" name="firstName" class="form-control">
	            </div>
	            <div class="col-xs-6 col-md-2 col-sm-4 form-group">
	               <label for="name">Claim Type </label>
	               <select class="selectpicker span8" name="claimType" style="display: none;">
						<option value="dental">Dental</option>
						<option value="Professional">Professional</option>
						<option value="Institutional">Institutional</option>
	               </select>
	               </div>
	               <div class="col-xs-6 col-md-2 col-sm-4 form-group">
	                  <label for="rg-from">Date Of Birth </label>
	                  <div data-date-format="YYYY/MM/DD" id="datetimepicker1" class="input-group date">
	                     <input type="text" class="form-control" name="dob">
	                     <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
	                     </span>
	                  </div>
	               </div>
	               <div class="col-xs-6 col-md-2 col-sm-4 form-group">
	                  <label for="rg-from">Service From </label>
	                  <div data-date-format="YYYY/MM/DD" id="datetimepicker2" class="input-group date">
	                     <input type="text" class="form-control" name="serviceFrom">
	                     <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
	                     </span>
	                  </div>
	               </div>
	               <div class="col-xs-6 col-md-2 col-sm-4 form-group">
	                  <label for="rg-from">Service To</label>
	                  <div data-date-format="YYYY/MM/DD" id="datetimepicker3" class="input-group date">
	                     <input type="text" class="form-control" name="serviceTo">
	                     <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
	                     </span>
	                  </div>
	               </div>
					<div class="col-md-12">
						<div class="pull-right">
							<input type="submit" class="btn xbtn-primary submit" value="Search">
							<input type="reset" class="btn xbtn-primary" value="Reset ">						
						</div>
					</div>
				</form>
            </div>
         </div>
         <div class="h2TextCon-G">
            <h2>Search Results</h2>
         </div>
         <!-- Grid -->
      <div id='recentClaims' class='responsivetbl'>
         <div class='tblcontainer'>
            <table class="table table-bordered table-striped">
               <thead>
               </thead>
               <tbody>
               </tbody>
            </table>
         </div>
      </div>
   </div>
</div>

<script type="text/javascript" src="js/app/provider/memberEligibility.js"></script>



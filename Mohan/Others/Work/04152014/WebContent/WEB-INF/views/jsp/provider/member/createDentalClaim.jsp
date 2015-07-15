<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id='wrapper'> 
         <!-- Content Section
            ================================================== -->
         <div id="inner-Container">
            <!-- Inner Content
               ================================================== -->
            <div class='innerContent claimDetails' >
				<div class="xs-white">
				   <ul class="breadcrumb arrowBreadCrumb">
					  <li><a href="#">Home</a></li>
					  <li><a href="memberLandingPage">Summary of care</a></li>
					  <li class="active activeArrowBreadCrumb">Display Claims</li>
				   </ul>
				   <div class="h1TextCon">
					  <!--<span>Claim Detail</span>-->
					  <h1>Create Dental Claim</h1>
					  <a href="#" data-toggle="modal" data-target="#myModal">Advanced Search</a>
				   </div>
			   </div>
               <div class="display-claims-search hidden-xs shadow-btm">
               <c:if test="${message ne null}">
              <h3><b>${message}</b></h3>
              </c:if>
               <form action="saveClaim" method="post">              
                  <div class="col-xs-6 col-md-3 col-sm-4 form-group">
                     <label  for="rg-from">Medicaid Provider ID </label>
                     <input type="text" id= "medicalProviderID" name="medicalProviderID" class="form-control" required="required"/>							
				  </div>             
                  <div class="col-xs-6 col-md-3 col-sm-4 form-group">
                     <label  for="rg-from">Last Name </label>
                     <input type="text" id= "lastName" name="lastName" class="form-control" required="required"/>							
				  </div>
				 <div class="col-xs-6 col-md-3 col-sm-4 form-group">
                     <label  for="rg-from">Service Date</label>
                     <div class='input-group date' id='datetimepicker5' data-date-format="YYYY/MM/DD">
                        <input type="text" id= "strServiceDate" name="strServiceDate" class="form-control" required="required"/>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span>
                        </span>
                     </div>
                     
                  </div>				                 
                  <div class="col-xs-6 col-md-3 col-sm-3 form-group">
                     <input type="submit" value="Save Claim" class="btn xbtn-primary searchButton">
                  </div>
               </form>
               </div>            
              </div>
         </div>
      </div>     
     <script src="js/app/member/member.js"></script>
     
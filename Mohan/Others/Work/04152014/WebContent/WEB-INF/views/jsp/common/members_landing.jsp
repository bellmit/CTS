<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import=" com.xerox.ghs.mmis.enterprise.common.security.common.model.User"%>
<html lang="en">
   <head>
      <title>Xerox</title>
      <meta name="description" content="Health Care" />
      <meta name="keywords" content="HealthCare" />
      <meta name="location" content="us" />
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">         
   </head>
   <body >
      
      <div id='wrapper'>        
         <!-- Content Section
            ================================================== -->
         <div id="inner-Container">
            <!-- Inner Content
               ================================================== -->
            <div class='innerContent'>
               <!-- Member Landing 
                  =================================================== -->
               <div class='members-landing'>
                  <!-- Members Landing 
                     =================================================== -->
                  <div class="xs-white">
                     <h1>Welcome <strong><c:out value="${sessionScope.userLoginseesion}"/></strong></h1>
                     <p>This is your member home page area. It provides you with several functions to access information.</p>
                  </div>
                  <div class="my-recent-claims">
                     <h2>My Recent claims</h2>
                     <div class="pull-right">
                        <a href="displayClaims">View All  »</a>
                     </div>
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
                  <div class="hidden-xs row help-info-con">
                     <div class="help-info shadow-btm">
                        <i class="close-help-info" onclick="$(this).parent().parent().hide()"></i>
                        <div class='col-sm-4'>
                           <h3>Find a Healthcare Provider</h3>
                           <p>Search for a provider based on certain criteria. This will assist you locate a provider specific to a specialty or a location based on your needs.</p>
                        </div>
                        <div class='col-sm-4 brd'>
                           <h3>View Doctors/ Providers visited</h3>
                           <p>Search for doctors/providers you have visited in the past. You can search by name or date of visit.</p>
                        </div>
                        <div class='col-sm-4 brd'>
                           <h3>View Claims</h3>
                           <p>Search for claims that have been made on your behalf. You can search by date, or the provider information.</p>
                        </div>
                     </div>
                  </div>
                  <!-- Quick links -->
                  <div class='hidden-lg hidden-md quick-links'>
                     <h3>Quick links</h3>
                  </div>
               </div>
            </div>
            
         </div>
      </div>
      <script src="js/app/member/member.js"></script>
   </body>
  
</html>
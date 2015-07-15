<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id='wrapper'> 
 
    <!-- Content Section
================================================== -->
  <div id="inner-Container"> 
    <!-- Inner Content
    ================================================== -->
    <div class='innerContent'>
	<div class='claimDetails'>
		<div class="xs-white">
		  <ul class="breadcrumb arrowBreadCrumb">
			<li><a href="#">Home</a></li>
			<li><a href="memberLandingPage">Summary of care</a></li>
			<li><a href="displayClaims">Display Claims</a></li>
			<li class="active activeArrowBreadCrumb">Claim Details</li>
		  </ul> 
		  <div class="h1TextCon">
			<h1>Claim Detail</h1>
			<em class="help-icon"></em>
			<a href="displayClaims">Return to Display Claims</a>
		  </div>
	    </div>
      <div class="cDetail shadow-btm">
      <c:choose>
      <c:when test="${claimDtlsObj ne null}">
      <c:forEach items="${claimDtlsObj}" var="claimDetails">
        <div class="col-xs-12 col-md-6">

            <dl class="dl-horizontal ud-dl-horizontal">
              <dt>Member Name</dt><dd>${claimDetails.firstName} ${claimDetails.lastName}</dd>
              <dt>Provider</dt><dd>${claimDetails.providerName}</dd>
              <dt>Claim Number</dt><dd>${claimDetails.claimNumber}</dd>
              <dt>Status</dt><dd>${claimDetails.claimStatus}</dd>
            </dl>
          </div>
          <div class="col-xs-12 col-md-6 claimDetailsSeparator">

            <dl class="dl-horizontal ud-dl-horizontal">
              <dt>Begin Date</dt><dd><fmt:formatDate pattern="MM/dd/yyyy" 
            value="${claimDetails.beginDate}" /></dd>
              <dt>End Date</dt><dd><fmt:formatDate pattern="MM/dd/yyyy" 
            value="${claimDetails.endDate}" /></dd>
              <dt>Claim Paid on</dt><dd>${claimDetails.strClaimsPaidDate}</dd>
              <dt>Charges Submitted</dt><dd>${claimDetails.totalSubmittedCharges}</dd>
            </dl>
          </div>
          </c:forEach>
          </c:when>
          <c:otherwise>
          <h3>${nosearch}</h3>
          </c:otherwise>
          </c:choose>
        </div>
		<div class="my-recent-claims">
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
		
		<!-- Quick links -->
		<div class='hidden-lg hidden-md quick-links'>
            <h3>Quick links</h3>
          </div>
		  </div>
    </div>
     
  </div>

  </div>
 <script src="js/app/member/member.js"></script>
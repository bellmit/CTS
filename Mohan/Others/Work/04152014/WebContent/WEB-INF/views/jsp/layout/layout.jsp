<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>
			<tiles:insertAttribute name="title" />
		</title>
		<meta name="description" content="Health Care" />
		<meta name="keywords" content="HealthCare" />
		<meta name="location" content="us" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

		<tiles:insertAttribute name="styles" />
		<tiles:insertAttribute name="scripts" /> 

	</head>
	<body>
		<div id='wrapper'> 
			
			 <!-- Header Section -->
			<tiles:insertAttribute name="header" />
			
	 		<!-- Navigation Section -->
			<tiles:insertAttribute name="navigation" />  
	  
			<!-- Content Section -->
	  		<tiles:insertAttribute name="content" />
	    
			<!-- Footer Section -->
			<tiles:insertAttribute name="footer" />
		</div>
	</body>
</html>


<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>
			<tiles:insertAttribute name="title" />
		</title>
		<meta name="description" content="My Spring" />
		<meta name="keywords" content="Spring" />
		<meta name="location" content="us" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

		<tiles:insertAttribute name="styles" />
		<tiles:insertAttribute name="scripts"/>
	 
	
	</head>
	<body bgcolor="grey">
		<div id='wrapper'> 
			<table id="tlayout">
				<tr><td colspan="2" class="header">
					<!-- Header Section -->
					<tiles:insertAttribute name="header" />
				</td></tr>
				
				<tr>
					<td class="menu">
						<!-- Navigation Section -->
						<tiles:insertAttribute name="menu" />  
					</td>
					<td class="content">
						<!-- Content Section -->
	  					<tiles:insertAttribute name="content" />
					</td>
				</tr>
				<tr>
					<td colspan="2" class="footer">
						<!-- Footer Section -->
						<tiles:insertAttribute name="footer" />
					</td>
				</tr>
			</table>
			
		</div>
	</body>
</html>


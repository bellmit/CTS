<link rel="stylesheet" href="css/login.css" />
<div style="background-color: gray;" align="left">
<div>
	<h3>${login }</h3>
</div>
<!-- Login 
	=================================================== -->
<div >
	<h2>Log In</h2>
	<form action="loginResponse" method="post">
		<div class="error">
			<label for="">${error}</label>
			
		</div>
	
		<div class="form-group">
			<label for="userIdMemberId">User Id / Member Id</label><em
				class='help-icon'></em> <input type="text"
				class="form-control xinput" id="userIdMemberId" name="userId"
				required="required" />
		</div>
		<div class="form-group">
			<label for="userPswd">Password</label><em class='help-icon'></em> <input
				type="password" class="form-control xinput" id="userPswd"
				name="userPassword" required="required" />
		</div>
		<div class="addition-login-links">
			 
			<input type="submit" class="btn xbtn-primary pull-right" value="Log In  ?" />
		</div>
		
	</form>
</div>
</div>
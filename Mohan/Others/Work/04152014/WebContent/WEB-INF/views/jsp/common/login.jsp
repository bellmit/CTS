<link rel="stylesheet" href="css/login.css"/>
  <div id="inner-Container"> 
    <!-- Inner Content
    ================================================== -->
    <div class='innerContent'> 
      <!-- Carousel 
	=================================================== -->
      <div class='container-fluid hcontainer-lgScreen'>
        <div class="container-carousel">
          <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
              <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
              <li data-target="#carousel-example-generic" data-slide-to="1" class=""></li>
              <li data-target="#carousel-example-generic" data-slide-to="2" class=""></li>
            </ol>
            <div class="carousel-inner">
              <div class="item banner-1 active">
                <div class="slide-text">
                  <h3>Welcome to Xerox Montana&nbsp;Health&nbsp;Enterprise</h3>
                  <p>This system supports all of your Montana&nbsp;Health&nbsp;Care&nbsp;Programs and Decision&nbsp;Support needs.</p>
                </div>
                <img src="images/banner1.png"> 
				</div>
              <div class="item banner-2">
                <div class="slide-text">
                  <h3>Welcome to Xerox Montana&nbsp;Health&nbsp;Enterprise</h3>
                  <p>This system supports all of your Montana&nbsp;Health&nbsp;Care&nbsp;Programs and Decision&nbsp;Support needs.</p>
                </div>
                <img src="images/banner2.png"> </div>
              <div class="item banner-3">
                <div class="slide-text">
                  <h3>Welcome to Xerox Montana&nbsp;Health&nbsp;Enterprise</h3>
                  <p>This system supports all of your Montana&nbsp;Health&nbsp;Care&nbsp;Programs and Decision&nbsp;Support needs.</p>
                </div>
                <img src="images/banner3.png"> </div>
            </div>
          </div>
        </div>
        <!-- Login 
	=================================================== -->
        <div class="row">
          <div class='col-xs-12 col-sm-5 login_form'>
            <div class="login-div">
              <h2>Log In</h2>
               <form action="loginResponse" method="post">
                <div class="form-group">
                  <label for="userIdMemberId">User Id / Member Id</label><em class='help-icon'></em>
                  <input type="text" class="form-control xinput" id="userIdMemberId" name="userId" required="required" />
                </div>
                <div class="form-group">
                  <label for="userPswd">Password</label><em class='help-icon'></em>
                  <input type="password" class="form-control xinput" id="userPswd" name="userPassword" required="required"/>
                </div>
                <div class="addition-login-links">
                  <a href='#' class="btn-link">Forgot Password?</a>
                  <input type="submit" class="btn xbtn-primary pull-right" value="Log In  »" />
                </div>
                <div class="addition-login-links">
                  <label for="unlockAcc">For Member</label>
				   <a href='#' class="btn-link pull-right">Unlock Account</a>
                </div>
                <div class="addition-login-links">
                  <label for="registerAcc">For Provider</label>
                  <input type="button" class="btn xbtn-primary pull-right" id="registerAcc" value="Register  »"/>
                </div>
              </form>
            </div>
          </div>
          <div class='col-sm-7 quick-links'>
            <h3>Quick links</h3>
            <ul>
              <li><a href="#">» Information topics A-Z</a></li>
              <li><a href="#">» Find a Health Care Provider</a></li>
              <li><a href="#">» Benefits Overview</a></li>
              <li><a href="#">» Provider Enrollment</a></li>
              <li><a href="#">» Documents and Forms</a></li>
              <li><a href="#">» Report Fraud and Abuse</a></li>
              <li><a href="#">» Department of Health and Human Services</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    
  </div>
  
package com.xerox.ghs.mmis.enterprise.common.security.common.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.xerox.ghs.mmis.enterprise.common.security.common.controller.LoginController;
import com.xerox.ghs.mmis.enterprise.common.security.common.model.User;
/**
 * 
 * Test Class for LoginController
 *
 */
public class LoginControllerTest {
	
	LoginController loginController = new LoginController();
	
	User user = new User();
	User userForProvider = new User();

	/**
	 * set up method
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		user.setUserId("member");
		userForProvider.setUserId("provider");
		
	}

	/**
	 * Tests the login method
	 */
	@Test
	public void testLoginView() {
		ModelAndView mv = loginController.loginView();
		assertEquals("login",mv.getViewName());
	}

	

}

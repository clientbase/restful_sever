package hello;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountController {
	
	@Autowired
	InMemoryUserDetailsManager userService;
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/";
	}
	
	@RequestMapping("/changepassword")
	public String updatePassword(@RequestParam(value="oldpassword", required=false) String oldpassword,
			@RequestParam(value="newpassword", required=false) String newpassword){
			userService.changePassword("", newpassword);
			return "redirect:/weather";
	}
	
	@RequestMapping("/account")
	public String account(){
		return "changepassword";
	}
	
	@RequestMapping("/signup")
	public String createUser(
			@RequestParam(value="username", required=false) String username,
			@RequestParam(value="password", required=false) String password,
			@RequestParam(value="passwordconfirm", required=false) String passwordconfirm){
		System.out.println("Creating new user: " + username + " and password: " + password);
		userService.createUser(User.withUsername(username).password(password).roles("USER").build());
		return "redirect:/login";
	}
	
	@RequestMapping("/createaccount")
	public String createUser(){
		return "createaccount";
	}
	
}

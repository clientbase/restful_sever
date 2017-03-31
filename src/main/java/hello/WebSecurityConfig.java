package hello;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	
	public WebSecurityConfig(){
		manager.createUser(User.withUsername("user").password("password").roles("USER").build());
		manager.createUser(User.withUsername("foo").password("bar").roles("USER").build());
	}
	
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		return manager;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.permitAll();
	}
}

package com.pihotel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.pihotel.oauth2.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	/*
	 * Director, accountant, business, Receptionists, guest, member
	*/
	
//	@Autowired
//	private AccountServ accountServ;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private CustomOAuth2UserService customerOAuth2UserService;
	
	@Autowired
	private CustomOAuth2LoginSuccessHandler customOAuth2LoginSuccessHandler;
	
	@Autowired
	private MyCustomLogoutSuccessHandler myCustomLogoutSuccessHandler;
	
	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new MyCustomLoginSuccessHandler("/home");
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepo() {
		InMemoryTokenRepositoryImpl tokenRepositoryImpl = new InMemoryTokenRepositoryImpl();
		return tokenRepositoryImpl;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable();

//		access control
		http.authorizeRequests().antMatchers("/", "/login**", "/logout**", "/home**", "/register**", "/oauth2/**", "/error**").permitAll();
		
//		http.authorizeRequests().antMatchers("/api", "/admin**")
//			.hasAnyAuthority("ROLE_DIRECTOR", "ROLE_ACCOUNTANT", "ROLE_BUSINESS", "ROLE_RECEPTIONIST");
		
//		http.authorizeRequests().antMatchers("/api", "/admin**")
//		.access("hasAnyRole('ROLE_DIRECTOR', 'ROLE_ACCOUNTANT', 'ROLE_BUSINESS', 'ROLE_RECEPTIONIST')");
		
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/error/403");
		
//		authentication local
		http.authorizeRequests().and().formLogin().loginPage("/login")
		.usernameParameter("username")
		.passwordParameter("password")
		.loginProcessingUrl("/handle_login")
		.successHandler(successHandler())
		.failureUrl("/login?error=True");
		
//		authentication oauth2
		http.authorizeRequests().and().oauth2Login().loginPage("/login")
		.userInfoEndpoint().userService(customerOAuth2UserService)
		.and().successHandler(customOAuth2LoginSuccessHandler);
		
//		logout
		http.authorizeRequests().and().logout()
		.logoutSuccessHandler(myCustomLogoutSuccessHandler).logoutSuccessUrl("/home").permitAll();
		http.authorizeRequests().and().logout()
		.deleteCookies("JSESSIONID", "remember-me");
		
//		remember-me config
		http.authorizeRequests().and().rememberMe().rememberMeParameter("remember-me")
		.key("uniqueAndSecret").tokenRepository(persistentTokenRepo())
		.tokenValiditySeconds(24 * 60 * 60);
	}
	
}

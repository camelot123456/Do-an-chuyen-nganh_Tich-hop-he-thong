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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.pihotel.oauth2.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
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
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new MyCustomLoginFailHandler();
	}
	
	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new MyCustomLoginSuccessHandler("/home");
	}
	
//	@Bean
//	public PersistentTokenRepository persistentTokenRepo() {
//		InMemoryTokenRepositoryImpl tokenRepositoryImpl = new InMemoryTokenRepositoryImpl();
//		return tokenRepositoryImpl;
//	} //lưu cookie vào db
	
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
		http.authorizeRequests().antMatchers("/", "/login**", "/logout**", "/home**", "/register**", "/oauth2/**", "/error**", "/seo/public**").permitAll();
		
//		http.authorizeRequests().antMatchers("/api", "/admin**")
//			.hasAnyAuthority("ROLE_DIRECTOR", "ROLE_ACCOUNTANT", "ROLE_BUSINESS", "ROLE_RECEPTIONIST");
		
		http.authorizeRequests().antMatchers("/cart**", "/cart/**", "/profile/**", "/profile**")
		.access("hasAnyRole('ROLE_MEMBER', 'ROLE_DIRECTOR', 'ROLE_ACCOUNTANT', 'ROLE_BUSINESS', 'ROLE_RECEPTIONIST')");
		
		http.authorizeRequests().antMatchers("/api", "/admin", "/admin/news/**", "/home/cart**")
		.access("hasAnyRole('ROLE_DIRECTOR', 'ROLE_ACCOUNTANT', 'ROLE_BUSINESS', 'ROLE_RECEPTIONIST')");
		
		http.authorizeRequests().antMatchers("/admin/internal-managements/account/**", "/admin/dashboard")
		.access("hasAnyRole('ROLE_DIRECTOR')");
		
		http.authorizeRequests().antMatchers("/admin/customer-managements/account-customer/**")
		.access("hasAnyRole('ROLE_DIRECTOR', 'ROLE_BUSINESS')");
		
		http.authorizeRequests().antMatchers("/admin/statistical-managements/statistical/**", "/admin/invoice-managements/invoice/**")
		.access("hasAnyRole('ROLE_DIRECTOR', 'ROLE_ACCOUNTANT')");
		
		http.authorizeRequests().antMatchers("/admin/room-managements/room/**")
		.access("hasAnyRole('ROLE_DIRECTOR', 'ROLE_BUSINESS', 'ROLE_RECEPTIONISTS')");
		
		http.authorizeRequests().antMatchers("/admin/invoice-managements/invoice/**")
		.access("hasAnyRole('ROLE_DIRECTOR', 'ROLE_ACCOUNTANT', 'ROLE_RECEPTIONISTS')");
		
		http.authorizeRequests().antMatchers("/admin/service-managements/service/**")
		.access("hasAnyRole('ROLE_DIRECTOR', 'ROLE_RECEPTIONISTS')");
		
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/error/403");
		
//		authentication local
		http.authorizeRequests().and().formLogin().loginPage("/login")
		.usernameParameter("username")
		.passwordParameter("password")
		.loginProcessingUrl("/handle_login")
		.successHandler(successHandler())
		.failureHandler(authenticationFailureHandler());
		
//		authentication oauth2
		http.authorizeRequests().and().oauth2Login().loginPage("/login")
		.userInfoEndpoint().userService(customerOAuth2UserService)
		.and().successHandler(customOAuth2LoginSuccessHandler);
		
//		logout
		http.authorizeRequests().and().logout()
		.logoutSuccessHandler(myCustomLogoutSuccessHandler).logoutSuccessUrl("/home");
		
		http.authorizeRequests().and().logout()
		.deleteCookies("JSESSIONID", "remember-me");
		
		
//		remember-me config
		http.authorizeRequests().and().rememberMe().rememberMeParameter("remember-me")
		.key("uniqueAndSecret")//.tokenRepository(persistentTokenRepo()) // lưu cookie vào db
		.tokenValiditySeconds(24 * 60 * 60);
	}
	
}

package com.pakhi.agile;

import javax.servlet.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@SpringBootApplication
@EnableOAuth2Client
public class AgileAppDeveloperApplication extends WebSecurityConfigurerAdapter {

	private static final Logger log = LoggerFactory.getLogger(AgileAppDeveloperApplication.class);
	
	@Autowired
	OAuth2ClientContext oAuth2ClientContext;
	
	public static void main(String[] args) {
		SpringApplication.run(AgileAppDeveloperApplication.class, args);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.antMatcher("/**")
				.authorizeRequests()
			.antMatchers("/", "/register")
				.permitAll()
			.anyRequest()
				.authenticated()
			.and()
				.logout().logoutSuccessUrl("/").permitAll()
			.and().exceptionHandling()
			      .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
			.and()
				.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
	}
	
	private Filter ssoFilter() {
		OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter("/login/google");
		OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(google(), oAuth2ClientContext);
		filter.setRestTemplate(oAuth2RestTemplate);
		UserInfoTokenServices userInfoTokenServices = new UserInfoTokenServices(googleResource().getUserInfoUri(), google().getClientId());
		userInfoTokenServices.setRestTemplate(oAuth2RestTemplate);
		filter.setTokenServices(userInfoTokenServices);
		return filter;
	}
	
	@Bean
	@ConfigurationProperties("google.client")
	public AuthorizationCodeResourceDetails google() {
		return new AuthorizationCodeResourceDetails();
	}
	
	@Bean
	@ConfigurationProperties("google.resource")
	public ResourceServerProperties googleResource() {
		return new ResourceServerProperties();
	}
	
	@Bean
	public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter oAuth2ClientContextFilter) {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(oAuth2ClientContextFilter);
		registrationBean.setOrder(-100);
		return registrationBean;
	}
}

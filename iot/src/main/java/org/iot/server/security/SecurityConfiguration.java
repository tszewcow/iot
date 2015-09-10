package org.iot.server.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iot.server.properties.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private RememberMeTokenRepository rememberMeTokenRepository;

    

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
    	http
//    		.httpBasic()
//    		.and()
			.authorizeRequests()
			.antMatchers("/beacon-status", "/beacons-statuses", "services/login")
			.permitAll()
			.anyRequest()
			.authenticated();
	    	
    	http
			.formLogin()
			.loginPage("/services/login")
			.failureUrl("/services/login")
			.loginProcessingUrl("/services/login")
			.permitAll()
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/services/logout"))
			.logoutSuccessUrl("/services/login")
			.permitAll();
    	
    	http
			.csrf()
			.disable();
//			.csrfTokenRepository(csrfTokenRepository())
//			.and()
//			.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);

    	
//		http
//			.rememberMe()//.authenticationSuccessHandler(new CustomSavedRequestAwareAuthenticationSuccessHandler())
//			.key("secretKey")
////			.authenticationSuccessHandler(authenticationSuccessHandler)
//			.tokenRepository(rememberMeTokenRepository)//persistentTokenRepository())
//			.tokenValiditySeconds(1230000);
    }

    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
    	 auth.userDetailsService(userLoginService).passwordEncoder(passwordEncoder());
	
    	 if(ApplicationProperties.isInitialized())
	    	 auth.
	    	 	inMemoryAuthentication().
	    	 	withUser(ApplicationProperties.getProperty("user.name")).
	    	 	password(ApplicationProperties.getProperty("user.password")).
	    	 	roles(ApplicationProperties.getProperty("user.role"));
    }
    

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    private CsrfTokenRepository csrfTokenRepository()
    {
    	HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
    	repository.setHeaderName("X-XSRF-TOKEN");
    	return repository;
	}


    @Bean
	public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler()
    {
    	SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
    	auth.setTargetUrlParameter("/");
    	auth.setAlwaysUseDefaultTargetUrl(true);
    	return auth;
	}
}
package org.iot.server.security;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    
//    private TokenAuthenticationService tokenAuthenticationService = null;//new TokenAuthenticationService(secret, userService)
    
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
    	http
			.authorizeRequests()
			.antMatchers()
			.permitAll()
			.anyRequest()
			.authenticated();
	    	
    	http
			.formLogin()
			.successHandler(savedRequestAwareAuthenticationSuccessHandler())
			.loginPage("/services/login")
			.failureUrl("/services/login")
			.permitAll()
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/services/logout"))
			.logoutSuccessUrl("/services/logout")
			.permitAll();
    	
    	http
			.csrf()
			.disable();
//			.csrfTokenRepository(csrfTokenRepository())
//			.and()
//			.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);

    	
		
		http
			.rememberMe().authenticationSuccessHandler(savedRequestAwareAuthenticationSuccessHandler())
			.tokenRepository(rememberMeTokenRepository)//persistentTokenRepository())
			.tokenValiditySeconds(123);
    	
		    	
    	//stateless
//    	if(tokenAuthenticationService == null)
//        	tokenAuthenticationService = new TokenAuthenticationService("tooManySecrets", userLoginService);
//    	
//    	
//    	 http
//         .exceptionHandling().and()
//         .anonymous().and()
//         .servletApi().and()
//         .headers().cacheControl().and()
//         .authorizeRequests()
//
//         // Allow anonymous resource requests
//         .antMatchers("/", "/services/auth/**", "/services/login", "/services/user").permitAll()
//         .antMatchers("/favicon.ico").permitAll()
//         .antMatchers("**/*.html").permitAll()
//         .antMatchers("**/*.css").permitAll()
//         .antMatchers("**/*.js").permitAll()
//
//         // Allow anonymous logins
//         .antMatchers("/services/auth/**", "/services/login", "/services/user").permitAll()
//
//         // All other request need to be authenticated
//         .anyRequest().authenticated().and()
//
//         // Custom Token based authentication based on the header previously given to the client
//         .addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService),
//                 UsernamePasswordAuthenticationFilter.class);
    	
    	
    	
//    	http 
//    		.httpBasic()
//    		.and()
//    		.authorizeRequests()
//            .antMatchers()
//            .permitAll()
//            .anyRequest()
//            .authenticated();
//    	    	
//		http
//			.formLogin()
//			.successHandler(savedRequestAwareAuthenticationSuccessHandler())
//			.loginPage("/main/login")
//			.failureUrl("/services/login?error")
//			.permitAll()
//			.and()
//			.logout()
//			.logoutRequestMatcher(new AntPathRequestMatcher("/services/logout"))
//			.logoutSuccessUrl("/services/logout")
//			.permitAll();
//
//	
     ////			.csrfTokenRepository(csrfTokenRepository())
////			.and()
////            .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
//		
//		
//		http
//			.rememberMe().authenticationSuccessHandler(savedRequestAwareAuthenticationSuccessHandler())
//			.tokenRepository(rememberMeTokenRepository)//persistentTokenRepository())
//			.tokenValiditySeconds(123);
    }

    
    
    //zxc 
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
 
//    @Bean
//    @Override
//    public UserService userDetailsService() {
//        return userService;
//    }
 
    
    //zxc
//    @Bean
//    public TokenAuthenticationService tokenAuthenticationService() {
//        if(tokenAuthenticationService == null)
//        	tokenAuthenticationService = new TokenAuthenticationService("tooManySecrets", userLoginService);
//        
//        return tokenAuthenticationService;
//    }
    
    
    
    
    
    
    
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




    ///zxc
    @Bean
	public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler()
    {
    	SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
    	auth.setTargetUrlParameter("targetUrl");
    	auth.setAlwaysUseDefaultTargetUrl(true);
    	return auth;
	}
    
//	@Bean
//	public PersistentTokenRepository persistentTokenRepository()
//	{
//		return rememberMeTokenRepository;
//	}
}
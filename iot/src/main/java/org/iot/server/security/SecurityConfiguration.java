package org.iot.server.security;

import org.iot.server.properties.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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


    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
    	http 
    		.httpBasic()
    		.and()
    		.authorizeRequests()
            .antMatchers()
            .permitAll()
            .anyRequest()
            .authenticated().and()
            .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
    	    	
		http
			.formLogin()
			.loginPage("/services/login")
			.failureUrl("/services/login?error")
			.permitAll()
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/services/logout"))
			.logoutSuccessUrl("/services/logout")
			.permitAll();

	
		http
			.csrf()
			.csrfTokenRepository(csrfTokenRepository());
    }

    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
    	 auth.userDetailsService(userLoginService).passwordEncoder(passwordEncoder());
	
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
}
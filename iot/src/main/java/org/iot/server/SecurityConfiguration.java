package org.iot.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Autowired
    private UserLoginService userLoginService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests().anyRequest().fullyAuthenticated().and().
        httpBasic().and().
        csrf().disable();
    	
//    	http
//    	.httpBasic().and()
//    	.authorizeRequests().anyRequest().authenticated();
    }

    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
    	 auth
         .userDetailsService(userLoginService);
//         .passwordEncoder(passwordEncoder()); //TODO zrobic!!!
//    	
    	 //tymczasowe rozwiazanie
    	 //jak ktos nie ma userow w bazie to loguje sie tymi danymi
    	 auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
//        auth.inMemoryAuthentication().withUser("admin1").password("password").roles("ADMIN");
    }
    
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
 
    //tez dziala
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception
//    {
//    	SecurityContextHolder.clearContext();
//    	
//    	auth.eraseCredentials(true);
//    	
//    	auth.inMemoryAuthentication().withUser("usera1").password("password1").roles("USER");
//    	auth.inMemoryAuthentication().withUser("userb1").password("password2").roles("USER");
//    	auth.inMemoryAuthentication().withUser("userc1").password("password3").roles("USER");
//    	
//    }    
}
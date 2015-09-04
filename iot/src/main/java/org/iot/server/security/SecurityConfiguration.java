package org.iot.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
            .authenticated();
    	
		http
			.formLogin()
			.loginPage("/services/login")
			.failureUrl("/services/login?error")
			.permitAll()
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/services/logout"))
			.logoutSuccessUrl("/services/login?logout")
			.permitAll();

	
		http
			.csrf()
			.disable();//bez tego nie da rady przslad danych do zapisu  TODO
   	 					  // mozna wczytac i wyswietlic, ale nie ma mozliwosci zapisu
    }

    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
    	 auth.userDetailsService(userLoginService);
//         .passwordEncoder(passwordEncoder()); //TODO zrobic!!!
//    	
    	
    	 //tymczasowe rozwiazanie
    	 //jak ktos nie ma userow w bazie to loguje sie tymi danymi
    	 auth.inMemoryAuthentication().withUser("q").password("q").roles("USER");//properties tomek dodac do repo
    }
    

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }   
}
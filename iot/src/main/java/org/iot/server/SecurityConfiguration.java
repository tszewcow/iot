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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Autowired
    private UserLoginService userLoginService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	
    	http
        .authorizeRequests()
            .antMatchers("/", "/home").permitAll()
            .anyRequest().authenticated();
//            .and()
//        http.formLogin()
//            .loginPage("/services/login/**")
//            .permitAll()
//            .and()
//        .logout()
//            .permitAll();
    	
    	 http.formLogin()
         .loginPage("/services/login").failureUrl("/services/login?error").permitAll()
         .and()
         .logout().logoutRequestMatcher(new AntPathRequestMatcher("/services/logout")).logoutSuccessUrl("/services/login?logout").permitAll();

    	http.csrf().disable();
    	
    	
    	//dziala
//    	http.authorizeRequests().anyRequest().fullyAuthenticated().and().
//        httpBasic().and().
//        csrf().disable();
    	
//    	http
//    	.httpBasic().and()
//    	.authorizeRequests().anyRequest().authenticated();
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
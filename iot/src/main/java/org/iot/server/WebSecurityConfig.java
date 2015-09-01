package org.iot.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http.authorizeRequests()
        .antMatchers("/services/getUser/**").permitAll()
        .antMatchers("/css/**").permitAll()
        .anyRequest().authenticated();

    	http.formLogin()
        .loginPage("/main/login").failureUrl("/main/login?error").permitAll()
        .and()
        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout").permitAll();

    	
//        http
//            .authorizeRequests()
//                .antMatchers("/services/getUser/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//            .formLogin()
//            .authenticated()//;
//            
//            
//                .loginPage("/services/beacons")
//                .permitAll()
//                .and()
//            .logout()
//                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }
}

package org.iot.server;

//import org.iot.server.document.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



//public class SecurityConfiguration{}

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {
//    	super.configure(http);
    	
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
         .userDetailsService(userDetailsService);
//         .passwordEncoder(passwordEncoder());
    	
//    	 auth
//         .userDetailsService(userDetailsService);
        // .passwordEncoder(passwordEncoder());
    	
//    	auth.inMemoryAuthentication().withUser("userx").password("password").roles("USER");
//        auth.inMemoryAuthentication().withUser("admin1").password("password").roles("ADMIN");
    }
    
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    
    @Bean
    protected UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            	System.out.println("loadUserByUsername " + username);
            	
                if(username != null && username.equals("test")) {
                	
                	System.out.println("==");
                	
                    return new User("test", "test", true, true, true, true,
                            AuthorityUtils.createAuthorityList("USER"));
                } else {
                	
                	System.out.println("error");
                	
                    throw new UsernameNotFoundException("could not find the user '"
                            + username + "'");
                }
            }
        };
    }
    
//    @Bean
//    protected UserDetailsService userDetailsService123() {
//        return new UserDetailsService() {
//
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//              //  User customer = new User();
//                
//            	System.out.println("loadUserByUsername " + username);
//            	
//                if(username == "test") {
//                    return new User("test", "test", true, true, true, true,
//                            AuthorityUtils.createAuthorityList("USER"));
//                } else {
//                    throw new UsernameNotFoundException("could not find the user '"
//                            + username + "'");
//                }
//            }
//        };
//    }
    
    
    
    
    
    
    
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
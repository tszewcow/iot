package org.iot.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.context.SecurityContextHolder;



//public class SecurityConfiguration{}
//
@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	super.configure(http);
//    	http.logout();
//      http.
//      formLogin().
//      loginPage("/main/login").
//  and().
//      logout().
//  and().
//      authorizeRequests().
//      antMatchers("/user**","/user/**", "user/**","user", "/user", "/user/",
//      		"/services/user/**"
//      		);
////      .
////      permitAll().
////      anyRequest().
////      authenticated().
////      
////  and().
////      csrf().
////      csrfTokenRepository(csrfTokenRepository()).
////  and().
////      addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
    	SecurityContextHolder.clearContext();
    	
    	auth.eraseCredentials(true);
    	
    	auth.inMemoryAuthentication().withUser("usera").password("password1").roles("USER");
    	auth.inMemoryAuthentication().withUser("userb").password("password2").roles("USER");
    	auth.inMemoryAuthentication().withUser("userc").password("password3").roles("USER");
    	
    	System.out.println("configure");
    	
//        JdbcUserDetailsManager userDetailsService = new JdbcUserDetailsManager();
//        userDetailsService.setDataSource(datasource);
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//
//        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
//        auth.jdbcAuthentication().dataSource(datasource);
//
//        if(!userDetailsService.userExists("user")) {
//            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//            authorities.add(new SimpleGrantedAuthority("USER"));
//            User userDetails = new User("user", encoder.encode("password"), authorities);
//
//            userDetailsService.createUser(userDetails);
//        }
    }
    
    
    
    
    
    
    
    
    
    
    
//  private Filter csrfHeaderFilter() {
//  return new OncePerRequestFilter() {
//      @Override
//      protected void doFilterInternal(HttpServletRequest request,
//                                      HttpServletResponse response, FilterChain filterChain)
//          throws ServletException, IOException {
//          CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
//              .getName());
//          if (csrf != null) {
//              Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
//              String token = csrf.getToken();
//              if (cookie == null || token != null
//                  && !token.equals(cookie.getValue())) {
//                  cookie = new Cookie("XSRF-TOKEN", token);
//                  cookie.setPath("/");
//                  response.addCookie(cookie);
//              }
//          }
//          filterChain.doFilter(request, response);
//      }
//  };
//}
//
//private CsrfTokenRepository csrfTokenRepository() {
//  HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
//  repository.setHeaderName("X-XSRF-TOKEN");
//  return repository;
//}
//
//@Override
//protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//  auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
//}
//}

    
}
package org.iot.server;

import java.util.List;

import org.iot.server.service.AutomaticMobileSetService;
import org.iot.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class UserLoginService implements UserDetailsService
{
	private UserService userDBService;

	@Autowired
	public UserLoginService(UserService userDBService) {
		this.userDBService = userDBService;
	}
	
	@Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		org.iot.server.document.User user = userDBService.getUser(userName);
		
		if(!StringUtils.isEmpty(user.getId()))
			return generateUser(user.getUserName(), user.getPassword());
		
		throw new UsernameNotFoundException("could not find the user '" + userName + "'");
    }

	//TODO mozliwe, ze trzeba bedzie to rozbudowac
	private User generateUser(String name, String pswd)
	{
		return new User(name, pswd, true, true, true, true, userAuthority("USER"));
	}
	
	//TODO mozliwe, ze trzeba bedzie to rozbudowac
	private List<GrantedAuthority> userAuthority(String role)
	{
		return AuthorityUtils.createAuthorityList("USER");
	}
}

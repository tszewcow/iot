package org.iot.server.security;

import java.util.List;

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
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		
		org.iot.server.document.User user = userDBService.getUser(userEmail);
		
		if(user != null && !StringUtils.isEmpty(user.getId()))
			return generateUser(user.getEmail(), user.getPassword(), user.isActive(), user.getUserRole());
		
		throw new UsernameNotFoundException("could not find the user '" + userEmail + "'");
    }

	//TODO mozliwa rozbudowa
	private User generateUser(String name, String pswd, boolean isActive, String role)
	{
		return new User(name, pswd, true, true, true, isActive, userAuthority(role));
	}

	//TODO mozliwa rozbudowa
	private List<GrantedAuthority> userAuthority(String role)
	{
		return AuthorityUtils.createAuthorityList(role);
	}
}

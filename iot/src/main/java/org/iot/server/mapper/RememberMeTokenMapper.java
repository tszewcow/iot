package org.iot.server.mapper;

import org.iot.server.document.RememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.stereotype.Component;

@Component
public class RememberMeTokenMapper {

	public RememberMeToken map2RememberMeToken(PersistentRememberMeToken token)
	{
		RememberMeToken result = new RememberMeToken();
		
		result.setDate(token.getDate());
		result.setSeries(token.getSeries());
		result.setTokenValue(token.getTokenValue());
		result.setUserName(token.getUsername());
		
		return result;
	}
	
	public PersistentRememberMeToken map2PersistentRememberMeToken(RememberMeToken token)
	{
		return new PersistentRememberMeToken(token.getUserName(), token.getSeries(), token.getTokenValue(), token.getDate());
	}
}
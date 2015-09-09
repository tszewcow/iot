package org.iot.server.security;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.iot.server.document.RememberMeToken;
import org.iot.server.mapper.RememberMeTokenMapper;
import org.iot.server.repository.RememberMeTokenMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class RememberMeTokenRepository implements PersistentTokenRepository {

	private final RememberMeTokenMongoRepository rememberMeTokenMongoRepository;
	private final RememberMeTokenMapper rememberMeTokenMapper;
	
	@Autowired
	public RememberMeTokenRepository(RememberMeTokenMongoRepository rememberMeTokenRepository, RememberMeTokenMapper rememberMeTokenMapper)
	{
		this.rememberMeTokenMongoRepository = rememberMeTokenRepository;
		this.rememberMeTokenMapper = rememberMeTokenMapper;
	}
	
	@Override
	public void createNewToken(PersistentRememberMeToken token)
	{	
		System.err.println("create");
		
		rememberMeTokenMongoRepository.save(rememberMeTokenMapper.map2RememberMeToken(token));
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		// TODO Auto-generated method stub
		System.err.println("update");
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId)
	{
		System.err.println("get");
		
		Predicate<RememberMeToken> tokenFilter = token -> token.getSeries().equals(seriesId);
		
		List<RememberMeToken> tokens = rememberMeTokenMongoRepository.findAll().stream().filter(tokenFilter).collect(Collectors.toList());
		
		if(tokens != null && tokens.size() == 1)
			return rememberMeTokenMapper.map2PersistentRememberMeToken(tokens.get(0));
		
		return rememberMeTokenMapper.map2PersistentRememberMeToken(new RememberMeToken());

	}

	@Override
	public void removeUserTokens(String username)
	{
		System.err.println("delete: " + username);
		
		Predicate<RememberMeToken> tokenFilter = token -> token.getUserName().equals(username);
		
		List<RememberMeToken> tokens = rememberMeTokenMongoRepository.findAll().stream().filter(tokenFilter).collect(Collectors.toList());
		
		if(tokens != null && tokens.size() == 1)
			rememberMeTokenMongoRepository.delete(tokens.get(0));
		
	}
}
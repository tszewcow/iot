package org.iot.server.rest;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AdditionalRestService {

	@RequestMapping("/services/user")
	public Principal checkIfUserIsAuthorized(Principal user) {
		return user;
	}
}

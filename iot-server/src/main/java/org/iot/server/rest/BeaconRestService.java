package org.iot.server.rest;

import java.util.List;

import org.iot.server.service.BeaconService;
import org.iot.server.to.BeaconTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeaconRestService {
	
	// TODO use constructor dependency injection
	
	@Autowired
	private BeaconService beaconService;
		
	@RequestMapping(value = "/beacons", method = RequestMethod.GET)
	public List<BeaconTo> getAllBeacons() {
		return beaconService.getAllBeacons();
	}

}

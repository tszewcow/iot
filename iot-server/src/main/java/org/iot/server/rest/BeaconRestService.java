package org.iot.server.rest;

import java.util.List;

import org.iot.server.service.BeaconService;
import org.iot.server.to.BeaconStatusTo;
import org.iot.server.to.BeaconTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeaconRestService {
	
	private final BeaconService beaconService;	
	
	@Autowired
	public BeaconRestService(BeaconService beaconService) {
		this.beaconService = beaconService;	
		}
	
	@RequestMapping(value = "/beacons", method = RequestMethod.GET)
	public List<BeaconTo> getAllBeacons() {
		return beaconService.getAllBeacons();
	}
	
	@RequestMapping(value = "/beacon-status", method = RequestMethod.POST)
	public void reportStatus(@RequestBody BeaconStatusTo beaconStatus) {
		beaconService.registerStatus(beaconStatus);
	}
	
	@RequestMapping(value = "/beacons-statuses", method = RequestMethod.GET)
	public List<BeaconStatusTo> getAllBeaconsStatuses() {
		return beaconService.getAllBeaconsStatuses();
	}
}

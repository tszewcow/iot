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
	private final RequestToBeaconStatusToConverter requestToBeaconStatusToConverter;

	@Autowired
	public BeaconRestService(BeaconService beaconService,
			RequestToBeaconStatusToConverter requestToBeaconStatusToConverter) {
		this.beaconService = beaconService;
		this.requestToBeaconStatusToConverter = requestToBeaconStatusToConverter;
	}

	@RequestMapping(value = "/services/beacons", method = RequestMethod.GET)
	public List<BeaconTo> getAllBeacons() {
		return beaconService.getAllBeacons();
	}

	@RequestMapping(value = "/beacon-status", method = RequestMethod.POST)
	public void reportStatus(@RequestBody String request) {
		BeaconStatusTo beaconStatusTo = requestToBeaconStatusToConverter.convert(request);
		beaconService.registerStatus(beaconStatusTo);
	}

	@RequestMapping(value = "/beacons-statuses", method = RequestMethod.GET)
	public List<BeaconStatusTo> getAllBeaconsStatuses() {
		return beaconService.getAllBeaconsStatuses();
	}

	@RequestMapping(value = "/services/beacon-add", method = RequestMethod.POST)
	public void addBeacon(@RequestBody BeaconTo request) {
		beaconService.addBeacon(request);
	}

}

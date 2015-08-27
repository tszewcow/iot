package org.iot.server.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.iot.server.service.BeaconService;
import org.iot.server.to.AutomaticMobileSetTo;
import org.iot.server.to.BeaconStatusTo;
import org.iot.server.to.BeaconTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value = "/services/beacons/{building}/{floor}", method = RequestMethod.GET)
	public List<BeaconTo> getBeaconsOnFloor(@PathVariable("building") String building, @PathVariable("floor") int floor) {
		return beaconService.getAllBeacons().stream().filter(beacon -> beacon.getBuilding().equals(building) && beacon.getFloor() == floor).collect(Collectors.toList());
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

	@RequestMapping(value = "/services/beacon", method = RequestMethod.POST)
	public BeaconTo addBeacon(@RequestBody BeaconTo request) {
		return beaconService.addBeacon(request);
	}

	@RequestMapping(value = "/services/beacon/{id}", method = RequestMethod.DELETE)
	public void deleteBeacon(@PathVariable("id") String id) {
		beaconService.deleteBeacon(id);
	}

	@RequestMapping(value = "/services/beacon", method = RequestMethod.PUT)
	public BeaconTo updateBeacon(@RequestBody BeaconTo request) {
		return beaconService.updateBeacon(request);
	}
}

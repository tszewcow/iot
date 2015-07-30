package org.iot.server.rest;

import java.util.List;
import java.util.regex.Pattern;

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
	public void reportStatus(@RequestBody Object request) {		

		String req =  (String) request;	
		String[] tokens = req.split("&");
		tokens[0] = tokens[0].substring(6, 11); 		//major
		tokens[1] = tokens[1].substring(5, 37); 		//uuid
		tokens[1] = tokens[1].replace("%3A", ":"); 
		tokens[2] = tokens[2].substring(4, 31); 		//mac
		tokens[2] = tokens[2].replace("%3A", ":");
		tokens[3] = tokens[3].substring(5, 8);			//rssi
		tokens[4] = tokens[4].substring(6, 11); 		//minor
		tokens[5] = tokens[5].substring(17, 20);		//measuredstrenght
	
		
		BeaconStatusTo beaconStatusTo = new BeaconStatusTo();
		
		beaconStatusTo.setMac(tokens[2]);
		beaconStatusTo.setMajor(Integer.parseInt(tokens[0]));
		beaconStatusTo.setMinor(Integer.parseInt(tokens[4]));
		beaconStatusTo.setUuid(tokens[1]);
		beaconStatusTo.setRssi(Integer.parseInt(tokens[3]));
		beaconStatusTo.setMeasuredStrenght(Integer.parseInt(tokens[5]));
		
		beaconService.registerStatus(beaconStatusTo);
	}
	
//	application/x-www-form-urlencoded
//	@RequestMapping(value = "/beacon-status", method = RequestMethod.POST, headers="Accept=*")
//	public void reportStatus(@RequestBody Object requestBody, ServletRequest request) {
//		BeaconStatusTo beaconStatus = new BeaconStatusTo();		
//		beaconService.registerStatus(beaconStatus);
//	}
	
	@RequestMapping(value = "/beacons-statuses", method = RequestMethod.GET)
	public List<BeaconStatusTo> getAllBeaconsStatuses() {
		return beaconService.getAllBeaconsStatuses();
	}
}

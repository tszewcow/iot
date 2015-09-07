package org.iot.server.rest;

import org.iot.server.to.BeaconStatusTo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
class RequestToBeaconStatusToConverter {

	public BeaconStatusTo convert(String requestString) {

		if (StringUtils.isEmpty(requestString)) {
			throw new IllegalArgumentException("Request string should neither null nor empty.");
		}

		String[] tokens = requestString.split("&");
		tokens[0] = tokens[0].substring(7, 12); // major
		tokens[1] = tokens[1].substring(5, 37); // uuid
		tokens[2] = tokens[2].substring(22, 49); // macAMS
		tokens[2] = tokens[2].replace("%3A", ":");
		tokens[3] = tokens[3].substring(4, 31); // macBeacon,
		tokens[3] = tokens[3].replace("%3A", ":");
		tokens[4] = tokens[4].substring(5, 8); // rssi
		tokens[5] = tokens[5].substring(6, 9); // minor
		tokens[6] = tokens[6].substring(17, 20); // measuredstrenght

		double measuredStrenght = Double.parseDouble(tokens[6]);
		double rssi = Double.parseDouble(tokens[4]);

		double distance = calculateDistance(measuredStrenght, rssi);

		BeaconStatusTo beaconStatusTo = new BeaconStatusTo();
		beaconStatusTo.setMac(tokens[3]);
		beaconStatusTo.setMajor(Integer.parseInt(tokens[0]));
		beaconStatusTo.setMinor(tokens[5]);
		beaconStatusTo.setUuid(tokens[1]);
		beaconStatusTo.setRssi(Integer.parseInt(tokens[4]));
		beaconStatusTo.setMeasuredStrenght(Integer.parseInt(tokens[6]));
		beaconStatusTo.setMacAutomaticMobileSet(tokens[2]);
		beaconStatusTo.setDistance(distance);

		return beaconStatusTo;

	}

	private double calculateDistance(double measuredStrenght, double rssi) {
		return Math.pow(10d, (measuredStrenght - rssi) / (10 * 2));
	}

}

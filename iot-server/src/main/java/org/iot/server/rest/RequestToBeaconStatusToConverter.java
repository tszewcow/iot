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
		tokens[0] = tokens[0].substring(6, 11); // major
		tokens[1] = tokens[1].substring(5, 37); // uuid
		tokens[2] = tokens[2].substring(4, 21); // mac
		tokens[2] = tokens[2].replace("%3A", ":");
		tokens[3] = tokens[3].substring(5, 8); // rssi
		tokens[4] = tokens[4].substring(6, 9); // minor
		tokens[5] = tokens[5].substring(17, 20); // measuredstrenght
		tokens[6] = tokens[6].substring(22, 39); // macAMS

		double measuredStrenght = Double.parseDouble(tokens[5]);
		double rssi = Double.parseDouble(tokens[3]);

		double distance = calculateDistance(measuredStrenght, rssi);

		BeaconStatusTo beaconStatusTo = new BeaconStatusTo();
		beaconStatusTo.setMac(tokens[2]);
		beaconStatusTo.setMajor(Integer.parseInt(tokens[0]));
		beaconStatusTo.setMinor(Integer.parseInt(tokens[4]));
		beaconStatusTo.setUuid(tokens[1]);
		beaconStatusTo.setRssi(Integer.parseInt(tokens[3]));
		beaconStatusTo.setMeasuredStrenght(Integer.parseInt(tokens[5]));
		beaconStatusTo.setMacAutomaticMobileSet(tokens[6]);
		beaconStatusTo.setDistance(distance);

		return beaconStatusTo;

	}

	private double calculateDistance(double measuredStrenght, double rssi) {
		return Math.pow(10d, (measuredStrenght - rssi) / (10 * 2));
	}

}

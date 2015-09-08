package org.iot.server.rest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.iot.server.to.BeaconStatusTo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
class RequestToBeaconStatusToConverter {

	public BeaconStatusTo convert(String requestString) {

		if (StringUtils.isEmpty(requestString)) {
			throw new IllegalArgumentException("Request string should neither null nor empty.");
		}

		String regexPattern = "Major=(\\d+)&UUDI=(\\w+)&MAC=([\\w%]+)&macAutomaticMobileSet=([\\w%]+)&RSSI=(-?\\d+)&Minor=(\\d+)&MeasuredStrenght=(-?\\d+)";

		Matcher matcher = Pattern.compile(regexPattern).matcher(requestString);

		if (!matcher.find()) {
			throw new IllegalArgumentException("Request string doesn't match pattern");
		}

		String mac = matcher.group(3).replace("%3A", ":");
		String macAutomaticMobileSet = matcher.group(4).replace("%3A", ":");
		double rssi = Double.parseDouble(matcher.group(5));
		double measuredStrength = Double.parseDouble(matcher.group(7));
		double distance = calculateDistance(measuredStrength, rssi);

		BeaconStatusTo beaconStatusTo = new BeaconStatusTo();
		beaconStatusTo.setMajor(Integer.parseInt(matcher.group(1)));
		beaconStatusTo.setUuid(matcher.group(2));
		beaconStatusTo.setMinor(matcher.group(6));
		beaconStatusTo.setMac(mac);
		beaconStatusTo.setMacAutomaticMobileSet(macAutomaticMobileSet);
		beaconStatusTo.setRssi(Integer.parseInt(matcher.group(5)));
		beaconStatusTo.setMeasuredStrenght(Integer.parseInt(matcher.group(7)));
		beaconStatusTo.setDistance(distance);
		return beaconStatusTo;

	}

	private double calculateDistance(double measuredStrenght, double rssi) {
		return Math.pow(10d, (measuredStrenght - rssi) / (10 * 2));
	}

}

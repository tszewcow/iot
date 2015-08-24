package org.iot.server.to;

/**
 * It represents data sent from automatic mobile set collected from a single
 * beacon.
 *
 */
public class BeaconStatusTo {

	private int major;
	private String minor;
	private String uuid;
	private int rssi;
	private String mac;
	private int measuredStrenght;
	private double distance;
	private String macAutomaticMobileSet;

	public String getMacAutomaticMobileSet() {
		return macAutomaticMobileSet;
	}

	public void setMacAutomaticMobileSet(String macAutomaticMobileSet) {
		this.macAutomaticMobileSet = macAutomaticMobileSet;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public int getMeasuredStrenght() {
		return measuredStrenght;
	}

	public void setMeasuredStrenght(int measuredStrenght) {
		this.measuredStrenght = measuredStrenght;
	}

	public int getMajor() {
		return major;
	}

	public void setMajor(int major) {
		this.major = major;
	}

	public String getMinor() {
		return minor;
	}

	public void setMinor(String minor) {
		this.minor = minor;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getRssi() {
		return rssi;
	}

	public void setRssi(int rssi) {
		this.rssi = rssi;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
}

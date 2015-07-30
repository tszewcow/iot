package org.iot.server.document;

import org.springframework.data.annotation.Id;

public class BeaconStatus {

	@Id
	private String id;
	private String uuid;
	private int measuredStrenght;
	private String mac;
	private int rssi;
	private int minor;
	private double distance;

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	private int major;

	public int getMajor() {
		return major;
	}

	public void setMajor(int major) {
		this.major = major;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getMeasuredStrenght() {
		return measuredStrenght;
	}

	public void setMeasuredStrenght(int measuredStrenght) {
		this.measuredStrenght = measuredStrenght;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public int getRssi() {
		return rssi;
	}

	public void setRssi(int rssi) {
		this.rssi = rssi;
	}

	public int getMinor() {
		return minor;
	}

	public void setMinor(int minor) {
		this.minor = minor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}

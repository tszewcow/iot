package org.iot.server.document;

import org.springframework.data.annotation.Id;

public class BeaconStatus {

	@Id
	private String id;
	private String uuid;
	private int measuredStrenght;
	private String mac;
	private int rssi;
	private String minor;
	private int major;
	private String macAutomaticMobileSet;
	private double distance;

	public double getDistance() {
		return distance;
	}

	public String getMacAutomaticMobileSet() {
		return macAutomaticMobileSet;
	}

	public void setMacAutomaticMobileSet(String macAutomaticMobileSet) {
		this.macAutomaticMobileSet = macAutomaticMobileSet;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

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

	public String getMinor() {
		return minor;
	}

	public void setMinor(String minor) {
		this.minor = minor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString()
	{
		return new StringBuilder().append(id).append(", ").
								append(major).append(", ").
								append(minor).append(", ").
								append(uuid).append(", ").
								append(rssi).append(", ").
								append(mac).append(", ").
								append(measuredStrenght).append(", ").
								append(distance).append(", ").
								append(macAutomaticMobileSet).toString();
	}
}

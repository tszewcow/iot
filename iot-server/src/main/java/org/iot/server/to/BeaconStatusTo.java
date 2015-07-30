package org.iot.server.to;

public class BeaconStatusTo {
	
	private int major;
	private int minor;
	private String uuid;
	private int rssi;
	private String mac;
	private int measuredStrenght;

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
	public int getMinor() {
		return minor;
	}
	public void setMinor(int minor) {
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

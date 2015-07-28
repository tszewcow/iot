package org.iot.server.to;

public class BeaconStatusTo {
	
	private int major;
	private int minor;
	private String uuidNor;
	private String uuidSec;
	private String uuidSer;
	private int rssi;
	private int battery;

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
	public String getUuidNor() {
		return uuidNor;
	}
	public void setUuidNor(String uuidNor) {
		this.uuidNor = uuidNor;
	}
	public String getUuidSec() {
		return uuidSec;
	}
	public void setUuidSec(String uuidSec) {
		this.uuidSec = uuidSec;
	}
	public String getUuidSer() {
		return uuidSer;
	}
	public void setUuidSer(String uuidSer) {
		this.uuidSer = uuidSer;
	}
	public int getRssi() {
		return rssi;
	}
	public void setRssi(int rssi) {
		this.rssi = rssi;
	}
	public int getBattery() {
		return battery;
	}
	public void setBattery(int battery) {
		this.battery = battery;
	}	
}

package org.iot.server.document;

import org.springframework.data.annotation.Id;

public class Beacon {

	@Id
	private String id;
	private String name;
	private String mac;
	private String uuidNormal;
	private String uuidSecure;
	private String uuidService;
	private String building;
	private int floor;
	private double room;
	private float xBeacon;
	private float yBeacon;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getUuidNormal() {
		return uuidNormal;
	}

	public void setUuidNormal(String uuidNormal) {
		this.uuidNormal = uuidNormal;
	}

	public String getUuidSecure() {
		return uuidSecure;
	}

	public void setUuidSecure(String uuidSecure) {
		this.uuidSecure = uuidSecure;
	}

	public String getUuidService() {
		return uuidService;
	}

	public void setUuidService(String uuidService) {
		this.uuidService = uuidService;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public double getRoom() {
		return room;
	}

	public void setRoom(double room) {
		this.room = room;
	}

	public float getxBeacon() {
		return xBeacon;
	}

	public void setxBeacon(float xBeacon) {
		this.xBeacon = xBeacon;
	}

	public float getyBeacon() {
		return yBeacon;
	}

	public void setyBeacon(float yBeacon) {
		this.yBeacon = yBeacon;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}

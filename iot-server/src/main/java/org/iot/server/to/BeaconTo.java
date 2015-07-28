package org.iot.server.to;

public class BeaconTo {
	private String name;
	private String mac;
	private String uuidnor;
	private String uuidsec;
	private String uuidser;
	private String building;
	private String floor;
	private String room;
	private String coordinates;

	
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
	public String getUuidnor() {
		return uuidnor;
	}
	public void setUuidnor(String uuidnor) {
		this.uuidnor = uuidnor;
	}
	public String getUuidsec() {
		return uuidsec;
	}
	public void setUuidsec(String uuidsec) {
		this.uuidsec = uuidsec;
	}
	public String getUuidser() {
		return uuidser;
	}
	public void setUuidser(String uuidser) {
		this.uuidser = uuidser;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
}

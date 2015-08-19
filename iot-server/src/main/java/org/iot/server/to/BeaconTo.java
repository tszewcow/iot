package org.iot.server.to;

public class BeaconTo {
	private String name;
	private String mac;
	private String uuidnor;
	private String uuidsec;
	private String uuidser;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mac == null) ? 0 : mac.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BeaconTo other = (BeaconTo) obj;
		if (mac == null) {
			if (other.mac != null)
				return false;
		} else if (!mac.equals(other.mac))
			return false;
		return true;
	}

}

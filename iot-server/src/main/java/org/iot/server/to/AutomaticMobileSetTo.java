package org.iot.server.to;

public class AutomaticMobileSetTo {

	private String project;
	private String guardian;
	private String building;
	private int floor;
	private double room;
	private int xAutomaticMobileSet;
	private int yAutomaticMobileSet;
	private String macAutomaticMobileSet;
	private String position;

	// TODO maybe better int x, int y

	public String getMacAutomaticMobileSet() {
		return macAutomaticMobileSet;
	}

	public void setMacAutomaticMobileSet(String macAutomaticMobileSet) {
		this.macAutomaticMobileSet = macAutomaticMobileSet;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getGuardian() {
		return guardian;
	}

	public void setGuardian(String guardian) {
		this.guardian = guardian;
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

	public int getX() {
		return xAutomaticMobileSet;
	}

	public void setX(int x) {
		this.xAutomaticMobileSet = x;
	}

	public int getY() {
		return yAutomaticMobileSet;
	}

	public void setY(int y) {
		this.yAutomaticMobileSet = y;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}

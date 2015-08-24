package org.iot.server.to;

public class AutomaticMobileSetTo {

	private String id;
	private String project;
	private String guardian;
	private String building;
	private int floor;
	private double room;
	private float xAutomaticMobileSet;
	private float yAutomaticMobileSet;
	private String macAutomaticMobileSet;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public float getxAutomaticMobileSet() {
		return xAutomaticMobileSet;
	}

	public void setxAutomaticMobileSet(float x) {
		this.xAutomaticMobileSet = x;
	}

	public float getyAutomaticMobileSet() {
		return yAutomaticMobileSet;
	}

	public void setyAutomaticMobileSet(float y) {
		this.yAutomaticMobileSet = y;
	}
}

package org.iot.server.document;

import org.springframework.data.annotation.Id;

public class AutomaticMobileSet {

	@Id
	private String id;
	private String project;
	private String guardian;
	private String building;
	private int floor;
	private double room;
	private float xAutomaticMobileSet;
	private float yAutomaticMobileSet;
	private String macAutomaticMobileSet;
	private String position;

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

	public void setxAutomaticMobileSet(float xAutomaticMobileSet) {
		this.xAutomaticMobileSet = xAutomaticMobileSet;
	}

	public float getyAutomaticMobileSet() {
		return yAutomaticMobileSet;
	}

	public void setyAutomaticMobileSet(float yAutomaticMobileSet) {
		this.yAutomaticMobileSet = yAutomaticMobileSet;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}

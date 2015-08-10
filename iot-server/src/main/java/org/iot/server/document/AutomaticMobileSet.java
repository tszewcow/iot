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
	private int xAutomaticMobileSet;
	private int yAutomaticMobileSet;
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

	public int getxAutomaticMobileSet() {
		return xAutomaticMobileSet;
	}

	public void setxAutomaticMobileSet(int x) {
		this.xAutomaticMobileSet = x;
	}

	public int getyAutomaticMobileSet() {
		return yAutomaticMobileSet;
	}

	public void setyAutomaticMobileSet(int y) {
		this.yAutomaticMobileSet = y;
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

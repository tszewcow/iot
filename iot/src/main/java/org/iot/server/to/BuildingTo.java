package org.iot.server.to;

import java.util.List;

public class BuildingTo {

	private String buildingNumber;
	private List<Integer> floors;

	public String getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public List<Integer> getFloors() {
		return floors;
	}

	public void setFloors(List<Integer> floors) {
		this.floors = floors;
	}
}

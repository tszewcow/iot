package org.iot.server.to;

/**
 * Transfer object representing location.
 * 
 * @author MSKAWINS
 *
 */
public class LocationFloorTo {

	private final String building;
	
	private final int floor;

	/**
	 * Constructs object representing location in given building on given floor.
	 * 
	 * @param building
	 * @param floor
	 */
	public LocationFloorTo(final String building, final int floor) {
		this.building = building;
		this.floor = floor;
	}
	
	public String getBuilding() {
		return building;
	}

	public int getFloor() {
		return floor;
	}
	
}

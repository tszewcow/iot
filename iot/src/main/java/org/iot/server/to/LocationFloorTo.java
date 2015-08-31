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
		if (building == null) {
			throw new IllegalArgumentException("building may not be null");
		}
		this.building = building;
		this.floor = floor;
	}
	
	public String getBuilding() {
		return building;
	}

	public int getFloor() {
		return floor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + building.hashCode();
		result = prime * result + floor;
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
		LocationFloorTo other = (LocationFloorTo) obj;
		if (!building.equals(other.building))
			return false;
		if (floor != other.floor)
			return false;
		return true;
	}
	
	
	
}

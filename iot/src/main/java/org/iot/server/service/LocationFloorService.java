package org.iot.server.service;

import java.util.List;

import org.iot.server.to.BuildingTo;
import org.iot.server.to.LocationFloorTo;

/**
 * Interface of service providing operations related to buildings and floors.
 * 
 * @author MSKAWINS
 */
public interface LocationFloorService {

	/**
	 * Provides all defined floors in all buildings.
	 * 
	 * @return list of available floors
	 */
	List<LocationFloorTo> getAllAvailableFloors();

	/**
	 * Provides all buildings with list of floors for each building.
	 * 
	 * @return list of available buildings
	 */
	List<BuildingTo> getAllBuildings();

}

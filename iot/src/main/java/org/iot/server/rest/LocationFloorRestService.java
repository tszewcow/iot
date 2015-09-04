package org.iot.server.rest;

import java.util.List;

import org.iot.server.service.LocationFloorService;
import org.iot.server.to.BuildingTo;
import org.iot.server.to.LocationFloorTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest service providing operations related to buildings and floors.
 * 
 * @author MSKAWINS
 */
@RestController
public class LocationFloorRestService {

	private final LocationFloorService locationFloorService;

	@Autowired
	public LocationFloorRestService(final LocationFloorService locationFloorService) {
		this.locationFloorService = locationFloorService;
	}

	/**
	 * Provides available buildings and floors
	 * 
	 * @return unmodifiable list of available buildings and floors
	 */
	@RequestMapping(value = "/services/locationfloor", method = RequestMethod.GET)
	public List<LocationFloorTo> getAvailableFloors() {
		return locationFloorService.getAllAvailableFloors();
	}

	@RequestMapping(value = "/services/buildings", method = RequestMethod.GET)
	public List<BuildingTo> getAllBuildings() {
		return locationFloorService.getAllBuildings();
	}

}

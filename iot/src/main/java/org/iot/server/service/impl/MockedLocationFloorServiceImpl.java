package org.iot.server.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.iot.server.service.LocationFloorService;
import org.iot.server.to.LocationFloorTo;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link LocationFloorService} interface which provides mocked, hardcoded values.
 * 
 * @author MSKAWINS
 */
@Service
public class MockedLocationFloorServiceImpl implements LocationFloorService {

	private static final List<LocationFloorTo> MOCKED_FLOORS;
	
	private static final String MOCKED_BUILDING_NAME = "MT2";
	
	static {
		final List<LocationFloorTo> floors = new ArrayList<>();
		for (int floor=6; floor<=11; ++floor) {
			floors.add(new LocationFloorTo(MOCKED_BUILDING_NAME, floor));
		}
		MOCKED_FLOORS = Collections.unmodifiableList(floors);
	}
	
	@Override
	public List<LocationFloorTo> getAllAvailableFloors() {
		return MOCKED_FLOORS;
	}

}

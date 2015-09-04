package org.iot.server.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.iot.server.service.LocationFloorService;
import org.iot.server.to.BuildingTo;
import org.iot.server.to.LocationFloorTo;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link LocationFloorService} interface which provides
 * mocked, hardcoded values.
 * 
 * @author MSKAWINS
 */
@Service
public class MockedLocationFloorServiceImpl implements LocationFloorService {

	private static final List<LocationFloorTo> MOCKED_FLOORS;
	private static final List<BuildingTo> MOCKED_BUILDINGS;

	private static final String MOCKED_MT2_BUILDING_NAME = "MT2";
	private static final String MOCKED_MT4_BUILDING_NAME = "MT4";

	static {
		final List<LocationFloorTo> floors = new ArrayList<>();
		for (int floor = 6; floor <= 11; ++floor) {
			floors.add(new LocationFloorTo(MOCKED_MT2_BUILDING_NAME, floor));
		}
		MOCKED_FLOORS = Collections.unmodifiableList(floors);

		prepareMockedBuildingsData();

		MOCKED_BUILDINGS = prepareMockedBuildingsData();
	}

	private static List<BuildingTo> prepareMockedBuildingsData() {
		BuildingTo mt2Building = new BuildingTo();
		mt2Building.setBuildingNumber(MOCKED_MT2_BUILDING_NAME);
		mt2Building.setFloors(Arrays.asList(5, 6, 7, 8, 9, 10, 11));
		BuildingTo mt4Building = new BuildingTo();
		mt4Building.setBuildingNumber(MOCKED_MT4_BUILDING_NAME);
		mt4Building.setFloors(Arrays.asList(6, 7, 8, 9, 10, 11, 12));

		return Arrays.asList(mt2Building, mt4Building);
	}

	@Override
	public List<LocationFloorTo> getAllAvailableFloors() {
		return MOCKED_FLOORS;
	}

	@Override
	public List<BuildingTo> getAllBuildings() {

		return MOCKED_BUILDINGS;
	}

}

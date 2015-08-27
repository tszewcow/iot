package org.iot.server.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iot.server.document.AutomaticMobileSet;
import org.iot.server.mapper.AutomaticMobileSetMapper;
import org.iot.server.repository.AutomaticMobileSetRepository;
import org.iot.server.service.impl.PositionCalculator.Pair;
import org.iot.server.to.AutomaticMobileSetTo;
import org.iot.server.to.BeaconStatusTo;
import org.iot.server.to.BeaconTo;
import org.iot.server.to.PositionTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PositionUpdater {

	private final PositionCalculator positionCalculator;
	private final AutomaticMobileSetRepository automaticMobileSetRepository;
	private final AutomaticMobileSetMapper automaticMobileSetMapper;

	// FileWriter writer;

	@Autowired
	public PositionUpdater(PositionCalculator positionCalculator,
			AutomaticMobileSetRepository automaticMobileSetRepository,
			AutomaticMobileSetMapper automaticMobileSetMapper) {
		this.positionCalculator = positionCalculator;
		this.automaticMobileSetRepository = automaticMobileSetRepository;
		this.automaticMobileSetMapper = automaticMobileSetMapper;
	}

	public void updateAutomaticMobileSetPositions(List<BeaconTo> beacons, List<BeaconStatusTo> beaconStatuses,
			List<AutomaticMobileSetTo> automaticMobileSets) {

		Map<String, List<BeaconStatusTo>> map = groupStatusesByAutomaticMobileSet(beaconStatuses);

		for (Map.Entry<String, List<BeaconStatusTo>> entry : map.entrySet()) {
			entry.getValue();
			Map<String, List<BeaconStatusTo>> beaconToBeaconStatuses = groupStatusesByBeacon(beaconStatuses);
			Map<String, Float> beaconToDistance = calculateDistance(beaconToBeaconStatuses);
			String mac = entry.getKey();
			AutomaticMobileSetTo automaticMobileSetTo = getAutomaticMobileSetByMac(automaticMobileSets, mac);
			PositionTo coordinates = calculateCoordinates(beaconToDistance, beacons);
			saveToDataBase(coordinates, automaticMobileSetTo);
		}
	}

	private Map<String, List<BeaconStatusTo>> groupStatusesByAutomaticMobileSet(List<BeaconStatusTo> beaconStatuses) {
		Map<String, List<BeaconStatusTo>> map = new HashMap<>();

		for (BeaconStatusTo beaconStatus : beaconStatuses) {
			String automaticMobileSetMac = beaconStatus.getMacAutomaticMobileSet();

			List<BeaconStatusTo> beaconStatusesForMobileSet = map.get(automaticMobileSetMac);
			if (beaconStatusesForMobileSet == null) {
				beaconStatusesForMobileSet = new ArrayList<>();
				map.put(automaticMobileSetMac, beaconStatusesForMobileSet);
			}
			beaconStatusesForMobileSet.add(beaconStatus);
		}
		return map;
	}

	private Map<String, List<BeaconStatusTo>> groupStatusesByBeacon(List<BeaconStatusTo> beaconStatuses) {
		Map<String, List<BeaconStatusTo>> map = new HashMap<>();

		for (BeaconStatusTo beaconStatusTo : beaconStatuses) {
			String beaconMac = beaconStatusTo.getMac();
			List<BeaconStatusTo> beaconStatusesForBeacon = map.get(beaconMac);
			if (beaconStatusesForBeacon == null) {
				beaconStatusesForBeacon = new ArrayList<>();
				map.put(beaconMac, beaconStatusesForBeacon);
			}
			beaconStatusesForBeacon.add(beaconStatusTo);
		}
		return map;
	}

	private AutomaticMobileSetTo getAutomaticMobileSetByMac(List<AutomaticMobileSetTo> automaticMobileSets,
			String mac) {
		for (AutomaticMobileSetTo automaticMobileSet : automaticMobileSets) {
			String currentMac = automaticMobileSet.getMacAutomaticMobileSet();
			if (currentMac.equals(mac)) {
				return automaticMobileSet;
			}
		}
		throw new IllegalStateException("automaticMobileSet with mac " + mac + " has not been found.");
	}

	private Map<String, Float> calculateDistance(Map<String, List<BeaconStatusTo>> beaconMacToBeaconStatuses) {
		Map<String, Float> map = new HashMap<>();
		for (Map.Entry<String, List<BeaconStatusTo>> entry : beaconMacToBeaconStatuses.entrySet()) {
			map.put(entry.getKey(), calculateAverageDistance(entry.getValue()));
		}
		return map;
	}

	private float calculateAverageDistance(List<BeaconStatusTo> value) {

		float distance = 0;
		float avarageDistance = 0;
		int counter = 0;

		if (value.size() > 9) {
			for (BeaconStatusTo beaconData : value.subList(value.size() - 10, value.size())) {
				distance = (float) beaconData.getDistance();
				avarageDistance = avarageDistance + distance;
				counter++;
			}
		} else {
			for (BeaconStatusTo beaconData : value) {
				distance = (float) beaconData.getDistance();
				avarageDistance = avarageDistance + distance;
				counter++;
			}
		}

		return avarageDistance / counter * 25;
	}

	private PositionTo calculateCoordinates(Map<String, Float> beaconToDistance, List<BeaconTo> beacons) {

		List<Pair<PositionTo, Float>> coordinates = new ArrayList<>();
		Map<String, Float> map = new HashMap<>(beaconToDistance);

		for (Map.Entry<String, Float> entry : map.entrySet())
			for (BeaconTo beacon : beacons) {
				String key = entry.getKey();
				Float value = entry.getValue();

				if (beacon.getMac().equals(key)) {
					coordinates.add(new Pair<>(new PositionTo(beacon.getxBeacon(), beacon.getyBeacon()), value));
				}
			}

		PositionTo automaticMobileSetPosition = positionCalculator.calculatePosition(coordinates);

		System.out.println(automaticMobileSetPosition.getX());
		System.out.println(automaticMobileSetPosition.getY());

		// SaveDataPostitionToCsv(automaticMobileSetPosition.getX(),
		// automaticMobileSetPosition.getY());

		return automaticMobileSetPosition;
	}

	private void saveToDataBase(PositionTo coordinates, AutomaticMobileSetTo automaticMobileSetTo) {

		if (Float.isNaN(coordinates.getX()) && Float.isNaN(coordinates.getY())) {
			automaticMobileSetTo.setIsActual(false);
		} else {
			automaticMobileSetTo.setxAutomaticMobileSet(coordinates.getX());
			automaticMobileSetTo.setyAutomaticMobileSet(coordinates.getY());
			automaticMobileSetTo.setIsActual(true);
		}

		AutomaticMobileSet automaticMobileSet = automaticMobileSetMapper.mapTo2Document(automaticMobileSetTo);
		automaticMobileSetRepository.save(automaticMobileSet);
	}

	/*
	 * private void SaveDataPostitionToCsv(float x, float y) {
	 * 
	 * Path path = Paths.get("C://Users/tokepa/Desktop/DataFromServer.csv"); try
	 * { if (Files.exists(path)) { writer.write(String.valueOf(x));
	 * writer.write(","); writer.write(String.valueOf(y));
	 * writer.write(System.lineSeparator()); writer.flush(); } else { writer =
	 * new FileWriter("C://Users/tokepa/Desktop/DataFromServer.csv"); }
	 * 
	 * } catch (IOException e) { e.printStackTrace(); } }
	 */
}
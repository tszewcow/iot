package org.iot.server.service.impl;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.iot.server.document.Beacon;
import org.iot.server.document.BeaconStatus;
import org.iot.server.mapper.BeaconMapper;
import org.iot.server.mapper.BeaconStatusMapper;
import org.iot.server.repository.BeaconRepository;
import org.iot.server.repository.BeaconStatusRepository;
import org.iot.server.service.BeaconService;
import org.iot.server.to.BeaconStatusTo;
import org.iot.server.to.BeaconTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BeaconServiceImpl implements BeaconService {

	private final BeaconStatusRepository beaconStatusRepository;
	private final BeaconStatusMapper beaconStatusMapper;
	private final BeaconRepository beaconRepository;
	private final BeaconMapper beaconMapper;

	@Autowired
	public BeaconServiceImpl(BeaconStatusRepository beaconStatusRepository, BeaconStatusMapper beaconStatusMapper,
			BeaconRepository beaconRepository, BeaconMapper beaconMapper) {
		this.beaconStatusRepository = beaconStatusRepository;
		this.beaconStatusMapper = beaconStatusMapper;
		this.beaconMapper = beaconMapper;
		this.beaconRepository = beaconRepository;
	}

	@Override
	public List<BeaconStatusTo> getAllBeaconsStatuses() {
		List<BeaconStatus> beaconStatuses = beaconStatusRepository.findAll();
		return beaconStatusMapper.mapDocuments2Tos(beaconStatuses);
	}

	@Override
	public void registerStatus(BeaconStatusTo statusTo) {
		BeaconStatus beaconStatus = beaconStatusMapper.mapTo2Document(statusTo);
		beaconStatusRepository.save(beaconStatus);
	}

	@Override
	public List<BeaconTo> getAllBeacons() {
		List<Beacon> beaconTo = beaconRepository.findAll();
		return beaconMapper.mapDocuments2Tos(beaconTo);
	}

	@Override
	public List<BeaconTo> getBeacons(final String building, final int floor) {
		final Predicate<Beacon> buildingFloorFilter = beacon -> beacon.getBuilding().equals(building) && beacon.getFloor() == floor;
		final List<Beacon> beaconTo = beaconRepository.findAll().stream().filter(buildingFloorFilter).collect(Collectors.toList());
		return beaconMapper.mapDocuments2Tos(beaconTo);
	}
	
	@Override
	public BeaconTo addBeacon(BeaconTo beaconTo) {
		Beacon beacon = beaconMapper.mapTo2Document(beaconTo);
		beaconRepository.save(beacon);
		return beaconMapper.mapDocument2To(beacon);
	}

	@Override
	public void deleteBeacon(String id) {
		beaconRepository.delete(id);
	}

	@Override
	public BeaconTo updateBeacon(BeaconTo beaconTo) {
		Beacon beacon = beaconMapper.mapTo2Document(beaconTo);
		beaconRepository.save(beacon);
		return beaconMapper.mapDocument2To(beacon);
	}
}
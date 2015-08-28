package org.iot.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.iot.server.document.AutomaticMobileSet;
import org.iot.server.document.Beacon;
import org.iot.server.document.BeaconStatus;
import org.iot.server.repository.AutomaticMobileSetRepository;
import org.iot.server.repository.BeaconRepository;
import org.iot.server.repository.BeaconStatusRepository;
import org.iot.server.service.PositionUpdateService;
import org.iot.server.to.AutomaticMobileSetTo;
import org.iot.server.to.BeaconStatusTo;
import org.iot.server.to.BeaconTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionUpdateServiceImpl implements PositionUpdateService {

	private final PositionUpdater positionUpdater;
	private final BeaconStatusRepository beaconStatusRepository;
	private final BeaconRepository beaconRepository;
	private final AutomaticMobileSetRepository automaticMobileSetRepository;

	@Autowired
	public PositionUpdateServiceImpl(PositionUpdater positionUpdater, BeaconStatusRepository beaconStatusRepository,
			AutomaticMobileSetRepository automaticMobileSetRepository, BeaconRepository beaconRepository) {
		this.positionUpdater = positionUpdater;
		this.beaconStatusRepository = beaconStatusRepository;
		this.automaticMobileSetRepository = automaticMobileSetRepository;
		this.beaconRepository = beaconRepository;
	}

	@Override
	public void updatePositions() {

		List<Beacon> beaconList = beaconRepository.findAll();
		List<BeaconStatus> beaconStatusList = beaconStatusRepository.findAll();
		List<AutomaticMobileSet> automaticMobileSetsList = automaticMobileSetRepository.findAll();

		List<BeaconTo> beaconToList = new ArrayList<>();
		List<BeaconStatusTo> BeaconStatusToList = new ArrayList<>();
		List<AutomaticMobileSetTo> AutomaticMobileSetToList = new ArrayList<>();

		for (Beacon beacons : beaconList) {

			BeaconTo beaconTo = new BeaconTo();
			beaconTo.setName(beacons.getName());
			beaconTo.setMac(beacons.getMac());
			beaconTo.setUuidNormal(beacons.getUuidNormal());
			beaconTo.setUuidSecure(beacons.getUuidSecure());
			beaconTo.setUuidService(beacons.getUuidService());
			beaconTo.setBuilding(beacons.getBuilding());
			beaconTo.setFloor(beacons.getFloor());
			beaconTo.setRoom(beacons.getRoom());
			beaconTo.setxBeacon(beacons.getxBeacon());
			beaconTo.setyBeacon(beacons.getyBeacon());

			beaconToList.add(beaconTo);

		}
		for (BeaconStatus beaconStatus : beaconStatusList) {

			BeaconStatusTo beaconStatusTo = new BeaconStatusTo();

			beaconStatusTo.setUuid(beaconStatus.getUuid());
			beaconStatusTo.setMeasuredStrenght(beaconStatus.getMeasuredStrenght());
			beaconStatusTo.setMac(beaconStatus.getMac());
			beaconStatusTo.setRssi(beaconStatus.getRssi());
			beaconStatusTo.setMinor(beaconStatus.getMinor());
			beaconStatusTo.setMajor(beaconStatus.getMajor());
			beaconStatusTo.setMacAutomaticMobileSet(beaconStatus.getMacAutomaticMobileSet());
			beaconStatusTo.setDistance(beaconStatus.getDistance());

			BeaconStatusToList.add(beaconStatusTo);
		}

		for (AutomaticMobileSet automaticMobileSet : automaticMobileSetsList) {

			AutomaticMobileSetTo automaticMobileSetTo = new AutomaticMobileSetTo();

			automaticMobileSetTo.setId(automaticMobileSet.getId());
			automaticMobileSetTo.setProject(automaticMobileSet.getProject());
			automaticMobileSetTo.setGuardian(automaticMobileSet.getGuardian());
			automaticMobileSetTo.setBuilding(automaticMobileSet.getBuilding());
			automaticMobileSetTo.setFloor(automaticMobileSet.getFloor());
			automaticMobileSetTo.setRoom(automaticMobileSet.getRoom());
			automaticMobileSetTo.setxAutomaticMobileSet(automaticMobileSet.getxAutomaticMobileSet());
			automaticMobileSetTo.setyAutomaticMobileSet(automaticMobileSet.getyAutomaticMobileSet());
			automaticMobileSetTo.setMacAutomaticMobileSet(automaticMobileSet.getMacAutomaticMobileSet());
			automaticMobileSetTo.setIpAutomaticMobileSet(automaticMobileSet.getIpAutomaticMobileSet());

			AutomaticMobileSetToList.add(automaticMobileSetTo);
		}

		positionUpdater.updateAutomaticMobileSetPositions(beaconToList, BeaconStatusToList, AutomaticMobileSetToList);

	}
}
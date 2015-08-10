package org.iot.server.service.impl;

import org.iot.server.repository.BeaconStatusRepository;
import org.iot.server.service.PositionUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionUpdateServiceImpl implements PositionUpdateService {

	private final PositionUpdater positionUpdater;
	private final BeaconStatusRepository repository;

	// TODO Add repositories

	@Autowired
	public PositionUpdateServiceImpl(PositionUpdater positionUpdater, BeaconStatusRepository beaconStatusRepository) {
		this.positionUpdater = positionUpdater;
		this.repository = beaconStatusRepository;
	}

	@Override
	public void updatePositions() {
		// TODO User position updater

		repository.count();

	}
}
package org.iot.server;

import org.iot.server.service.PositionUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UpdateJobRunner {

	private final PositionUpdateService positionUpdateService;

	@Autowired
	public UpdateJobRunner(PositionUpdateService positionUpdateService) {
		this.positionUpdateService = positionUpdateService;
	}

	@Scheduled(fixedDelay = 500)
	public void runUpdateJob() {
		positionUpdateService.updatePositions();
		System.out.println("1 sec\n");
	}
}

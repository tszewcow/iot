package org.iot.server.repository;

import org.iot.server.document.BeaconStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BeaconStatusRepository extends MongoRepository<BeaconStatus, String> {
}

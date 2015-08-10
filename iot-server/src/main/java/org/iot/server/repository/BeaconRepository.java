package org.iot.server.repository;

import org.iot.server.document.Beacon;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BeaconRepository extends MongoRepository<Beacon, String> {
}
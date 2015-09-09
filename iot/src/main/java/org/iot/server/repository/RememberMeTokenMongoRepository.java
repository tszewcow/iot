package org.iot.server.repository;

import org.iot.server.document.RememberMeToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RememberMeTokenMongoRepository extends MongoRepository<RememberMeToken, String> {
}
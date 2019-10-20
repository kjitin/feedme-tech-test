package com.group.stars.repo;

import com.group.stars.entity.EventEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<EventEntity, String> {
}

package com.cavityeye.backend.user.repository;

import com.cavityeye.backend.user.dto.NotificationGroupDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationGroupRepository extends MongoRepository<NotificationGroupDto, String> {


}

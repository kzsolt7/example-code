package com.cavityeye.backend.user.repository;

import com.cavityeye.backend.user.dto.PermissionGroupDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionGroupRepository extends MongoRepository<PermissionGroupDto, String> {
}

package com.cavityeye.backend.user.repository;

import com.cavityeye.backend.user.dto.PermissionGroupDto;
import com.cavityeye.backend.user.dto.UserDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionGroupRepository extends MongoRepository<PermissionGroupDto, String> {

    @Query("{name : ?0}")
    Optional<PermissionGroupDto> findByName(String name);
}

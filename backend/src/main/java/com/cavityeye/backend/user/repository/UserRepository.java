package com.cavityeye.backend.user.repository;

import com.cavityeye.backend.user.dto.UserDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserDto, String> {

    @Query("{userName : ?0}")
    UserDto findByUserName(String name);
}

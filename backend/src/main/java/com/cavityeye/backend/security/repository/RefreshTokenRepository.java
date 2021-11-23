package com.cavityeye.backend.security.repository;

import com.cavityeye.backend.security.dto.RefreshTokenDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends MongoRepository<RefreshTokenDto, String> {

    @Query("{token : ?0}")
    Optional<RefreshTokenDto> getUserByRefreshToken(String token);
}

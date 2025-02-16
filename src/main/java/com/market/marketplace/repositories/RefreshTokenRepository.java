package com.market.marketplace.repositories;

import com.market.marketplace.models.AppUser;
import com.market.marketplace.models.RefreshToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findTokenByUser(AppUser user);
    // Dumb query, but it works
    @Modifying
    @Transactional
    @Query("UPDATE RefreshToken u SET u.token = :token, u.expiration = :expiration WHERE u.id = :userId")
    void updateTokenAndExpirationById(String token, Instant expiration, Integer userId);
}

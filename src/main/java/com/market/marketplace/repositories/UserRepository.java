package com.market.marketplace.repositories;

import com.market.marketplace.enums.Role;
import com.market.marketplace.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByRole(Role role);
    Optional<AppUser> findByUsername(String username);
}

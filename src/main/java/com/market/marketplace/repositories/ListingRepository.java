package com.market.marketplace.repositories;

import com.market.marketplace.models.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Integer> {
    List<Listing> findByUsername(String username);
    List<Listing> findByUsernameAndId(String username, Integer id);
    List<Listing> findByTitleContainingIgnoreCase(String keyword);
}

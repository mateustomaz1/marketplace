package com.market.marketplace.services;

import com.market.marketplace.dto.PageListingResponse;
import com.market.marketplace.models.Listing;
import com.market.marketplace.repositories.ListingRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ListingPageService {
    private final ListingRepository listingRepository;

    @Transactional
    public PageListingResponse listing(Integer id) {
        Optional<Listing> listing = listingRepository.findById(id);
        return listing.map(value -> new PageListingResponse(value.getTitle(), value.getDescription(), value.getPrice(),
                value.getCategory(), value.getUsername(), value.getImage(), value.getNumber())).orElse(null);
    }

    @Transactional
    public List<Listing> search(Map<String, String> title) {
        String encodedTitle = title.get("title");
        if (encodedTitle == null) return null;

        String decodedTitle = URLDecoder.decode(encodedTitle, StandardCharsets.UTF_8);
        List<Listing> listings = listingRepository.findByTitleContainingIgnoreCase(decodedTitle);
        if (listings.isEmpty()) return null;

        return listings;
    }
}

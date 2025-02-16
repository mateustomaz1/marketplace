package com.market.marketplace.services;

import com.market.marketplace.dto.ListingRequest;
import com.market.marketplace.dto.ProfileResponse;
import com.market.marketplace.models.AppUser;
import com.market.marketplace.models.Listing;
import com.market.marketplace.repositories.ListingRepository;
import com.market.marketplace.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ProfileService {
    private final ListingRepository listingRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public String list(ListingRequest request, MultipartFile file, String authorizationHeader) throws IOException {
        String jwt = authorizationHeader.split(" ")[1].trim();
        String username = jwtService.extractUsername(jwt);
        AppUser user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Listing listing = new Listing();

        listing.setTitle(request.title());
        listing.setDescription(request.description());
        listing.setPrice(request.price());
        listing.setCategory(request.category());
        listing.setImage(file.getBytes());
        listing.setUsername(username);
        listing.setNumber(request.number());
        listingRepository.save(listing);

        user.setTotalProducts(user.getTotalProducts() + 1);
        userRepository.save(user);

        return "Listing created successfully";
    }

    @Transactional
    public ProfileResponse profile(String username) {
        AppUser user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        List<Listing> userListings = listingRepository.findByUsername(username);
        return new ProfileResponse(username, user.getTotalProducts(), user.getTotalSold(), userListings);
    }

    @Transactional
    public void delete(Integer id, String authorizationHeader) {
        String jwt = authorizationHeader.split(" ")[1].trim();
        String username = jwtService.extractUsername(jwt);
        List<Listing> userListings = listingRepository.findByUsernameAndId(username, id);
        if (userListings.isEmpty()) throw new RuntimeException("Listing not found");
        AppUser user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        user.setTotalProducts(user.getTotalProducts() - 1);

        listingRepository.deleteById(id);
    }

    @Transactional
    public void sold(Integer id, String authorizationHeader) {
        String jwt = authorizationHeader.split(" ")[1].trim();
        String username = jwtService.extractUsername(jwt);
        List<Listing> userListings = listingRepository.findByUsernameAndId(username, id);
        if (userListings.isEmpty()) throw new RuntimeException("Listing not found");;
        AppUser user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        user.setTotalProducts(user.getTotalProducts() - 1);
        user.setTotalSold(user.getTotalSold() + 1);

        userRepository.save(user);
        listingRepository.deleteById(id);
    }
}

package com.market.marketplace.controllers;

import com.market.marketplace.dto.ListingRequest;
import com.market.marketplace.dto.ProfileResponse;
import com.market.marketplace.services.ProfileService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/profile")
@AllArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/{username}")
    public ResponseEntity<ProfileResponse> profile(@PathVariable String username) {
        return ResponseEntity.ok(profileService.profile(username));
    }

    @PostMapping("/new/listing")
    public ResponseEntity<?> list(@RequestPart("request") @Valid ListingRequest request,
                                  @RequestPart("image") MultipartFile file,
                                  @RequestHeader(name = "Authorization") String authorizationHeader) throws IOException {
        return ResponseEntity.ok(profileService.list(request, file, authorizationHeader));
    }

    @DeleteMapping("/delete/listing/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id, @RequestHeader(name = "Authorization") String authorizationHeader) {
        profileService.delete(id, authorizationHeader);
        return ResponseEntity.ok("Listing deleted successfully");
    }

    @PostMapping("/listing/sold/{id}")
    public ResponseEntity<?> sold(@PathVariable Integer id, @RequestHeader(name = "Authorization") String authorizationHeader) {
        profileService.sold(id, authorizationHeader);
        return ResponseEntity.ok("Listing marked as sold");
    }
}

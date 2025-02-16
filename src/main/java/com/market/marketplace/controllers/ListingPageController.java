package com.market.marketplace.controllers;

import com.market.marketplace.models.Listing;
import com.market.marketplace.services.ListingPageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class ListingPageController {
    private final ListingPageService listingPageService;

    @GetMapping("/listing/{id}")
    public ResponseEntity<?> listing(@PathVariable Integer id) {
        return ResponseEntity.ok(listingPageService.listing(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Listing>> searchByKeyword(@RequestParam Map<String, String> title) {
        return new ResponseEntity<>(listingPageService.search(title), HttpStatus.OK);
    }
}

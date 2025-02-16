package com.market.marketplace.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ListingRequest(
        @NotNull @Size(max = 100) String description,
        @NotNull double price,
        @NotNull @Pattern(regexp = "^(TV|PC|GA|PH|CAR)$") String category,
        @NotNull @Size(max = 10) String title,
        @NotNull String number) { }
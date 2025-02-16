package com.market.marketplace.dto;

public record PageListingResponse(String title, String description, double price, String category, String username, byte[] image, String number) { }

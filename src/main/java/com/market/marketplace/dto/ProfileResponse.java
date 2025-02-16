package com.market.marketplace.dto;

import com.market.marketplace.models.Listing;
import java.util.List;

public record ProfileResponse(String username, Integer totalProducts, Integer totalSold, List<Listing> listings) { }

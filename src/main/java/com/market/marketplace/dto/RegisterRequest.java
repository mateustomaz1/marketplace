package com.market.marketplace.dto;

import jakarta.validation.constraints.NotNull;

public record RegisterRequest(@NotNull String username, @NotNull String email, @NotNull String password) { }
package com.market.marketplace.services;

import com.market.marketplace.dto.AuthenticationResponse;
import com.market.marketplace.dto.LoginRequest;
import com.market.marketplace.dto.RefreshTokenRequest;
import com.market.marketplace.dto.RegisterRequest;
import com.market.marketplace.enums.Role;
import com.market.marketplace.models.*;
import com.market.marketplace.repositories.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.market.marketplace.repositories.UserRepository;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtUtil;

    public AppUser register(RegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent() || userRepository.findByUsername(request.username()).isPresent()) {
            throw new RuntimeException("Email/Username already exists");
        }

        return userRepository.save(AppUser.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .totalSold(0)
                .totalProducts(0)
                .role(Role.USER).build());
    }

    // *-* clarification *-*
    // method acts as a login and access, refresh token generator
    // user logs in, a new access, refresh token is generated and returned
    // if the user is logged in and the access token is expired a new access token is generated and returned
    // if the user is logged in and the refresh token is expired, a new access and refresh token is generated and returned
    // if the user logs out, the refresh token is deleted from the frontend
    // if the user tries to log back in, the old refresh token is used again until it expires
    public AuthenticationResponse authenticate(LoginRequest request) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        var user = userRepository.findByUsername(request.username()).orElseThrow(() -> new RuntimeException("User not found"));
        var jwtToken = jwtUtil.generateToken(user);
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findTokenByUser(user);

        if(refreshToken.isPresent()){
            if (jwtUtil.isRefreshTokenExpired(refreshToken.get())) return new AuthenticationResponse(jwtToken, refreshToken.get().getToken());
            else {
                RefreshToken newRefreshToken = jwtUtil.generateRefreshToken(user);
                refreshTokenRepository.updateTokenAndExpirationById(newRefreshToken.getToken(), newRefreshToken.getExpiration(), user.getId());
                return new AuthenticationResponse(jwtToken, newRefreshToken.getToken());
            }
        }

        RefreshToken newRefreshToken = jwtUtil.generateRefreshToken(user);
        refreshTokenRepository.save(newRefreshToken);

        return new AuthenticationResponse(jwtToken, newRefreshToken.getToken());
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest request) {
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByToken(request.refreshToken());
        if (refreshToken.isPresent() && jwtUtil.isRefreshTokenExpired(refreshToken.get())) {
            Optional<AppUser> user = userRepository.findById(refreshToken.get().getUser().getId());
            if (user.isPresent()) {
                var jwtToken = jwtUtil.generateToken(user.get());
                return new AuthenticationResponse(jwtToken, request.refreshToken());
            }
        }
        else throw new RuntimeException("Refresh token expired, please Log in");
        return null;
    }
}

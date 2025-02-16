package com.market.marketplace;

import com.market.marketplace.enums.Role;
import com.market.marketplace.models.AppUser;
import com.market.marketplace.models.RefreshToken;
import com.market.marketplace.repositories.RefreshTokenRepository;
import com.market.marketplace.repositories.UserRepository;
import com.market.marketplace.services.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@SpringBootApplication
@AllArgsConstructor
public class MarketPlaceApplication implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtUtil;

    public static void main(String[] args) {
        SpringApplication.run(MarketPlaceApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Optional<AppUser> adminAccount = userRepository.findByRole(Role.USER);

        if (adminAccount.isEmpty()) {
            AppUser user1 = new AppUser();
            user1.setUsername("Gustas");
            user1.setEmail("gustas8520@gmail.com");
            user1.setPassword(new BCryptPasswordEncoder().encode("gustas123"));
            user1.setTotalProducts(0);
            user1.setTotalSold(0);
            user1.setRole(Role.USER);
            userRepository.save(user1);
            RefreshToken newRefreshToken1 = jwtUtil.generateRefreshToken(user1);
            refreshTokenRepository.save(newRefreshToken1);

            AppUser user2 = new AppUser();
            user2.setUsername("Antanas");
            user2.setEmail("antanas8520@gmail.com");
            user2.setPassword(new BCryptPasswordEncoder().encode("antanas123"));
            user2.setTotalProducts(0);
            user2.setTotalSold(0);
            user2.setRole(Role.USER);
            userRepository.save(user2);
            RefreshToken newRefreshToken2 = jwtUtil.generateRefreshToken(user2);
            refreshTokenRepository.save(newRefreshToken2);
        }
    }
}
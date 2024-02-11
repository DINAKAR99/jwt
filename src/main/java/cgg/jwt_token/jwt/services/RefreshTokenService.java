package cgg.jwt_token.jwt.services;

import java.time.Instant;
import java.util.UUID;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cgg.jwt_token.jwt.entities.RefreshToken;
import cgg.jwt_token.jwt.entities.User;
import cgg.jwt_token.jwt.repo.RefreshTokenRepo;
import cgg.jwt_token.jwt.repo.UserRepo;

@Service
public class RefreshTokenService {

    public long refreshTokenValidity = 2 * 60 * 1000;

    @Autowired
    UserRepo userRepo;

    @Autowired
    RefreshTokenRepo refreshTokenRepo;

    public RefreshToken createRefreshToken(String username) {
        User user1 = userRepo.findByUsername(username);
        RefreshToken refreshToken = user1.getRefreshToken();

        if (refreshToken == null) {

            refreshToken = RefreshToken.builder().refreshToken(UUID.randomUUID().toString())
                    .expiry(Instant.now().plusMillis(refreshTokenValidity)).user(user1).build();

        } else {

            refreshToken.setExpiry(Instant.now().plusMillis(refreshTokenValidity));
        }

        user1.setRefreshToken(refreshToken);

        refreshTokenRepo.save(refreshToken);

        return refreshToken;
    }

    public RefreshToken verifyRefreshToken(RefreshToken rt) {

        RefreshToken byRefreshToken = refreshTokenRepo.findByRefreshToken(rt.getRefreshToken());
        if (byRefreshToken == null) {
            throw new RuntimeException("given rf token doesnt exist ");

        }
        if (byRefreshToken.getExpiry().compareTo(Instant.now()) < 0) {
            throw new RuntimeException("  rf token expired ");

        }

        return byRefreshToken;

    }
}

package cgg.jwt_token.jwt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import cgg.jwt_token.jwt.entities.RefreshToken;
import java.util.List;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Integer> {
    RefreshToken findByRefreshToken(String refreshToken);
}

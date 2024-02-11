package cgg.jwt_token.jwt.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {
    private String token;
    public String user;
    public RefreshToken refreshtoken;
}

package cgg.jwt_token.jwt.entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtRequest {
    public String user;
    public String password;
}

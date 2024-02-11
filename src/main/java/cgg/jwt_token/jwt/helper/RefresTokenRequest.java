package cgg.jwt_token.jwt.helper;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefresTokenRequest {
    public String refreshtoken;
}

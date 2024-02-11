package cgg.jwt_token.jwt.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user_1")

public class User {
    @Id
    private String username;
    private String password;

    @Column(unique = true)
    private String email;
    private String role;
    @OneToOne
    @JsonIgnore
    private RefreshToken refreshToken;
}
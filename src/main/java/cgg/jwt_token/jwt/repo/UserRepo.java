package cgg.jwt_token.jwt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cgg.jwt_token.jwt.entities.User;
import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    public User findByEmail(String email);

    public User findByUsername(String username);

}

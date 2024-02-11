package cgg.jwt_token.jwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cgg.jwt_token.jwt.entities.User;
import cgg.jwt_token.jwt.repo.UserRepo;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo repo;
    @Autowired
    private PasswordEncoder pass;

    // static {
    // users.add(new User("din", "123", "ed@gmail.com"));
    // users.add(new User("bin", "123", "re@gmail.com"));
    // users.add(new User("tin", "123", "io@gmail.com"));
    // }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(repo.findAll());

    }

    @GetMapping("/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name) {

        // User orElse = users.stream().filter(s ->
        // s.getUsername().equals(name)).findAny().orElse(null);

        return ResponseEntity.ok(repo.findByUsername(name));

    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User us) {

        String encode = pass.encode(us.getPassword());
        us.setPassword(encode);
        return ResponseEntity.ok(repo.save(us));

    }

}

package com.campus.event.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.campus.event.model.User;
import com.campus.event.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User register(User user) {
        user.setVerified(true);
        user.setRole(User.Role.STUDENT);
        return repo.save(user);
    }

    public User login(String email, String password) {
        Optional<User> u = repo.findByEmail(email);
        if (u.isPresent() && u.get().getPassword().equals(password)) {
            return u.get();
        }
        return null;
    }
}
package com.denik.vy.services;

import com.denik.vy.models.User;
import com.denik.vy.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }
    public List<User> users(){
        return userRepository.findAll();
    }
    public User user(long userId){
        return userRepository.findById(userId).orElse(null);
    }
    public void create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    public void edit(User user) {
        User us = Objects.requireNonNull(userRepository.findById(user.getId()).orElse(null), "null user");
        us.setUsername(user.getUsername());
        us.setEmail(user.getEmail());
        us.setPassword(passwordEncoder.encode(user.getPassword()));
        us.setRoles(user.getRoles());
        userRepository.save(us);
    }
    public void delete(long userId){
        userRepository.deleteById(userId);
    }

}

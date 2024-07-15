package com.aathi.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aathi.webapp.model.AppUser;
import com.aathi.webapp.repo.UserRepo;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepo userRepo;

    public AppUser createUser(AppUser user) {
        return userRepo.save(user);
    }

    public Optional<AppUser> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public boolean validateUser(String username, String password) {
        Optional<AppUser> optionalUser = findByUsername(username);
        if (optionalUser.isPresent()) {
            AppUser user = optionalUser.get();
            return user.getPassword().equals(password); // In a real application, you should hash the password
        }
        return false;
    }
}

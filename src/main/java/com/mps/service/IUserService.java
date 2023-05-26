package com.mps.service;

import com.mps.entity.User;

import java.util.Optional;

public interface IUserService {
    Long saveUser(User user);
    Optional<User> findByUsername(String username);
}

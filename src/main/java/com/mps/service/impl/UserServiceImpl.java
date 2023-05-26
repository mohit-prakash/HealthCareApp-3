package com.mps.service.impl;

import com.mps.entity.User;
import com.mps.repository.UserRepository;
import com.mps.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository repo;
    @Override
    public Long saveUser(User user) {
        return repo.save(user).getUserId();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repo.findByUsername(username);
    }
}

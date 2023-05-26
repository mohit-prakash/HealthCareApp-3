package com.mps.service.impl;

import com.mps.entity.User;
import com.mps.repository.UserRepository;
import com.mps.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {
    @Autowired
    private UserRepository repo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public Long saveUser(User user) {
        String pwd = user.getPassword();
        System.out.println("Password :=> "+pwd);
        String encodedPwd = passwordEncoder.encode(pwd);
        user.setPassword(encodedPwd);
        return repo.save(user).getUserId();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repo.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser = repo.findByUsername(username);
        if (optUser.isPresent()){
            User user = optUser.get();
            return new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}

package com.mps.runner;

import com.mps.constants.UserRoles;
import com.mps.entity.User;
import com.mps.service.IUserService;
import com.mps.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MasterAccountSetup implements CommandLineRunner {
    @Value("${master.user.name}")
    private String displayName;
    @Value("${master.user.email}")
    private String username;
    @Autowired
    private UserUtil userUtil;
    @Autowired
    private IUserService userService;

    @Override
    public void run(String... args) throws Exception {
        if (!userService.findByUsername(username).isPresent()) {
            User user = new User();
            user.setUsername(username);
            user.setDisplayName(displayName);
            user.setPassword(userUtil.genPwd());
            user.setRole(UserRoles.ADMIN.name());
            userService.saveUser(user);
        }
    }
}

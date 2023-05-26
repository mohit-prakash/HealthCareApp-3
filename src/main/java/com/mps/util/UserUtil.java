package com.mps.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserUtil {
    public String genPwd(){
        String pwd=null;
        pwd= UUID.randomUUID().toString().replace("-","").substring(0,8);
        return pwd;
    }
}

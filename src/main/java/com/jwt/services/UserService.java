package com.jwt.services;

import com.jwt.pojo.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService
{
    private static Map<String, User> users = new HashMap<>();
    static {
        User user = new User();
        user.setUsername("Pavlin");
        user.setPassword("123456");
        users.put("Pavlin", user);
    }

    public User findUserByUserName(String userName)
    {
        return users.get(userName);
    }


    public Boolean authenticate(String userName, String passWord)
    {
        User user = findUserByUserName(userName);

        if (null!=user)
            return user.getPassword().equals(passWord);

        return false;
    }
}

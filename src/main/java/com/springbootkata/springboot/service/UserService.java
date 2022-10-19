package com.springbootkata.springboot.service;

import com.springbootkata.springboot.model.User;
import java.util.List;

public interface UserService {

    List<User> findAllUser ();
    User getUserById(long id);
    void saveUser(User user);
    void deleteUserById(long id);
    User findUserByUsername(String username);
    void addDefaultUser();
    User passwordCoder(User user);
}
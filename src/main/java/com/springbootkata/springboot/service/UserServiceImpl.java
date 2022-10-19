package com.springbootkata.springboot.service;

import com.springbootkata.springboot.DAO.RoleDAO;
import com.springbootkata.springboot.DAO.UserDAO;
import com.springbootkata.springboot.model.Role;
import com.springbootkata.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User passwordCoder(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

        @Override
    public List<User> findAllUser() {
        return userDAO.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userDAO.getById(id);
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        userDAO.save(passwordCoder(user));
    }

    @Transactional
    @Override
    public void deleteUserById(long id) {
        userDAO.deleteById(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    @PostConstruct
    public void addDefaultUser() {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleDAO.findById(1L).get());
        Set<Role> roleSet2 = new HashSet<>();
        roleSet2.add(roleDAO.findById(1L).get());
        roleSet2.add(roleDAO.findById(2L).get());
        User user1 = new User("Billy", "Herrington", (byte) 27, "user1@mail.ru", "user1", "12345", roleSet);
        User user2 = new User("Steve", "Jobs", (byte) 52, "admin@mail.ru", "admin", "admin", roleSet2);
        saveUser(user1);
        saveUser(user2);
    }
}
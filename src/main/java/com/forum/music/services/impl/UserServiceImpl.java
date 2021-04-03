package com.forum.music.services.impl;

import com.forum.music.entity.User;
import com.forum.music.repository.UserRepository;
import com.forum.music.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }
    @Override
    public User update(User user)  {
        return userRepository.save(user);
    }

    @Override
    public User delete(User user)  {
        return userRepository.save(user);
    }

    @Override
    public List<User> list() {
        return userRepository.findAll();
    }
}

package com.forum.music.services;

import com.forum.music.entity.User;

import java.util.List;

public interface UserService {
    public User create(User user) ;
    public User update(User user) ;
    public User delete(User user) ;
    public List<User> list() ;


}

package com.forum.music.repository;

import com.forum.music.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  /*  @Override
    @EntityGraph("User.userProfile")
    User save(User user);

    @Override
    @EntityGraph("User.userProfile")
    List<User> findAll();*/
}

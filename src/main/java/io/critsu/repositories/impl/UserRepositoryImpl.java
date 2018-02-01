package io.critsu.repositories.impl;

import io.critsu.entities.User;
import io.critsu.mapper.UserMapper;
import io.critsu.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(long id) {
        return userMapper.getUser(id);
    }

    @Override
    public long checkUsername(String username) {
        Long id = userMapper.getId(username);
        return null == id ? 0 : id;
    }

    @Override
    public boolean authorization(User user) {
        // TODO
        return false;
    }

    @Override
    public boolean register(User user) {

        // TODO

        return false;
    }
}

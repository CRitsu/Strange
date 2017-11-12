package io.critsu.mapper;

import io.critsu.entities.Friend;
import io.critsu.entities.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    User getUser(long id);
    List<Friend> getFriends(long id);
    int auth(@Param("id") long id, @Param("password") String password);
    long getId(String username);
}

package io.critsu.repositories;

import io.critsu.entities.User;

public interface UserRepository {

    /**
     * 通过id获得用户信息
     * @param id 检索id
     * @return 用户信息；如果不存在则返回null
     */
    User getUser(long id);

    /**
     * 检查用户名是否存在并返回用户id
     * @param username 用来检查的用户名
     * @return 0 如果不存在；否则返回用户id
     */
    long checkUsername(String username);

    /**
     * 用户认证
     * @param user 认证信息，至少包括用户名和秘密
     * @return true如果通过；false不通过
     */
    boolean authorization(User user);

    /**
     * 注册用户
     * @param user 注册用户信息
     * @return 注册结果，true成功
     */
    boolean register(User user);

}

package io.critsu.mapper;

import io.critsu.entities.Friend;
import io.critsu.entities.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息映射
 * 获取关于用户的信息
 * 包括用户的基本信息，好友信息
 */
public interface UserMapper {
    /**
     * 通过ID获得用户信息
     * @param id 指定id
     * @return 用户信息；无此用户则为null
     */
    User getUser(long id);

    /**
     * 获取指定用户id的好友信息
     * @param id 获取好友信息的用户id
     * @return 好友信息list
     */
    List<Friend> getFriends(long id);

    /**
     * 用户认证
     * @param id 认证用户id
     * @param password 用来匹配的密码
     * @return 匹配结果：无匹配结果为0；匹配通过为1；否则异常
     */
    int auth(@Param("id") long id, @Param("password") String password);

    /**
     * 通过用户名获取用户ID
     * @param username 指定用户名
     * @return 用户ID；无用户则null
     */
    Long getId(String username);

    /**
     * 检测用户信息是否存在
     * @param user 用户信息，检测用户名，邮箱，联系号码
     * @return 1存在；0不存在
     */
    int checkRegistered(User user);

    /**
     * 储存用户并设定储存后的ID
     * @param user 用户信息
     * @return 影响行数
     */
    int saveUser(User user);

    /**
     * 通过ID删除用户
     * @param id 指定被删除用户的id
     * @return 影响行数
     */
    int deleteUser(long id);

    /**
     * 添加好友
     * @param uid 用户id
     * @param fid 好友id
     * @return 影响行数
     */
    int addFriend(@Param("userId") long uid, @Param("friendId") long fid);

    /**
     * 检查好友关系（检查黑名单）
     * @param uid 用户id
     * @param fid 好友id
     * @return 返回 Integer 对象：null为非好友；0为好友关系；1为黑名单
     */
    Integer checkFriendStatus(@Param("userId") long uid, @Param("friendId") long fid);

    /**
     * 移除好友
     * @param uid 用户id
     * @param fid 好友id
     * @return 影响行数
     */
    int removeFriend(@Param("userId") long uid, @Param("friendId") long fid);

    /**
     * 添加黑名单
     * @param uid 用户id
     * @param bid 黑名单id
     * @return 影响行数
     */
    int addBlackList(@Param("userId") long uid, @Param("blackId") long bid);

    /**
     * 移除黑名单
     * @param uid 用户id
     * @param bid 黑名单id
     * @return 影响行数
     */
    int removeBlackList(@Param("userId") long uid, @Param("blackId") long bid);

    /**
     * 锁定用户第一步<br>
     * 检查锁定次数
     * @param uid 检查用户id
     * @return 无锁定历史为null；否则返回锁定次数（大于0）
     */
    Integer checkLockedTimes(long uid);

    /**
     * 锁定用户第二步 A<br>
     * 该用户无锁定记录时<br>
     * 锁定用户并设置锁定次数为1
     * @param uid 锁定用户id
     * @param unlockTime 解锁时间
     * @return 影响行数
     */
    int lockUserByInsert(@Param("userId") long uid, @Param("unlockTime") String unlockTime);

    /**
     * 锁定用户第二步 B<br>
     * 有锁定记录更新次数
     * @param uid 锁定用户id
     * @param unlockTime 解锁时间
     * @param times 更新锁定次数
     * @return 影响行数
     */
    int lockUserByUpdate(@Param("userId") long uid, @Param("unlockTime") String unlockTime, @Param("times") int times);

    /**
     * 清理给定时间之前的数据<br>
     * 通常为半年之前的数据<br>
     * 基准时间的格式为 YYYY-MM-DD<br>
     * 清理用户锁定历史记录
     * @param baseTime 基准时间
     * @return 影响行数
     */
    int clearLockedHistory(String baseTime);

    /**
     * 解锁用户
     * @param uid 被解锁用户id
     * @return 影响行数
     */
    int unLockUser(long uid);


}

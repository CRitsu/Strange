package test.db;

import io.critsu.config.StrangeRootConfig;
import io.critsu.entities.Friend;
import io.critsu.entities.User;
import io.critsu.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;
import static io.critsu.constant.StrangeDefinition.*;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = StrangeRootConfig.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testGetUser() {
        User user = userMapper.getUser(10001L);
        User noUser = userMapper.getUser(20001L);
        assertNotNull(user);
        assertNull(noUser);
        System.out.println(user);
    }

    @Test
    public void testGetFriends() {
        List<Friend> friends = userMapper.getFriends(10000L);
        List<Friend> noFriends = userMapper.getFriends(20000L);
        assertNotNull(friends);
        assertEquals(new ArrayList<>(), noFriends);
        System.out.println(friends);
    }

    @Test
    public void testAuth() {
        assertEquals(FLAG_ENABLE, userMapper.auth(10000L,"TEST_USER_PASSWORD"));
        assertEquals(FLAG_DISABLE, userMapper.auth(20001L,"TEST_USER_PASSWORD"));
    }

    @Test
    public void testGetId() {
        assertEquals(10000L, userMapper.getId("TEST_USER_STRANGE").longValue());
        assertNull(userMapper.getId("TEST_USER_STRANGE1"));
    }

    @Test
    public void testCheckRegistered() {
        User user = new User();
        user.setMail("aa");
        user.setTel("11");
        user.setUsername("TEST_USER_STRANGE");

        User noUser = new User();
        noUser.setMail("TEST");
        noUser.setTel("TEST");
        noUser.setUsername("TEST");

        assertEquals(FLAG_EXISTS, userMapper.checkRegistered(user));
        assertEquals(FLAG_NOT_EXISTS, userMapper.checkRegistered(noUser));
    }

    @Test
    public void testSaveAndDeleteUser() {
        User user = new User();
        user.setUsername("TEST_SAVE");
        user.setPassword("TEST_SAVE_PASS");
        user.setTel("123123");
        user.setMail("TEST_SAVE_MAIL");

        int num = userMapper.saveUser(user);

        assertNotEquals(0L, user.getId());
        assertEquals(SINGLE_SUCCEED, num);

        Long id = userMapper.getId("TEST_SAVE");
        assertNotNull(id);

        num = userMapper.deleteUser(id);

        assertEquals(SINGLE_SUCCEED, num);
    }

    @Test
    public void testAddAndRemoveFriend() {
        long a = 20000L, b = 30000L;
        Integer num = userMapper.checkFriendStatus(a, b);
        assertNull(num);
        num = userMapper.addFriend(a, b);
        assertEquals(SINGLE_SUCCEED, num.intValue());
        // 检查白名单状态
        num = userMapper.checkFriendStatus(a, b);
        assertEquals(FLAG_DISABLE, num.intValue());
        num = userMapper.removeFriend(a, b);
        assertEquals(SINGLE_SUCCEED, num.intValue());
        num = userMapper.checkFriendStatus(a, b);
        assertNull(num);
    }

    @Test
    public void testAddAndRemoveBlacklist() {
        long a = 20001L, b = 30001L;
        // 未加黑名单前check 结果应该为null 非好友
        Integer num = userMapper.checkFriendStatus(a, b);
        assertNull(num);
        // 添加黑名单check 结果应为1 黑名单
        num = userMapper.addBlackList(a, b);
        assertEquals(SINGLE_SUCCEED, num.intValue());
        // 检查黑名单状态
        num = userMapper.checkFriendStatus(a, b);
        assertEquals(FLAG_ENABLE, num.intValue());
        // 删除黑名单 结果应为null 非好友
        num = userMapper.removeBlackList(a, b);
        assertEquals(SINGLE_SUCCEED, num.intValue());
        num = userMapper.checkFriendStatus(a, b);
        assertNull(num);
    }

    @Test
    public void testLockUser() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);

        long uid = 12345L;
        // check
        Integer num = userMapper.checkLockedTimes(uid);
        assertNull(num);
        // insert
        num = userMapper.lockUserByInsert(uid, simpleDateFormat.format(calendar.getTime()));
        assertEquals(SINGLE_SUCCEED, num.intValue());
        // check again
        num = userMapper.checkLockedTimes(uid);
        assertEquals(num.intValue(),1);

        calendar.add(Calendar.MONTH, 1);

        // update
        num = userMapper.lockUserByUpdate(uid, simpleDateFormat.format(calendar.getTime()), num + 1);
        assertEquals(SINGLE_SUCCEED, num.intValue());
        // and check
        num = userMapper.checkLockedTimes(uid);
        assertEquals(num.intValue(),2);
        // unlock
        num = userMapper.unLockUser(uid);
        assertEquals(SINGLE_SUCCEED, num.intValue());

        calendar.add(Calendar.YEAR, 1);

        // clear
        num = userMapper.clearLockedHistory(simpleDateFormat.format(calendar.getTime()));
        assertEquals(SINGLE_SUCCEED, num.intValue());

        // clear again
        num = userMapper.clearLockedHistory(simpleDateFormat.format(calendar.getTime()));
        assertEquals(SINGLE_FAILED, num.intValue());
    }


}

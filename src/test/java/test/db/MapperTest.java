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

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = StrangeRootConfig.class)
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testGetUser() {
        User user = userMapper.getUser(10000L);
        assertNotNull(user);
        System.out.println(user);
    }

    @Test
    public void testGetFriends() {
        List<Friend> friends = userMapper.getFriends(10000L);
        assertNotNull(friends);
        System.out.println(friends);
    }

    @Test
    public void testAuth() {
        assertEquals(userMapper.auth(10000L,"TEST_USER_PASSWORD"), 1);
    }

    @Test
    public void testGetId() {
        assertEquals(userMapper.getId("TEST_USER_STRANGE"), 10000L);
    }

}

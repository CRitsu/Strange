package test.db;

import io.critsu.config.StrangeRootConfig;
import io.critsu.entities.User;
import io.critsu.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import test.TestConnection;

import java.util.List;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = StrangeRootConfig.class)
public class ConnectionTest {

    @Autowired
    private SqlSessionTemplate session;

    @Autowired
    private WebApplicationContext wac;

    @Test
    public void TestConnection() {
        int tar = session.selectOne("test.TestConnection.TestConnect");
        Assert.assertEquals(tar,1);
    }

    @Test
    public void TestUsers() {
        List<User> users = session.selectList("test.TestConnection.AllUsers");
        System.out.println(users.toString());
    }

    @Test
    public void testMapper() {
        TestConnection testConnection = session.getMapper(TestConnection.class);
        int tar = testConnection.TestConnect();
        Assert.assertEquals(tar,1);
        List<User> users = testConnection.AllUsers();
        System.out.println(users.toString());
    }

    @Test
    public void testBeanConfig() {
        UserMapper userMapper = (UserMapper) wac.getBean("UserMapper");
        User user = userMapper.getUser(10000L);
        Assert.assertNotNull(user);
        System.out.println(user.toString());
    }

}

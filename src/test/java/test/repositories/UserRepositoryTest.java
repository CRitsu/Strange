package test.repositories;

import io.critsu.config.StrangeRootConfig;
import io.critsu.entities.User;
import io.critsu.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = StrangeRootConfig.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGetUserAndId() {
        User user = userRepository.getUser(9999L);
        assertNull(user);
        long id = 10000L;
        user = userRepository.getUser(id);
        assertNotNull(user);
        Long tem = userRepository.checkUsername(user.getUsername());
        assertNotNull(tem);
        assertEquals(id, tem.longValue());


    }


}

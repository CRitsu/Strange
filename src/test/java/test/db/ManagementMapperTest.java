package test.db;

import io.critsu.config.StrangeRootConfig;
import io.critsu.entities.Attribute;
import io.critsu.entities.User;
import io.critsu.mapper.ManagementMapper;
import io.critsu.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = StrangeRootConfig.class)
public class ManagementMapperTest {

    @Autowired
    private ManagementMapper managementMapper;

    @Autowired
    private UserMapper userMapper;



    @Test
    public void testGetAllUsers() {

        if (managementMapper.getAllUsers(null, null, null, 0).size() < 100) createData();

        List<User> users = managementMapper.getAllUsers("user_id", "asc", 123, 10);
        assertEquals(10, users.size());
        assertEquals(10 - 1, users.get(users.size() - 1).getId() - users.get(0).getId() );
        System.out.println(users.toString());

        users = managementMapper.getAllUsers("user_id", "asc", null, 10);
        assertNotEquals(10, users.size());

        assertNotEquals(managementMapper.getAllUsers("user_id", "desc", 123, 10),
                managementMapper.getAllUsers("user_id", "asc", 123, 10));
    }


    @Test
    public void testAboutAttribute() {

        Attribute attribute = new Attribute();
        attribute.setAttribute("FIRST_ONE_TEST");
        attribute.setValue("First");

        int num = managementMapper.addAttribute(attribute);
        assertEquals(1, num);

        List<Attribute> attributes = createAttributes();
        String[] names = new String[attributes.size()];

        for (int i = 0; i < attributes.size(); i++) {
            names[i] = attributes.get(i).getAttribute();
        }
        assertEquals(attributes.size(), names.length);

        num = managementMapper.addMoreAttributes(attributes);
        assertEquals(15, num);

        attributes = managementMapper.getAllAttributes();

        // 初始化1个 + 单独测试1个 + 复数测试15个 = 17
        assertEquals(17, attributes.size());

        System.out.println(attributes);

        num = managementMapper.removeAttribute(attribute.getAttribute());
        assertEquals(1, num);
        assertEquals(16, managementMapper.getAllAttributes().size());

        num = managementMapper.removeMoreAttributes(names);
        assertEquals(15, num);
        assertEquals(1, managementMapper.getAllAttributes().size());

    }

    private List<Attribute> createAttributes() {
        List<Attribute> attributes = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Attribute attribute = new Attribute();
            attribute.setAttribute("TEST_ATTR_NO_" + i);
            attribute.setValue("VAL_NO_" + i);
            attributes.add(attribute);
        }
        assertEquals(15, attributes.size());
        return attributes;
    }


    private void createData() {
        String name = "CREATED_DATA_FOR_TEST_";
        String pass = "CREATED_PASSWORD_";
        String avatar = "CREATED_AVATAR_PATH_";
        String mail = "CREATED_MAIL@TEST.NO";
        Long tel = 135178L;
        User user = new User();
        for (int i = 0; i < 1000; i++) {
            user.setUsername(name + i);
            user.setPassword(pass + i);
            user.setAvatar(avatar + i);
            user.setMail(mail + i);
            user.setTel(tel.toString() + i);
            userMapper.saveUser(user);
        }
    }

}

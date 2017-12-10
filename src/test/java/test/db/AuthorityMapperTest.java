package test.db;

import io.critsu.config.StrangeRootConfig;
import io.critsu.entities.AuthorityOrigin;
import io.critsu.mapper.AuthorityMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = StrangeRootConfig.class)
public class AuthorityMapperTest {

    @Autowired
    private AuthorityMapper authorityMapper;

    @Test
    public void testGetAuthority() {
        long uid = 10000L;
        List<AuthorityOrigin> list = authorityMapper.getAuthority(uid);
        System.out.println(list.toString());
        assertEquals(5, list.size());

    }

    @Test
    public void testSetAndRemoveSingleAuthority() {
        AuthorityOrigin authorityOrigin = new AuthorityOrigin();
        authorityOrigin.setUserId(12345L);
        authorityOrigin.setAuthValue("12345L_TEST_VALE");
        authorityOrigin.setAuthName("12345L_TEST_NAME");

        int num = authorityMapper.setAuthority(authorityOrigin);
        assertEquals(1, num);

        String update = "UPDATE_TEST";
        authorityOrigin.setAuthValue(update);
        num = authorityMapper.updateAuthority(authorityOrigin);
        assertEquals(1, num);
        assertEquals(update, authorityMapper.getAuthorityByName(12345L, "12345L_TEST_NAME"));
        num = authorityMapper.removeAuthorityByName(12345L, "12345L_TEST_NAME");
        assertEquals(1, num);
        assertNull(authorityMapper.getAuthorityByName(12345L, "12345L_TEST_NAME"));
    }

    @Test
    public void testSetAndRemoveAuthorities() {
        long uid = 99999L;
        List<AuthorityOrigin> list = createAuthority(uid);
        int num = authorityMapper.setAuthorities(list);
        assertEquals(10, num);

        list = authorityMapper.getAuthority(uid);
        assertEquals(10, list.size());

        System.out.println(list);

        num = authorityMapper.removeAuthorityById(uid);
        assertEquals(10, num);

    }

    private List<AuthorityOrigin> createAuthority(long uid) {
        List<AuthorityOrigin> authorityOrigins = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AuthorityOrigin authorityOrigin = new AuthorityOrigin();
            authorityOrigin.setUserId(uid);
            authorityOrigin.setAuthName("TEST_AUTH_NAME_" + i);
            authorityOrigin.setAuthValue("TEST_AUTH_VALUE_" + i);
            authorityOrigins.add(authorityOrigin);
        }
        assertEquals(10, authorityOrigins.size());
        return authorityOrigins;
    }

}

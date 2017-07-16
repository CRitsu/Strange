package springtest.test;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import springtest.web.HomeController;

public class HomeControllerTest {
    @Test
    public void testHomePage() throws Exception {
        HomeController home = new HomeController();
        MockMvc mock = MockMvcBuilders.standaloneSetup(home).build();
        mock.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(MockMvcResultMatchers.view().name("home"));
    }
}

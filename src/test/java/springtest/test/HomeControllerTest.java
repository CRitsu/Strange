package springtest.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import springtest.entity.Spittle;
import springtest.services.SpittleRepository;
import springtest.web.HomeController;
import springtest.web.SpittleController;

public class HomeControllerTest {
    @Test
    public void testHomePage() throws Exception {
        HomeController home = new HomeController();
        MockMvc mock = MockMvcBuilders.standaloneSetup(home).build();
        mock.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(MockMvcResultMatchers.view().name("home"));
    }
    
    @Test
    public void testSpittleEqualeAndHash() {
        Spittle s1 = new Spittle("sp1", new Date());
        Spittle s2 = new Spittle("sp2", new Date());
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        System.out.println(s1.equals(s2));
        System.out.println(s1.getId()+","+s2.getId());
    }
    
    @Test
    public void testSpittleController() throws Exception {
        List<Spittle> expected = createSpittles(10);
        SpittleRepository mockRepository = Mockito.mock(SpittleRepository.class);
        Mockito.when(mockRepository.findSpittles(99999999L, 10))
            .thenReturn(expected);
        SpittleController controller = new SpittleController(mockRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/view/spittles.jsp")).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/spittles?max=99999999&count=10"))
            .andExpect(MockMvcResultMatchers.view().name("spittles"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("spittlesList"))
            .andExpect(MockMvcResultMatchers.model().attribute("spittlesList",
                    Matchers.hasItems(expected.toArray())));
        
        
    }
    
    private List<Spittle> createSpittles(int count) {
        List<Spittle> spittles = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            spittles.add(new Spittle("spittle.No." + i , new Date()));
        }
        return spittles;
    }
}

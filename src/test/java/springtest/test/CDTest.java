package springtest.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import config.CDConfig;
import springtest.CDImpl;
import springtest.MediaPlayer;

@ContextConfiguration(classes=CDImpl.class)
public class CDTest {

    @Test
    public void testMethod(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(CDConfig.class);
        MediaPlayer player = (MediaPlayer) ac.getBean("player");
        player.playTheCd();
        
        ((AnnotationConfigApplicationContext)ac).close();
    }
}

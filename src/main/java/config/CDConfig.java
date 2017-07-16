package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import springtest.CDImpl;
import springtest.CompactDisc;
import springtest.MediaPlayer;
import springtest.TestAspect;

@Configuration
@EnableAspectJAutoProxy
public class CDConfig {
    @Bean
    public CompactDisc getCdimpl() {
        return new CDImpl();
    }
    @Bean(name="player")
    public MediaPlayer mediaPlayer(CompactDisc compactDisc) {
        return new MediaPlayer(compactDisc);
    }
    
    @Bean
    public TestAspect testAspect() {
        return new TestAspect();
    }
    
}

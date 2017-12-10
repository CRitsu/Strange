import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DummyTest {

    @Test
    public void SimplenessTest() {
        long time = new Date().getTime();
        System.out.println(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(simpleDateFormat.format(new Date().getTime()));


    }

}

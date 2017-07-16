package springtest;

import org.springframework.stereotype.Component;

@Component
public class CDImpl implements CompactDisc {

    @Override
    public void paly() {
        System.out.println("Lalala...singing a song.");
    }

}

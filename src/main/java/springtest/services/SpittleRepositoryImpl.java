package springtest.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import springtest.entity.Spittle;

@Component
public class SpittleRepositoryImpl implements SpittleRepository {

    @Override
    public List<Spittle> findSpittles(Long max, int count) {
        List<Spittle> spittles = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            spittles.add(new Spittle("spittle.No." + i , new Date()));
        }
        return spittles;
    }

}

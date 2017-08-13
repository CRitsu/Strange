package springtest.services;

import java.util.List;

import springtest.entity.Spittle;

public interface SpittleRepository {
    List<Spittle> findSpittles(Long max, int count);
    Spittle findSpittle(Long id);
}

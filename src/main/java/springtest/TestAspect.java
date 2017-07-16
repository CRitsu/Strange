package springtest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TestAspect {
//    @Pointcut("execution(** springtest.MediaPlayer.playTheCd(..))")
//    @Pointcut("execution(** springtest.CompactDisc.play(..))")
    @Pointcut("execution(** playTheCd(..))")
    public void playTheCd(){};
    
    @Around("playTheCd()")
    public void watchPlay(ProceedingJoinPoint pj) {
        try {
            System.out.println("do something before sing a song.");
            System.out.println("so, let's begin.");
            pj.proceed();
            System.out.println("Okay, we end the show.");
        } catch (Throwable e) {
            System.out.println("This is a Exception");
        }
    }
    
}

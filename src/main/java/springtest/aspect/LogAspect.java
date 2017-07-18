package springtest.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAspect {
    
    @Pointcut("execution(* *.to*(..))")
    public void toPage(){};
    
    @Around("toPage()")
    public void logPages(ProceedingJoinPoint point) {
        try {
            System.out.println(point.getTarget() + "START -");
            point.proceed();
            System.out.println(point.getTarget() + "END -");
        } catch (Throwable e) {
            System.out.println(point.getTarget() + "FAILED -");
        }
    }
}

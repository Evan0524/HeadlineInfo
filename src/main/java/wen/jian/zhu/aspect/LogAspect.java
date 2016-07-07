package wen.jian.zhu.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/7/7.
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);
    @Before("execution(* wen.jian.zhu.controller.*Controller.*(..))")
    public void beforeMethod (JoinPoint joinPoint) {
        StringBuilder sb = new StringBuilder();
        for (Object args : joinPoint.getArgs()) {
            sb.append("args:" + args.toString() + "|");
        }
    log.info("beforemethod:" + sb.toString());
    }
    @After("execution(* wen.jian.zhu.controller.IndexController.*(..))")
    public void afterMethod(JoinPoint jointPoint) {
        log.info("aftermethod:111");
    }

}

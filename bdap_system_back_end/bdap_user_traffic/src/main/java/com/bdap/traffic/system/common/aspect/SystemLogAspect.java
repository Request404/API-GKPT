package com.bdap.traffic.system.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SystemLogAspect {

  private final Logger aspectLogger = LoggerFactory.getLogger(SystemLogAspect.class);

  @Pointcut("@annotation(com.bdap.traffic.system.common.annotation.SystemLog)")
  public void logPointcut(){}

  @Before("logPointcut()")
  public void runtimeBefore(JoinPoint joinPoint){
    aspectLogger.info("============================ before ======================================================");
    aspectLogger.info(String.valueOf(joinPoint.getSignature()));
    aspectLogger.info(joinPoint.getSignature().getName()+"方法执行了");
    Object[] args = joinPoint.getArgs();
    for (Object arg : args) {
      aspectLogger.info("方法参数："+String.valueOf(arg));
    }
  }

  @AfterReturning(value = "logPointcut()",returning = "result")
  public void runtimeReturn(JoinPoint joinPoint,Object result){
    aspectLogger.info(joinPoint.getSignature().getName()+"方法返回值："+result);
  }

  @AfterThrowing(value = "logPointcut()",throwing = "exception")
  public void runtimeThrowException(JoinPoint joinPoint,Exception exception){
    aspectLogger.error(joinPoint.getSignature().getName()+"方法发生了错误:"+exception);
  }

  @After("logPointcut()")
  public void endRun(JoinPoint joinPoint){
    aspectLogger.info(joinPoint.getSignature().getName()+"方法结束");
    aspectLogger.info("+++++++++++++++++++++++++++++++ after ++++++++++++++++++++++++++++++++++++++++++++++++++");

  }

}

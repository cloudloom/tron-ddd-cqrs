package com.tracebucket.tron.cqrs.support;

import com.tracebucket.tron.cqrs.annotation.CommandHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.context.request.async.DeferredResult;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author ffl
 * @since 08-04-2015
 * @version 0.1
 */

@Aspect
@Configurable
public class CommandHelperAdvisor {
    private static Logger log = LoggerFactory.getLogger(CommandHelperAdvisor.class);

    @Autowired
    private CommandHelper commandHelper;

    @After("@annotation(com.tracebucket.tron.cqrs.annotation.CommandHandler)")
    public void invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method m = ms.getMethod();
        CommandHandler annotation = AnnotationUtils.getAnnotation(m, CommandHandler.class);

        Object result = joinPoint.proceed();
        Object[] parameterValues = joinPoint.getArgs();
        Command command = (Command)Arrays.stream(parameterValues)
                .filter(p -> p instanceof Command)
                .findFirst()
                .get();

        DeferredResult deferredResult = commandHelper.fetch(command.getCid());








    }
}

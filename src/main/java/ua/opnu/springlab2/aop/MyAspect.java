package ua.opnu.springlab2.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class MyAspect {

    @Around("Pointcuts.allAddMethods()")
    public Object aroundAddingAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();
        log.info("Попытка добавить данные методом {}", methodName);

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("Ошибка при добавлении данных методом {}", methodName, e);
            throw new RuntimeException(e);
        }

        log.info("Данные добавлены методом {}", methodName);
        return result;
    }

    @Around("Pointcuts.allGetMethods()")
    public Object aroundGettingAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();
        log.info("Попытка получить данные методом {}", methodName);

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("Ошибка при получении данных методом {}", methodName, e);
            throw new RuntimeException(e);
        }

        log.info("Данные получены методом {}", methodName);
        return result;
    }

    @Around("Pointcuts.allDeleteMethods()")
    public Object aroundDeletingAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();
        log.info("Попытка удалить данные методом {}", methodName);

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("Ошибка при удалении данных методом {}", methodName, e);
            throw new RuntimeException(e);
        }

        log.info("Данные удалены методом {}", methodName);
        return result;
    }
}

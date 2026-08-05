package com.github.cdeor.multi.layer.cache.base;


import com.github.cdeor.multi.layer.cache.exception.TargetMethodInvocationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Optional;

public class ProceedingJoinPointWrapper {

    private final ProceedingJoinPoint pjp;
    private final Class<?> targetClazz;
    private final MethodSignature methodSignature;


    public ProceedingJoinPointWrapper(ProceedingJoinPoint pjp) {
        this.pjp = pjp;
        this.targetClazz = pjp.getTarget().getClass();
        this.methodSignature = (MethodSignature) pjp.getSignature();
    }

    public Object proceed() throws TargetMethodInvocationException {
        try {
            return pjp.proceed();
        } catch (Throwable e) {
            throw new TargetMethodInvocationException("Target Method Invocation Exception", e);
        }
    }

    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {

        String methodName = methodSignature.getName();
        Class[] paramTypes = methodSignature.getParameterTypes();
        Optional<Method> methodOpt =
                targetClazzMethod(targetClazz, methodName, paramTypes);

        if (methodOpt.isPresent() && annotationClass != null) {
            return methodOpt.get()
                    .getDeclaredAnnotation(annotationClass);
        }
        return null;
    }

    private Optional<Method> targetClazzMethod(Class<?> targetClazz,
                                               String methodName,
                                               Class[] paramTypes) {
        try {
            return Optional.of(targetClazz.getMethod(methodName, paramTypes));
        } catch (NoSuchMethodException e) {
            return Optional.empty();
        }
    }

}

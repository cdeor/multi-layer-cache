package com.github.cdeor.multi.layer.cache.entity.context;

import com.github.cdeor.multi.layer.cache.base.ProceedingJoinPointWrapper;
import org.aspectj.lang.ProceedingJoinPoint;

public class QueryContext extends ProceedingJoinPointWrapper implements CacheContext {


    public QueryContext(ProceedingJoinPoint pjp) {
        super(pjp);
    }

    @Override
    public String getKey() {
        return "";
    }

    @Override
    public String getClusterId() {
        return "";
    }
}

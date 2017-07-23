package com.yisaer.framework.proxy;

import com.yisaer.framework.annotations.Aspect;
import com.yisaer.framework.annotations.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Created by Yisa on 2017/7/23.
 */

@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy{
    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);
    private long begin;

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
        logger.debug(String.format("time:%dms",System.currentTimeMillis()-begin));
        logger.debug("-------end----------");
    }

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        logger.debug("------begin----------");
        logger.debug(String.format("class:%s" , cls.getName()));
        logger.debug(String.format("method:%s" , method.getName()));

        begin = System.currentTimeMillis();
    }
}

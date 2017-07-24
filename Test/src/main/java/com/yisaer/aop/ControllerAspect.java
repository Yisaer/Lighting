package com.yisaer.aop;

import com.yisaer.framework.annotations.Aspect;
import com.yisaer.framework.annotations.Controller;
import com.yisaer.framework.annotations.Service;
import com.yisaer.framework.proxy.AspectProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Created by Yisa on 2017/7/24.
 */

@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy{
    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);
    private long begin;

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
        logger.debug("-------begin------------");
        logger.debug(String.format("class : %s",cls.getName()));
        logger.debug(String.format("method : %s",method.getName()));
        System.out.println("===============Here=====================");
        begin  = System.currentTimeMillis();
    }

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        logger.debug(String.format("time: %dms",System.currentTimeMillis()-begin));
        System.out.println("===============END==============      "+(System.currentTimeMillis()-begin));
        logger.debug("------------ end ------------");
    }



}

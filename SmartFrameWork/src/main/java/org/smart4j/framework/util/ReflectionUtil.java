package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 */
public final class ReflectionUtil {
    private static final Logger LOGGER= LoggerFactory.getLogger(ReflectionUtil.class);

    public static Object newInstance(Class<?> cls){
        Object instance=null;
        try {
            instance=cls.newInstance();
        } catch (Exception e) {
            LOGGER.error("new instance failure",e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    public static Object invokeMethod(Object obj, Method method,Object...args){
        System.out.println(obj.toString()+"调用方法invokeMethod    "+method.getName());
        Object result;
        try {
            method.setAccessible(true);
            result=method.invoke(obj,args);
            System.out.println("invokeMethod    "+result.toString());
        } catch (Exception e) {
            LOGGER.error("invoke method failure",e);
            System.out.println(e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void setField(Object obj, Field field,Object value){
        try {
            field.setAccessible(true);
            field.set(obj,value);
        } catch (Exception e) {
            LOGGER.error("invoke method failure",e);
            throw new RuntimeException(e);
        }

    }
}

package com.yisaer.framework.helper;

import com.yisaer.framework.annotations.Inject;
import com.yisaer.framework.util.ArrayUtil;
import com.yisaer.framework.util.CollectionUtil;
import com.yisaer.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by Yisa on 2017/7/1.
 */
public final class IocHelper {
    static{
        /**
         * 获取所有的Bean类与Bean实例之间的映射关系
         */
        Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
        if(CollectionUtil.isNotEmpty(beanMap)){
            // 遍历BeanMap
            for(Map.Entry<Class<?>,Object> beanEntry: beanMap.entrySet()){
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                // 获取Bean类的所有成员变量
                Field[] beanFields = beanClass.getDeclaredFields();
                if(ArrayUtil.isNotEmpty(beanFields)){
                    // 遍历BeanField
                    for(Field beanField: beanFields){
                        // 判断当前Bean Field是否带有Inject 注解
                        if(beanField.isAnnotationPresent(Inject.class)){
                            // 在BeanMap中获取BeanField对应的实例
                            Class<?> beanFiledClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFiledClass);
                            if(beanFieldInstance!= null ){
                                // 通过反射初始化BeanField的值
                                ReflectionUtil.setField(beanInstance,beanField,beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}

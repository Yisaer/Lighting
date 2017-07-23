package com.yisaer.framework;

import com.yisaer.framework.helper.*;
import com.yisaer.framework.util.ClassUtil;

/**
 * Created by Yisa on 2017/7/1.
 */
public final class HelperLoader {
    public static void init(){
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for(Class<?> cls : classList){
            ClassUtil.loadClass(cls.getName(),true);
        }
    }
}

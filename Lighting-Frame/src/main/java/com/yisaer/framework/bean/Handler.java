package com.yisaer.framework.bean;

/**
 * Created by Yisa on 2017/7/1.
 */

import java.lang.reflect.Method;

/**
 * 封装Action信息
 */
public class Handler {

    /**
     * Controller类
     */

    private Class<?> controllerClass;
    /**
     * Action方法
     */
    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}

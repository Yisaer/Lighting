package com.yisaer.framework.helper;

import com.yisaer.framework.annotations.Aspect;
import com.yisaer.framework.proxy.AspectProxy;
import com.yisaer.framework.proxy.Proxy;
import com.yisaer.framework.proxy.ProxyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Created by Yisa on 2017/7/23.
 */
public final class AopHelper {

    private static final Logger logger = LoggerFactory.getLogger(AopHelper.class);

    static {
        try{
            //创建ProxyMap,用于存放代理类与目标类列表的映射关系
            Map<Class<?> , Set<Class<?>>> proxyMap = createProxyMap();

            // 用于存放目标类与代理类对象的映射关系
            Map<Class<?>,List<Proxy>> targetMap = createTargetMap(proxyMap);
            // 遍历TargetMap
            for(Map.Entry<Class<?> , List<Proxy>> targetEntry: targetMap.entrySet()){
                //目标类
                Class<?> targetClass = targetEntry.getKey();

                //代理类列表
                List<Proxy> proxyList = targetEntry.getValue();
                // 创建代理实例
                Object proxy = ProxyManager.createProxy(targetClass,proxyList);

                // 用代理实例覆盖目标实例
                BeanHelper.setBean(targetClass,proxy);
            }


        }catch (Exception e){
            logger.error("aop failure", e);
        }
    }

    /**
     * 获取Aspect注解中设置的注解类,若该类不是Aspect类,则通过classhelper获取相关类并放入目标集合
     * @param aspect
     * @return
     * @throws Exception
     */
    private static Set<Class<?>> createTargetClassSet (Aspect aspect) throws Exception{
        Set<Class<?>> targetClassSet = new HashSet<Class<?>>();
        Class<? extends Annotation> annotation = aspect.value();
        if(annotation != null && !annotation.equals(Aspect.class)){
            targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }
        return targetClassSet;
    }

    /**
     * 获取代理类与目标类列表之间的映射关系
     *
     * 例如某类拥有注解@Aspect(Controller.class)
     * 首先获取这个注解,其次通过这个注解获取所被标注@Controller的类
     * 最后将这个@Aspect与@List<Class<?>>(@Controller)建立起映射关系
     * @return
     * @throws Exception
     */
    private static Map<Class<?> , Set<Class<?>>> createProxyMap() throws Exception{
        Map<Class<?>,Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);

        // 遍历切面类
        for(Class<?> proxyClass: proxyClassSet){
            // 判断@Aspect注解是否存在
            if(proxyClass.isAnnotationPresent(Aspect.class)){
                Aspect aspect = proxyClass.getAnnotation(Aspect.class);
                // 获取目标类列表
                Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                proxyMap.put(proxyClass,targetClassSet);
            }
        }
        return proxyMap;
    }

    /**
     * 此时已经得到代理类与目标类的一对多的映射集合,称为A
     * 现在需要得到目标类与代理类实例的一对多映射集合
     * 遍历映射A,获取Key的每个List(Class) list
     * 遍历list,将每个Class的所对应的Proxy实例化添加进Map(Class,List(Proxy))
     * @param proxyMap
     * @return
     * @throws Exception
     */
    private static Map<Class<?>,List<Proxy>> createTargetMap(Map<Class<?> , Set<Class<?>>> proxyMap)
        throws Exception{
        Map<Class<?> , List<Proxy>> targetMap = new HashMap<Class<?>, List<Proxy>>();

        for(Map.Entry<Class<?> , Set<Class<?>>> proxyEntry: proxyMap.entrySet()){
            Class<?> proxyClass = proxyEntry.getKey();
            Set<Class<?>> targetClassSet = proxyEntry.getValue();

            for(Class<?> targetClass:targetClassSet){
                    Proxy proxy = (Proxy) proxyClass.newInstance();
                if(targetMap.containsKey(targetClass)){
                    targetMap.get(targetClass).add(proxy);
                }
                else{
                    List<Proxy> proxyList = new ArrayList<Proxy>();
                    proxyList.add(proxy);
                    targetMap.put(targetClass,proxyList);
                }

            }
        }
        return targetMap;
    }



}

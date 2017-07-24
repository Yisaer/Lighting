package com.yisaer.framework.proxy;

import com.yisaer.framework.annotations.Transaction;
import com.yisaer.framework.helper.DatabaseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Created by Yisa on 2017/7/24.
 */

/**
 *  这里定义了一个ThreadLocal的Boolean变量,为了保证同一线程中事务相关只会执行一次
 *
 */
public class TransactionProxy implements Proxy {

    private static final Logger logger = LoggerFactory.getLogger(TransactionProxy.class);
    private static final ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal<Boolean>(){
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Exception, Throwable {
        Object result=null ;
        boolean flag = FLAG_HOLDER.get();
        // 获取目标方法
        Method method = proxyChain.getTargetMethod();
        if(!flag && method.isAnnotationPresent(Transaction.class)){
            try{
                FLAG_HOLDER.set(true);
                // 开启事务,将当前线程内的连接的自动提交关闭,转为事务模式
                DatabaseHelper.beginTransaction();
                logger.debug("begin transaction");
                // 执行目标方法
                result = proxyChain.doProxyChain();
                // 提交事务
                DatabaseHelper.commitTransaction();
                logger.debug("commit transaction");
            }catch(Exception e){
                DatabaseHelper.rollbackTransaction();
                logger.debug("rollback transaction");
            }finally {
                FLAG_HOLDER.remove();
            }
        }else{
            // 未开启事务,执行目标方法
            result = proxyChain.doProxyChain();
        }

        return result;
    }
}

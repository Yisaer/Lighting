package com.yisaer.framework.proxy;

/**
 * Created by Yisa on 2017/7/23.
 */
public interface Proxy {
    Object doProxy(ProxyChain proxyChain) throws Exception, Throwable;
}

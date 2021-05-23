package com.benbird.inner.wxapplets.shiro;

import org.springframework.stereotype.Component;

/**
 * 项目名: wx-applets
 * 创建者: Admin
 * 创建时间: 2021/5/15
 * 描述:
 *
 * @author Admin
 */
@Component
public class ThreadLocalToken {

    private final ThreadLocal<String> THREAD_LOCAL = ThreadLocal.withInitial(() -> null);

    public void setToken(String token){
        THREAD_LOCAL.set(token);
    }

    public String getToken(){
        return THREAD_LOCAL.get();
    }

    public void clear(){
        THREAD_LOCAL.remove();
    }


}

package com.benbird.inner.wxapplets.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 项目名: wx-applets
 * 创建者: Admin
 * 创建时间: 2021/5/15
 * 描述: 认证对象，AuthenticationToken的子类可以交给Shiro去管理
 *
 * @author Admin
 */
public class OAuth2Token implements AuthenticationToken {

    private final String token;

    public OAuth2Token(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}

package com.benbird.inner.wxapplets.shiro;

import com.benbird.inner.wxapplets.util.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 项目名: wx-applets
 * 创建者: Admin
 * 创建时间: 2021/5/15
 * 描述: 实现认证和授权
 *
 * @author Admin
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof OAuth2Token;
    }

    /**
     * 授权(验证权限时调用)
     *
     * @param principalCollection 授权信息
     * @return 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //TODO 查询用户的权限列表
        //TODO 把权限列表添加到info对象中
        return simpleAuthorizationInfo;
    }

    /**
     * 认证（登录时使用）
     *
     * @param authenticationToken 认证TOKEN
     * @return 认证信息
     * @throws AuthenticationException Exception
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        //TODO 从令牌中获取userId，然后检测该账户是否被冻结。
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo();
        //TODO 往info对象中添加用户信息、Token字符串
        return simpleAuthenticationInfo;
    }
}

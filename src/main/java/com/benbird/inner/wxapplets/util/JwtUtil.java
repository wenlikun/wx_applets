package com.benbird.inner.wxapplets.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 项目名: wx-applets
 * 创建者: Admin
 * 创建时间: 2021/5/15
 * 描述:
 *
 * @author Admin
 */
@Slf4j
@Component
public class JwtUtil {

    /**
     * 密钥
     */
    @Value("${wx.applets.jwt.secret:wx_applets_19950710}")
    private String secret;

    /**
     * 令牌过期时间（天）
     */
    @Value("${wx.applets.jwt.expire:5}")
    private int expire;

    private static final String USER_ID = "userId";

    /**
     * 根据用户id创建TOKEN
     * @param userId    用户ID
     * @return          TOKEN
     */
    public String createToken(int userId) {
        Date date = DateUtil.offset(new Date(), DateField.DAY_OF_YEAR, expire);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTCreator.Builder builder = JWT.create();
        return builder.withClaim(USER_ID, userId).withExpiresAt(date).sign(algorithm);
    }

    /**
     * 根据TOKEN获取userId
     * @param token TOKEN
     * @return      userId
     */
    public int getUserId(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim(USER_ID).asInt();
    }

    /**
     * 验证TOKEN，验证失败会抛异常
     * @param token TOKEN
     */
    public void verifierToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        verifier.verify(token);
    }

}

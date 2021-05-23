package com.benbird.inner.wxapplets.common;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目名: wx-applets
 * 创建者: Admin
 * 创建时间: 2021/5/15
 * 描述: 小程序返回值
 *
 * @author Admin
 */
public class Result extends HashMap<String, Object> {

    public Result() {
        put("code", HttpStatus.SC_OK);
        put("msg", "success");
    }

    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static Result ok() {
        return new Result();
    }

    public static Result ok(String msg) {
        Result result = new Result();
        result.put("msg", msg);
        return result;
    }

    public static Result ok(Map<String, Object> map) {
        Result result = new Result();
        result.putAll(map);
        return result;
    }

    public static Result error(int code, String msg) {
        Result result = new Result();
        result.put("code", code);
        result.put("msg", msg);
        return result;
    }

    public static Result error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static Result error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

}

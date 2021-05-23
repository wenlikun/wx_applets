package com.benbird.inner.wxapplets.exception;

import lombok.Data;

/**
 * 项目名: wx-applets
 * 创建者: Admin
 * 创建时间: 2021/5/15
 * 描述: 小程序异常类
 *
 * @author Admin
 */
@Data
public class WxAppletsException extends RuntimeException {

    private String message;
    private int code = 500;


    public WxAppletsException(String message) {
        super(message);
        this.message = message;
    }

    public WxAppletsException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    public WxAppletsException(String msg, int code) {
        super(msg);
        this.message = msg;
        this.code = code;
    }

    public WxAppletsException(String message, int code, Throwable e) {
        super(message, e);
        this.message = message;
        this.code = code;
    }
}

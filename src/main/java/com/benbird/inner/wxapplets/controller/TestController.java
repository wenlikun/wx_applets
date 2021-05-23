package com.benbird.inner.wxapplets.controller;

import com.benbird.inner.wxapplets.common.Result;
import com.benbird.inner.wxapplets.controller.form.TestSayHelloForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 项目名: wx-applets
 * 创建者: Admin
 * 创建时间: 2021/5/15
 * 描述:
 *
 * @author Admin
 */
@RestController
@RequestMapping("/test")
@Api("测试Web接口")
public class TestController {


    @PostMapping("/sayHello")
    @ApiOperation("最简单的测试方法")
    public Result sayHello(@Valid @RequestBody TestSayHelloForm form) {
        return Result.ok().put("message", "Hello,"+form.getName());
    }

}

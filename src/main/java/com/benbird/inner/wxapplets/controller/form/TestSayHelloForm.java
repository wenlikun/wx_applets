package com.benbird.inner.wxapplets.controller.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 项目名: wx-applets
 * 创建者: Admin
 * 创建时间: 2021/5/15
 * 描述:
 *
 * @author Admin
 */
@Data
@ApiModel
public class TestSayHelloForm {

    // @NotBlank
    @ApiModelProperty("姓名")
    // @Pattern(regexp = "^[\\u4e00-\\u9fa5]{2,15}$",message = "不符合正则表达式")
    private String name;

}

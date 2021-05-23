package com.benbird.inner.wxapplets.pojo.mongodb;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * 项目名: wx-applets
 * 创建者: Admin
 * 创建时间: 2021/5/20
 * 描述:
 *
 * @author Admin
 */
@Data
@Document(collection = "message_ref")
public class MessageRefEntity implements Serializable {

    @Id
    private String _id;

    @Indexed
    private String messageId;

    @Indexed
    private Integer receiverId;

    @Indexed
    private Boolean readFlag;

    @Indexed
    private Boolean lastFlag;
}


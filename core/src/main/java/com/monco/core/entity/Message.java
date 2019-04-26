package com.monco.core.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Auther: monco
 * @Date: 2019/4/26 00:03
 * @Description: 公告消息管理模块
 */
@Entity
@Getter
@Setter
@Table(name = "sys_message")
public class Message extends BaseEntity<Long> {

    private static final long serialVersionUID = 1215455638632774015L;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 图片
     */
    private String pic;

    /**
     * 标题
     */
    private String title;

    /**
     * 消息类型
     */
    private Integer messageType;
}

package com.monco.core.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Auther: monco
 * @Date: 2019/5/6 22:40
 * @Description:
 */
@Entity
@Getter
@Setter
@Table(name = "sys_system_setting")
public class SystemSetting extends BaseEntity<Long> {

    private static final long serialVersionUID = 2600108403441889338L;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 手机号
     */
    private String phoneNo;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 地址
     */
    private String address;

    /**
     * 网站网址
     */
    private String ipAddress;

    /**
     * 简介
     */
    private String remarks;
}

package com.monco.core.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Auther: monco
 * @Date: 2019/4/26 12:18
 * @Description: 设备实体类
 */
@Entity
@Getter
@Setter
@Table(name = "sys_equipment")
public class Equipment extends BaseEntity<Long> {

    private static final long serialVersionUID = -4740593583148581607L;

    /**
     * 设备码
     */
    private String equipmentCode;

    /**
     * 设备名
     */
    private String equipmentName;

    /**
     * 价格
     */
    private Double price;

    /**
     * 位置
     */
    private String position;

    /**
     * 生产日期
     */
    private Date productDate;

    /**
     * 到期日期
     */
    private Date dueDate;

    /**
     * 设备类型
     */
    private Integer equipmentType;
}

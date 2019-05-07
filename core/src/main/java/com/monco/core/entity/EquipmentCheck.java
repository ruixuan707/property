package com.monco.core.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Auther: monco
 * @Date: 2019/4/29 15:44
 * @Description: 设备盘点
 */
@Entity
@Getter
@Setter
@Table(name = "sys_equipment_check")
public class EquipmentCheck extends BaseEntity<Long> {

    private static final long serialVersionUID = 6004219230053057411L;

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

    /**
     * 盘点日期
     */
    private Date checkDate;
}

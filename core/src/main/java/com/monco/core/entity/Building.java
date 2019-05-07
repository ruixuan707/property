package com.monco.core.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Auther: monco
 * @Date: 2019/5/5 03:01
 * @Description: 楼房
 */
@Entity
@Getter
@Setter
@Table(name = "sys_building")
public class Building extends BaseEntity<Long> {

    private static final long serialVersionUID = 7720990539536902444L;

    /**
     * 名称
     */
    private String buildingName;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 楼房类型 1 大楼 2 单元 3 房间号
     */
    private Integer buildingType;
}

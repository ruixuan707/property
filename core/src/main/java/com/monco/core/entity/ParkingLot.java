package com.monco.core.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: monco
 * @Date: 2019/5/6 20:41
 * @Description: 停车位
 */
@Entity
@Getter
@Setter
@Table(name = "sys_parking_lot")
public class ParkingLot extends BaseEntity<Long> {

    private static final long serialVersionUID = 2854811455138179722L;

    /**
     * 车位名称
     */
    private String lotName;

    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 到期时间
     */
    private Date endDate;

    /**
     * 车位照片
     */
    private String pic;

    /**
     * 车位价格
     */
    private BigDecimal money;

    /**
     * 绑定用户id
     */
    private Long userId;

    /**
     * 车位状态 0 未被购买  1 已被购买
     */
    private Integer lotStatus;

    @Transient
    public Integer getLotStatus() {
        return lotStatus;
    }

    /**
     * 购买人账号
     */
    private String userName;

    @Transient
    public String getUserName() {
        return userName;
    }

    /**
     * 购买人昵称
     */
    private String nickName;

    @Transient
    public String getNickName() {
        return nickName;
    }
}

package com.monco.core.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: monco
 * @Date: 2019/5/5 16:11
 * @Description: 花费缴费
 */
@Entity
@Getter
@Setter
@Table(name = "sys_cost")
public class Cost extends BaseEntity<Long> {

    private static final long serialVersionUID = -4142844409785477988L;

    /**
     * 缴费人姓名
     */
    private String nickName;

    /**
     * 缴纳费用
     */
    private BigDecimal money;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 缴费类型 0 水电 1 车位
     */
    private Integer costType;

    @Transient
    public Date getStartDate() {
        return startDate;
    }

    @Transient
    public Date getEndDate() {
        return endDate;
    }
}

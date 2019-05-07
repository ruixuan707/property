package com.monco.core.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Auther: monco
 * @Date: 2019/4/29 14:56
 * @Description: 操作记录
 */
@Entity
@Getter
@Setter
@Table(name = "sys_operation_record")
public class OperationRecord extends BaseEntity<Long> {

    private static final long serialVersionUID = 2683923449307437839L;

    /**
     * 操作类型 0 设备维系记录
     */
    private Integer operationType;

    /**
     * 操作标题
     */
    private String title;

    /**
     * 操作内容
     */
    private String content;

}

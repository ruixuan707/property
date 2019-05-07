package com.monco.core.page;

import com.monco.core.entity.Building;
import lombok.Data;

import java.util.List;

/**
 * @Auther: monco
 * @Date: 2019/5/5 03:07
 * @Description:
 */
@Data
public class BuildingPage extends BasePage {

    private static final long serialVersionUID = 5663147915895595562L;

    /**
     * 楼房名字
     */
    private String buildingName;

    /**
     * 单元名称
     */
    private String unitName;

    /**
     * 房间名称
     */
    private String homeName;

    /**
     * 楼房类型 0 大楼 1 单元 2 房间号
     */
    private Integer buildingType;

    /**
     * 父级ID
     */
    private Long parentId;
}

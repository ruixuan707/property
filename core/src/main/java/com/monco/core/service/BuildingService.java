package com.monco.core.service;

import com.monco.core.entity.Building;

import java.util.List;

/**
 * @Auther: monco
 * @Date: 2019/5/5 03:04
 * @Description:
 */
public interface BuildingService extends BaseService<Building, Long> {

    /**
     * 唯一性校验
     *
     * @param buildingName
     * @param buildingType
     * @return
     */
    Building validate(String buildingName, Integer buildingType, Long parentId);

    /**
     * 按照父级id查找子集
     *
     * @param parentId
     * @return
     */
    List<Building> getBuildingByParentId(Long parentId);

    /**
     * 根据当前id获取所有子集id
     *
     * @param id
     * @return
     */
    List<Building> getChildrenBuilding(Long id);
}

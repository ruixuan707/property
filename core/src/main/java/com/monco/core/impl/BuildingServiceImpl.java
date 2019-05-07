package com.monco.core.impl;

import com.monco.common.bean.ConstantUtils;
import com.monco.core.dao.BuildingDao;
import com.monco.core.entity.Building;
import com.monco.core.service.BuildingService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: monco
 * @Date: 2019/5/5 03:04
 * @Description:
 */
@Service
public class BuildingServiceImpl extends BaseServiceImpl<Building, Long> implements BuildingService {

    @Autowired
    BuildingDao buildingDao;

    @Override
    public Building validate(String buildingName, Integer buildingType, Long parentId) {
        Building building = new Building();
        building.setDataDelete(ConstantUtils.UN_DELETE);
        building.setBuildingName(buildingName);
        building.setBuildingType(buildingType);
        building.setParentId(parentId);
        Example<Building> buildingExample = Example.of(building);
        List<Building> buildingList = this.findAll(buildingExample, Sort.by("id"));
        if (CollectionUtils.isNotEmpty(buildingList)) {
            return buildingList.get(0);
        }
        return null;
    }

    @Override
    public List<Building> getBuildingByParentId(Long parentId) {
        Building building = new Building();
        building.setParentId(parentId);
        building.setDataDelete(ConstantUtils.UN_DELETE);
        Example<Building> buildingExample = Example.of(building);
        List<Building> buildingList = this.findAll(buildingExample, Sort.by("id"));
        return buildingList;
    }

    @Override
    public List<Building> getChildrenBuilding(Long id) {
        List<Building> buildingList = getBuildingByParentId(id);
        return buildingList;
    }

}

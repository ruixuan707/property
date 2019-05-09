package com.monco.api;

import com.monco.common.bean.ApiResult;
import com.monco.common.bean.ConstantUtils;
import com.monco.core.entity.Building;
import com.monco.core.entity.Message;
import com.monco.core.page.BuildingPage;
import com.monco.core.page.PageResult;
import com.monco.core.query.MatchType;
import com.monco.core.query.OrderQuery;
import com.monco.core.query.QueryParam;
import com.monco.core.service.BuildingService;
import com.sun.org.apache.bcel.internal.generic.LUSHR;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: monco
 * @Date: 2019/5/5 03:05
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("building")
public class BuildingController {

    @Autowired
    BuildingService buildingService;

    @PostMapping
    public ApiResult save(@RequestBody Building building) {
        if (building.getBuildingType() == 1) {
            building.setParentId(0L);
        }
        Building validate = buildingService.validate(building.getBuildingName(), building.getBuildingType(), building.getParentId());
        if (validate != null) {
            return ApiResult.error("该楼盘已存在");
        }
        buildingService.save(building);
        return ApiResult.ok();
    }


    @GetMapping
    public ApiResult getOne(@RequestParam Long id) {
        Building building = buildingService.find(id);
        BuildingPage buildingPage = new BuildingPage();
        entityToPage(building, buildingPage);
        return ApiResult.ok(buildingPage);
    }

    @DeleteMapping
    public ApiResult delete(@RequestParam Long id) {
        List<Building> list = buildingService.getChildrenBuilding(id);
        Building building = buildingService.find(id);
        list.add(building);
        for (Building b : list) {
            b.setDataDelete(ConstantUtils.DELETE);
        }
        buildingService.saveCollection(list);
        return ApiResult.ok();
    }

    @GetMapping("all")
    public ApiResult all(@RequestParam Long id) {
        List<Building> buildingList = buildingService.getChildrenBuilding(id);
        List<BuildingPage> buildingPageList = new ArrayList<>();
        for (Building b : buildingList) {
            BuildingPage p = new BuildingPage();
            entityToPage(b, p);
            buildingPageList.add(p);
        }
        return ApiResult.ok(buildingPageList);
    }

    @GetMapping("list")
    public ApiResult list(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                          @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                          BuildingPage buildingPage, OrderQuery orderQuery) {
        if (pageSize == 0) {
            Building building = new Building();
            building.setDataDelete(ConstantUtils.UN_DELETE);
            building.setParentId(buildingPage.getParentId());
            building.setBuildingType(buildingPage.getBuildingType());
            Example<Building> buildingExample = Example.of(building);
            List<Building> buildingList = buildingService.findAll(buildingExample, Sort.by("id"));
            List<BuildingPage> buildingPageList = new ArrayList<>();
            for (Building b : buildingList) {
                BuildingPage p = new BuildingPage();
                entityToPage(b, p);
                buildingPageList.add(p);
            }
            return ApiResult.ok(buildingPageList);
        }
        List<QueryParam> params = new ArrayList<>();
        QueryParam queryParam = new QueryParam("dataDelete", MatchType.equal, ConstantUtils.UN_DELETE);
        params.add(queryParam);
        // 楼房类型
        if (buildingPage.getBuildingType() != null) {
            queryParam = new QueryParam("buildingType", MatchType.equal, buildingPage.getBuildingType());
            params.add(queryParam);
        }
        Page<Building> result = buildingService.findPage(pageSize, currentPage, params, orderQuery.getOrderType(), orderQuery.getOrderField());
        List<Building> buildingList = result.getContent();
        List<BuildingPage> buildingPageList = new ArrayList<>();
        for (Building b : buildingList) {
            BuildingPage p = new BuildingPage();
            entityToPage(b, p);
            buildingPageList.add(p);
        }
        PageResult pageResult = new PageResult(result.getPageable(), buildingPageList, result.getTotalElements());
        return ApiResult.ok(pageResult);
    }

    public void entityToPage(Building building, BuildingPage buildingPage) {
        BeanUtils.copyProperties(building, buildingPage);
        buildingPage.setId(building.getId());
        if (building.getBuildingType() == 2) {
            Building topBuilding = buildingService.find(building.getParentId());
            buildingPage.setBuildingName(topBuilding.getBuildingName());
            buildingPage.setUnitName(building.getBuildingName());
        }
        if (building.getBuildingType() == 3) {
            Building parentBuilding = buildingService.find(building.getParentId());
            Building topBuilding = buildingService.find(parentBuilding.getParentId());
            buildingPage.setBuildingName(topBuilding.getBuildingName());
            buildingPage.setUnitName(parentBuilding.getBuildingName());
            buildingPage.setHomeName(building.getBuildingName());
        }
    }
}

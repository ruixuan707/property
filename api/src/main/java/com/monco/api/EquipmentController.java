package com.monco.api;

import com.monco.common.bean.ApiResult;
import com.monco.common.bean.ConstantUtils;
import com.monco.core.entity.Equipment;
import com.monco.core.entity.Message;
import com.monco.core.query.MatchType;
import com.monco.core.query.OrderQuery;
import com.monco.core.query.QueryParam;
import com.monco.core.service.EquipmentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: monco
 * @Date: 2019/4/29 14:50
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("equipment")
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;

    @PostMapping
    public ApiResult save(@RequestBody Equipment equipment) {
        equipmentService.save(equipment);
        return ApiResult.ok();
    }

    @DeleteMapping
    public ApiResult delete(@RequestParam Long id) {
        Equipment equipment = equipmentService.find(id);
        equipment.setDataDelete(ConstantUtils.DELETE);
        equipmentService.save(equipment);
        return ApiResult.ok();
    }

    @GetMapping
    public ApiResult getOne(@RequestParam Long id) {
        Equipment equipment = equipmentService.find(id);
        return ApiResult.ok(equipment);
    }

    @GetMapping("list")
    public ApiResult list(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                          @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                          Equipment equipment, OrderQuery orderQuery) {
        List<QueryParam> params = new ArrayList<>();
        QueryParam queryParam = new QueryParam("dataDelete", MatchType.equal, ConstantUtils.UN_DELETE);
        params.add(queryParam);
        // 设备名字模糊查询
        if (StringUtils.isNotBlank(equipment.getEquipmentName())) {
            queryParam.setFiled("equipmentName").setMatchType(MatchType.like).setValue(equipment.getEquipmentName());
            params.add(queryParam);
        }
        Page<Equipment> result = equipmentService.findPage(pageSize, currentPage, params, orderQuery.getOrderType(), orderQuery.getOrderField());
        return ApiResult.ok(result);
    }
}

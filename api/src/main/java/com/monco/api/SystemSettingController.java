package com.monco.api;

import com.monco.common.bean.ApiResult;
import com.monco.core.entity.ParkingLot;
import com.monco.core.entity.SystemSetting;
import com.monco.core.service.SystemSettingService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: monco
 * @Date: 2019/5/6 22:45
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("system-setting")
public class SystemSettingController {

    @Autowired
    SystemSettingService systemSettingService;

    @PostMapping
    public ApiResult save(@RequestBody SystemSetting systemSetting) {
        systemSettingService.save(systemSetting);
        return ApiResult.ok();
    }

    @GetMapping
    public ApiResult getOne() {
        SystemSetting systemSetting = systemSettingService.find(1L);
        return ApiResult.ok(systemSetting);
    }
}

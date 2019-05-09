package com.monco.api;

import com.monco.common.bean.ApiResult;
import com.monco.common.bean.ConstantUtils;
import com.monco.core.entity.Cost;
import com.monco.core.entity.Message;
import com.monco.core.entity.User;
import com.monco.core.manager.UserManager;
import com.monco.core.query.MatchType;
import com.monco.core.query.OrderQuery;
import com.monco.core.query.QueryParam;
import com.monco.core.service.CostService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: monco
 * @Date: 2019/5/5 16:15
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("cost")
public class CostController {

    @Autowired
    CostService costService;

    @PostMapping
    public ApiResult save(@RequestBody Cost cost) {
        User user = UserManager.get();
        if (user == null) {
            return ApiResult.error("请登录");
        }
        cost.setNickName(user.getNickName());
        costService.save(cost);
        return ApiResult.ok();
    }

    @GetMapping("list")
    public ApiResult list(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                          @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                          Cost cost, OrderQuery orderQuery) {
        List<QueryParam> params = new ArrayList<>();
        QueryParam queryParam = new QueryParam("dataDelete", MatchType.equal, ConstantUtils.UN_DELETE);
        params.add(queryParam);
        // 缴费用户
        if (cost.getCreatedId()!=null) {
            queryParam = new QueryParam("createdId", MatchType.equal, cost.getCreatedId());
            params.add(queryParam);
        }
        // 开始时间
        if (cost.getStartDate() != null) {
            queryParam = new QueryParam("createDate", MatchType.greaterThanOrEqualTo, cost.getStartDate());
            params.add(queryParam);
        }
        // 结束时间
        if (cost.getEndDate() != null) {
            queryParam = new QueryParam("createDate", MatchType.lessThanOrEqualTo, cost.getEndDate());
            params.add(queryParam);
        }
        Page<Cost> result = costService.findPage(pageSize, currentPage, params, orderQuery.getOrderType(), orderQuery.getOrderField());
        return ApiResult.ok(result);
    }

    @GetMapping("user-list")
    public ApiResult userList(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                              @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                              Cost cost, OrderQuery orderQuery) {
        List<QueryParam> params = new ArrayList<>();
        QueryParam queryParam = new QueryParam("dataDelete", MatchType.equal, ConstantUtils.UN_DELETE);
        params.add(queryParam);
        // 缴费用户
        User user = UserManager.get();
        if (user != null) {
            queryParam = new QueryParam("createdName", MatchType.equal, user.getUsername());
            params.add(queryParam);
        }
        // 开始时间
        if (cost.getStartDate() != null) {
            queryParam = new QueryParam("createDate", MatchType.greaterThanOrEqualTo, cost.getStartDate());
            params.add(queryParam);
        }
        // 结束时间
        if (cost.getEndDate() != null) {
            queryParam = new QueryParam("createDate", MatchType.lessThanOrEqualTo, cost.getEndDate());
            params.add(queryParam);
        }
        Page<Cost> result = costService.findPage(pageSize, currentPage, params, orderQuery.getOrderType(), orderQuery.getOrderField());
        return ApiResult.ok(result);
    }
}

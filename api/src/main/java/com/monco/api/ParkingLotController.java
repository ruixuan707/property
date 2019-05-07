package com.monco.api;

import com.monco.common.bean.ApiResult;
import com.monco.common.bean.ConstantUtils;
import com.monco.core.entity.Cost;
import com.monco.core.entity.ParkingLot;
import com.monco.core.entity.User;
import com.monco.core.manager.UserManager;
import com.monco.core.query.MatchType;
import com.monco.core.query.OrderQuery;
import com.monco.core.query.QueryParam;
import com.monco.core.service.ParkingLotService;
import com.monco.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: monco
 * @Date: 2019/5/6 22:18
 * @Description: 停车位Controller
 */
@Slf4j
@RestController
@RequestMapping("parking-lot")
public class ParkingLotController {

    @Autowired
    ParkingLotService parkingLotService;

    @Autowired
    UserService userService;

    @PostMapping
    public ApiResult save(@RequestBody ParkingLot parkingLot) {
        parkingLotService.save(parkingLot);
        return ApiResult.ok();
    }

    @PutMapping
    public ApiResult update(@RequestBody ParkingLot parkingLot) {
        User user = UserManager.get();
        parkingLot.setUserId(user.getId());
        parkingLotService.save(parkingLot);
        return ApiResult.ok();
    }

    @GetMapping
    public ApiResult getOne(@RequestParam Long id) {
        ParkingLot parkingLot = parkingLotService.find(id);
        if (parkingLot.getStartDate() == null && parkingLot.getEndDate() == null) {
            parkingLot.setLotStatus(ConstantUtils.NUM_0);
        } else {
            parkingLot.setLotStatus(ConstantUtils.NUM_1);
        }
        if (parkingLot.getUserId() != null) {
            User user = userService.find(parkingLot.getUserId());
            parkingLot.setUserName(user.getUsername());
            parkingLot.setNickName(user.getNickName());
        }
        return ApiResult.ok(parkingLot);
    }

    @GetMapping("list")
    public ApiResult list(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                          @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                          ParkingLot parkingLot, OrderQuery orderQuery) {
        if (pageSize == 0) {
            ParkingLot parking = new ParkingLot();
            parking.setDataDelete(ConstantUtils.UN_DELETE);
            Example<ParkingLot> parkingLotExample = Example.of(parking);
            List<ParkingLot> parkingLotList = parkingLotService.findAll(parkingLotExample, Sort.by("id"));
            List<ParkingLot> resultList = new ArrayList<>();
            for (ParkingLot pl : parkingLotList) {
                if (pl.getEndDate() == null || pl.getEndDate().before(new Date())) {
                    pl.setLotStatus(ConstantUtils.NUM_0);
                    resultList.add(pl);
                }
            }
            return ApiResult.ok(resultList);
        }
        List<QueryParam> params = new ArrayList<>();
        QueryParam queryParam = new QueryParam("dataDelete", MatchType.equal, ConstantUtils.UN_DELETE);
        params.add(queryParam);
        Page<ParkingLot> result = parkingLotService.findPage(pageSize, currentPage, params, orderQuery.getOrderType(), orderQuery.getOrderField());
        List<ParkingLot> parkingLots = result.getContent();
        for (ParkingLot lot : parkingLots) {
            if (parkingLot.getStartDate() == null && parkingLot.getEndDate() == null || parkingLot.getEndDate().before(new Date())) {
                lot.setLotStatus(ConstantUtils.NUM_0);
            } else {
                lot.setLotStatus(ConstantUtils.NUM_1);
            }
            if (lot.getUserId() != null) {
                User user = userService.find(lot.getUserId());
                lot.setUserName(user.getUsername());
                lot.setNickName(user.getNickName());
            }
        }
        return ApiResult.ok(result);
    }

    @GetMapping("user-list")
    public ApiResult userList(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                              @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                              ParkingLot parkingLot, OrderQuery orderQuery) {
        List<QueryParam> params = new ArrayList<>();
        QueryParam queryParam = new QueryParam("dataDelete", MatchType.equal, ConstantUtils.UN_DELETE);
        params.add(queryParam);
        User currentUser = UserManager.get();
        if (currentUser != null) {
            queryParam = new QueryParam("userId", MatchType.equal, currentUser.getId());
            params.add(queryParam);
        }
        Page<ParkingLot> result = parkingLotService.findPage(pageSize, currentPage, params, orderQuery.getOrderType(), orderQuery.getOrderField());
        List<ParkingLot> parkingLots = result.getContent();
        for (ParkingLot lot : parkingLots) {
            if (parkingLot.getStartDate() == null && parkingLot.getEndDate() == null || parkingLot.getEndDate().before(new Date())) {
                lot.setLotStatus(ConstantUtils.NUM_0);
            } else {
                lot.setLotStatus(ConstantUtils.NUM_1);
            }
            if (lot.getUserId() != null) {
                User user = userService.find(lot.getUserId());
                lot.setUserName(user.getUsername());
                lot.setNickName(user.getNickName());
            }
        }
        return ApiResult.ok(result);
    }

}

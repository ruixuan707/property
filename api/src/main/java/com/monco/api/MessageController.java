package com.monco.api;

import com.monco.common.bean.ApiResult;
import com.monco.common.bean.ConstantUtils;
import com.monco.core.entity.Message;
import com.monco.core.entity.User;
import com.monco.core.query.OrderQuery;
import com.monco.core.query.QueryParam;
import com.monco.core.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * @Auther: monco
 * @Date: 2019/4/26 12:03
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @PostMapping
    public ApiResult save(@RequestBody Message message) {
        messageService.save(message);
        return ApiResult.ok();
    }

    @PutMapping
    public ApiResult update(@RequestBody Message message) {
        messageService.save(message);
        return ApiResult.ok();
    }

    @GetMapping
    public ApiResult getOne(@RequestParam Long id) {
        Message message = messageService.find(id);
        return ApiResult.ok(message);
    }

    @GetMapping("list")
    public ApiResult list(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                          @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                          Message message, OrderQuery orderQuery) {
        Page<Message> result = messageService.findPage(pageSize, currentPage, new ArrayList<>(), orderQuery.getOrderType(), orderQuery.getOrderField());
        return ApiResult.ok(result);
    }

    @DeleteMapping
    public ApiResult delete(@RequestParam Long id) {
        Message message = messageService.find(id);
        message.setDataDelete(ConstantUtils.DELETE);
        messageService.save(message);
        return ApiResult.ok();
    }

}

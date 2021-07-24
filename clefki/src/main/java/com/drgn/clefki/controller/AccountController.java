package com.drgn.clefki.controller;

import com.drgn.clefki.service.inter.AccountService;
import com.drgn.common.exception.ParamException;
import com.drgn.common.model.VO.AccountVO;
import com.drgn.common.model.query.AccountQuery;
import com.drgn.common.model.web.Result;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 账号相关 控制器
 *
 * @author wydrgn
 * @date 2021/7/24 11:45
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    AccountService accountService;

    @GetMapping("/{id}")
    public Result get(@PathVariable String id) throws NotFoundException {
        return Result.SUCCESS(accountService.get(id));
    }

    @GetMapping("/list")
    public Result get(AccountQuery aq) throws ParamException {
        return Result.SUCCESS(accountService.list(aq));
    }

    @PostMapping
    public Result save(@RequestBody AccountVO accountVO) {
        return Result.SUCCESS(accountService.save(accountVO));
    }

    @PutMapping
    public Result update(@RequestBody AccountVO accountVO) {
        return Result.SUCCESS(accountService.update(accountVO));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        return Result.SUCCESS(accountService.delete(id));
    }
}

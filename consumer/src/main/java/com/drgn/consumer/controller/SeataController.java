package com.drgn.consumer.controller;

import com.drgn.common.model.DTO.MyPmDTO;
import com.drgn.common.model.MyPm;
import com.drgn.common.model.User;
import com.drgn.common.model.web.Result;
import com.drgn.consumer.feign.astronaut.service.inter.UserService;
import com.drgn.consumer.feign.drgnmon.service.inter.MyPmService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: seata分布式事务整合 测试接口
 * @author: wydrgn
 * @createDate: 2021-06-24 17:21
 */
@RestController
@RequestMapping("seata")
public class SeataController {
    @Resource
    MyPmService myPmService;
    @Resource
    UserService userService;

    /**
     * 测试分布式事务回滚效果
     *
     * @param myPmDTO
     * @return
     */
    //@GlobalTransactional
    @PostMapping("globalTransactional")
    public Result globalTransactional(@RequestBody MyPmDTO myPmDTO) {
        // 更新drgnmon服务
        MyPm myPm = new MyPm();
        myPm.setId("1");
        myPm.setFlash(myPmDTO.isFlash());
        myPm.setNationalId(myPmDTO.getNationalId());
        myPm.setUserId(myPmDTO.getUserId());
        myPmService.save(myPm);
        // 更新astronaut服务
        String userId = myPmDTO.getUserId();
        User user = userService.get(userId);
        user.setDrgnmonFlag("1");
        userService.update(user);
        // 抛出异常 测试回滚
        //int errorNum = 1 / 0;
        return Result.SUCCESS("执行结束");
    }

}

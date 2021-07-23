package com.drgn.common.controller;

import com.drgn.common.exception.ParamException;
import com.drgn.common.model.DTO.EurekaServiceRequestDTO;
import com.drgn.common.model.web.Result;
import com.drgn.common.service.inter.RequestService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/request")
@Validated
public class RequestController {

    @Resource
    RequestService requestService;

    //@CrossOrigin
    @PostMapping("/post")
    public Result post(@Valid @RequestBody EurekaServiceRequestDTO eurekaServiceRequestDTO) throws ParamException {
        return Result.SUCCESS(requestService.post(eurekaServiceRequestDTO));
    }

    @PostMapping("/get")
    public Result get(@Valid @RequestBody EurekaServiceRequestDTO eurekaServiceRequestDTO) throws ParamException {
        return Result.SUCCESS(requestService.get(eurekaServiceRequestDTO));
    }

    @PostMapping("/delete")
    public Result delete(@Valid @RequestBody EurekaServiceRequestDTO eurekaServiceRequestDTO) throws ParamException {
        return Result.SUCCESS(requestService.delete(eurekaServiceRequestDTO));
    }

    @PostMapping("/put")
    public Result put(@Valid @RequestBody EurekaServiceRequestDTO eurekaServiceRequestDTO) throws ParamException {
        return Result.SUCCESS(requestService.put(eurekaServiceRequestDTO));
    }
}

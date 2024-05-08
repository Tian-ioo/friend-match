package com.briup.product_source.controller;

import com.briup.product_source.result.Result;
import com.briup.product_source.service.ManagerBatchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "批次记录模块")
@RestController
@RequestMapping("/batch")
public class BatchController {

    @Autowired
    private ManagerBatchService managerBatchService;

    @ApiOperation("查询所有批次信息接口")
    @GetMapping("/queryAll")
    public Result queryAllBatches() {
        return Result.success(managerBatchService.findAll());
    }

    @ApiOperation("查询所有未检疫的批次信息接口")
    @GetMapping("/queryAllUnquarantined")
    public Result queryAllUnquarantined() {
        return Result.success(managerBatchService.findAllUnquarantined());
    }

}
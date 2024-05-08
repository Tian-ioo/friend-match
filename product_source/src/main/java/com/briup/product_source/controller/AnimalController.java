package com.briup.product_source.controller;

import com.briup.product_source.pojo.ManagerAnimal;
import com.briup.product_source.pojo.ext.ManagerAnimalExt;
import com.briup.product_source.result.Result;
import com.briup.product_source.service.ManagerAnimalService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "动物管理模块")
@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private ManagerAnimalService managerAnimalService;

    @ApiOperation("分页多条件查询动物信息以及对应批次和栏圈信息接口")
    @GetMapping
    public Result getAnimalRelated(Integer pageNum, int pageSize, String aHealthy, String aGender) {
        PageInfo<ManagerAnimalExt> result = managerAnimalService.findByPage(pageNum, pageSize, aHealthy, aGender);
        return Result.success(result);
    }

    @ApiOperation("新增或修改动物接口")
    @PostMapping("/saveOrUpdate")
    public Result reviseAnimalInfo(@RequestBody ManagerAnimal animal) {
        managerAnimalService.saveOrUpdate(animal);
        return Result.success();
    }
}

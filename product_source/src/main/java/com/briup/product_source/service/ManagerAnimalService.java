package com.briup.product_source.service;

import com.briup.product_source.pojo.ManagerAnimal;
import com.briup.product_source.pojo.ext.ManagerAnimalExt;
import com.github.pagehelper.PageInfo;

public interface ManagerAnimalService {
    /**
     * 多条件分页查询
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @param aHealthy 健康状态
     * @param aGender 性别
     * @return 动物及其关联信息的分页信息
     */
    PageInfo<ManagerAnimalExt> findByPage(Integer pageNum, int pageSize, String aHealthy, String aGender);

    /**
     * 新增或修改动物信息
     * @param animal 动物信息
     */
    void saveOrUpdate(ManagerAnimal animal);

}

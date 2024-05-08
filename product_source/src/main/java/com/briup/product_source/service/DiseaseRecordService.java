package com.briup.product_source.service;

import com.briup.product_source.pojo.DiseaseRecord;
import com.briup.product_source.pojo.ManagerDisease;
import com.briup.product_source.pojo.ext.DiseaseRecordExt;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DiseaseRecordService {
    /**
     * 查询所有病症类型信息
     * @return
     */
    List<ManagerDisease> findAll();

    /**
     * 分页多条件查询多条病症记录，病症记录中包含病症类型信息
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @param drStatus 治疗状态
     * @param drDId 病症类型编号
     * @return 分页数据  思考如何设计DiseaseRecordExt表实现包含病症类型信息
     */
    PageInfo<DiseaseRecordExt> findByPage(Integer pageNum, Integer pageSize, String drStatus, Integer drDId);

    /**
     * 新增或更新病症记录
     * @param record 病症记录信息
     */
    void saveOrUpdate(DiseaseRecord record);
}

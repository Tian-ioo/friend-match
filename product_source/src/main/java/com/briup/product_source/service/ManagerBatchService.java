package com.briup.product_source.service;

import com.briup.product_source.pojo.ManagerBatch;

import java.util.List;

public interface ManagerBatchService {

    /**
     * 查询所有批次信息
     * @return 所有批次信息
     */
    List<ManagerBatch> findAll();

    /**
     * 查询所有未检疫以及检疫未合格的批次信息
     * @return 未检疫以及检疫未合格的批次信息
     */
    List<ManagerBatch> findAllUnquarantined();
}

package com.briup.product_source.service;

import com.briup.product_source.pojo.ManagerHurdles;
import com.briup.product_source.pojo.ext.ManagerHurdlesExt;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface HurdlesService {
    /**
     * 查询所有栏圈信息
     * @return 所有栏圈信息
     */
    List<ManagerHurdles> findAllEnable();

    /**
     * 查询所有的栏圈容量
     * @return
     */
    List<Integer> findAllMax();

    /**
     * 条件分页查询所有栏圈信息
     * @param pageNum
     * @param pageSize
     * @param hName
     * @param hMax
     * @param fhName
     * @param hEnable
     * @return
     */
    PageInfo<ManagerHurdlesExt> findByPage(Integer pageNum, Integer pageSize, String hName, Integer hMax, String fhName, String hEnable);

    /**
     * 根据栏圈编号禁用/启用栏圈
     * @param hId
     * @param hEnable
     */
    void modifyStatus(String hId, String hEnable);

    /**
     * 批量禁用/启用栏圈
     * @param idAndStatus
     */
    void modifyStatusBatch(List<Map<String, String>> idAndStatus);

}

package com.briup.product_source.service;

import java.util.List;
import java.util.Map;

public interface AnalysisService {

    /**
     * 统计栏舍数量(对应manager_fence_house表)
     * 栏圈数量(对应manager_hurdles表)
     * 动物数量(对应manager_animal表)
     * 冷库数量(对应manager_freezer表)
     * 员工数量(对应base_account表)
     * @return
     */
    Map<String, List<Object>> countNum();


    /**
     * 指标记录更改动物体重，根据动物表的批次获取对应体重的猪的数量，
     * 使用manager_animal表中a_weight字段存储动物体重
     * 划分类型为 [0-30)斤猪的数量
     * [30-50)斤的猪数量
     * 50斤及以上的数量
     * @return
     */
    Map<String,Integer> countWeight();


    /**
     * 根据病症类型统计对应的动物数量
     * @return
     */
    Map<String, Long> countDisease();


    /**
     * 统计获取1~12月的销售数量
     * 在出单记录表issue_record中根据isr_time出单时间统计isr_num表示销售数量，
     * @return
     */
    Map<String,List<Object>> countSales();

}

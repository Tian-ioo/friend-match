package com.briup.product_source.service.impl;

import com.briup.product_source.dao.IssueRecordMapper;
import com.briup.product_source.dao.ManagerAnimalMapper;
import com.briup.product_source.dao.ManagerDiseaseMapper;
import com.briup.product_source.dao.ManagerFenceHouseMapper;
import com.briup.product_source.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    private ManagerAnimalMapper managerAnimalMapper;

    @Autowired
    private ManagerDiseaseMapper managerDiseaseMapper;

    @Autowired
    private ManagerFenceHouseMapper managerFenceHouseMapper;

    @Autowired
    private IssueRecordMapper issueRecordMapper;

    @Override
    public Map<String, List<Object>> countNum() {
        List<Object> resourceList = Arrays.asList("栏舍", "栏圈", "动物", "冷库", "员工");
        List<Object> valueList = managerFenceHouseMapper.countAllResources();
        HashMap<String, List<Object>> map = new HashMap<>();
        map.put("name", resourceList);
        map.put("value", valueList);
        return map;
    }

    @Override
    public Map<String, Integer> countWeight() {
        return managerAnimalMapper.countWeight();
    }

    @Override
    public Map<String, Long> countDisease() {
        List<Map<String, Object>> mapList = managerDiseaseMapper.countDisease();
        HashMap<String, Long> map = new HashMap<>();
        mapList.forEach(e -> {
            map.put(String.valueOf(e.get("d_name")), (Long) e.get("sum"));
        });
        return map;
    }

    @Override
    public Map<String, List<Object>> countSales() {
        // 月份列表
        String[] month = {"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};
        List<Object> monthList = Arrays.asList(month);
        // 对应销售总数列表
        Integer[] sales = new Integer[12];
        // 初始化：全0填充
        Arrays.fill(sales, 0);
        List<Object> salesList = Arrays.asList(sales);
        List<Map<String, Integer>> baseData = issueRecordMapper.countSales();
        baseData.forEach(e ->
                salesList.set(e.get("月份") - 1, Integer.parseInt(String.valueOf(e.get("销售总数"))))
        );
        // 创建总结果集map
        HashMap<String, List<Object>> map = new HashMap<>();
        map.put("name", monthList);
        map.put("value", salesList);
        System.out.println(map);
        return map;
    }
}

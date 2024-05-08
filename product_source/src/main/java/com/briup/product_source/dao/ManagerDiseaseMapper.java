package com.briup.product_source.dao;

import com.briup.product_source.pojo.ManagerDisease;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ManagerDiseaseMapper {

    int insert(ManagerDisease record);

    int updateByPrimaryKey(ManagerDisease record);

    List<Map<String, Object>> countDisease();

    List<ManagerDisease> selectAllByDiseases();
}
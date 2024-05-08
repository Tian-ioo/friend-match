package com.briup.product_source.dao;

import com.briup.product_source.pojo.ManagerAnimal;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ManagerAnimalMapper {

    int insert(ManagerAnimal record);

    ManagerAnimal selectByPrimaryKey(String aAnimalId);

    int updateByPrimaryKey(ManagerAnimal record);
    Map<String, Integer> countWeight();

    int updateAHealthyByAAnimalId(@Param("aHealthy") String aHealthy,
                                  @Param("aAnimalId") String aAnimalId);
}
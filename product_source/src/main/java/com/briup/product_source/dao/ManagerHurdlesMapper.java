package com.briup.product_source.dao;

import com.briup.product_source.pojo.ManagerHurdles;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerHurdlesMapper {
    int selectCountByFhId(String fhId);
    List<ManagerHurdles> selectAllEnableHurdles();
    List<Integer> selectAllMax();

    ManagerHurdles selectByPrimaryKey(String hId);

    int updateByPrimaryKey(ManagerHurdles managerHurdles);

    int updateHEnable(@Param("hId") String hId,
                     @Param("hEnable") String hEnable);
}

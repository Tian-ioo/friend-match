package com.briup.product_source.dao;

import com.briup.product_source.pojo.ManagerBatch;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerBatchMapper {

    List<ManagerBatch> selectAllUnquarantined();

    ManagerBatch selectByPrimaryKey(String bSerialId);

    int updateBQuarantineAndBQualified(@Param("bQualified") String bQualified,
                                       @Param("bSerialId") String bSerialId);


    List<ManagerBatch> selectAllBatches();
}
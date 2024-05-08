package com.briup.product_source.dao.ext;

import com.briup.product_source.pojo.ext.ManagerAnimalExt;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerAnimalExtMapper {
    List<ManagerAnimalExt> selectAnimalRelated(@Param("aHealthy") String aHealthy, @Param("aGender") String aGender);
}

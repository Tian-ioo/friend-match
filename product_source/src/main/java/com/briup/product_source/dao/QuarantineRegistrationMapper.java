package com.briup.product_source.dao;

import com.briup.product_source.pojo.QuarantineRegistration;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuarantineRegistrationMapper {
    int insert(QuarantineRegistration record);

    int updateByPrimaryKey(QuarantineRegistration record);

    List<QuarantineRegistration> selectAllRecord(@Param("grMechanism") String grMechanism,
                                                @Param("bQualified") String bQualified);
}
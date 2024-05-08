package com.briup.product_source.service;

import com.briup.product_source.pojo.QuarantineRegistration;
import com.github.pagehelper.PageInfo;

public interface QuarantineRegistrationService {
    /**
     * 根据批次编号，检疫机构，检疫结果，分页查询检疫记录
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @param grMechanism 检疫机构名称
     * @param bQualified 检疫结果 合格、不合格
     * @return
     */
    PageInfo<QuarantineRegistration> findByPage(Integer pageNum, Integer pageSize, String grMechanism, String bQualified);

    /**
     * 新增或修改检疫记录
     * @param qr 检疫信息对象
     */
    void saveOrUpdate(QuarantineRegistration qr);
}

package com.briup.product_source.service.impl;

import com.briup.product_source.dao.ManagerBatchMapper;
import com.briup.product_source.dao.QuarantineRegistrationMapper;
import com.briup.product_source.exception.ServiceException;
import com.briup.product_source.pojo.QuarantineRegistration;
import com.briup.product_source.result.ResultCode;
import com.briup.product_source.service.QuarantineRegistrationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class QuarantineRegistrationServiceImpl implements QuarantineRegistrationService {

    @Autowired
    private QuarantineRegistrationMapper quarantineRegistrationMapper;

    @Autowired
    private ManagerBatchMapper managerBatchMapper;


    @Override
    public PageInfo<QuarantineRegistration> findByPage(Integer pageNum, Integer pageSize, String grMechanism, String bQualified) {
        // 分页详情
        PageHelper.startPage(pageNum, pageSize);
        List<QuarantineRegistration> all = quarantineRegistrationMapper.selectAllRecord(grMechanism, bQualified);
        // 分页对象
        PageInfo<QuarantineRegistration> pageInfo = new PageInfo<>(all);
        return pageInfo;
    }

    @Override
    public void saveOrUpdate(QuarantineRegistration qr) {
        // 参数校验
        String grBatchId = qr.getGrBatchId();
        String bQualified = qr.getBQualified();
        if (!StringUtils.hasText(grBatchId) ||
                !StringUtils.hasText(qr.getGrMechanism()) ||
                !StringUtils.hasText(bQualified) ||
                !StringUtils.hasText(qr.getGrImg()) ||
                !StringUtils.hasText(qr.getGrTime())) {
            throw new ServiceException(ResultCode.PARAM_IS_EMPTY);
        }

        int result; // sql语句执行后，返回受影响的行数result
        if (qr.getGrId() != null) {
            // 有id->更新操作
            result = quarantineRegistrationMapper.updateByPrimaryKey(qr);
        } else {
            // 无id->新增操作
            result = quarantineRegistrationMapper.insert(qr);
        }
        if (result == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }

        // 修改批次表中对应批次的检疫状态和检疫合格状态
        if (managerBatchMapper.updateBQuarantineAndBQualified(bQualified, grBatchId) == 0) {
            throw new ServiceException(ResultCode.FAIL);
        }
    }
}

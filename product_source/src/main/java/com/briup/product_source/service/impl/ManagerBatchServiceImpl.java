package com.briup.product_source.service.impl;

import com.briup.product_source.pojo.ManagerBatch;
import com.briup.product_source.dao.ManagerBatchMapper;
import com.briup.product_source.service.ManagerBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerBatchServiceImpl implements ManagerBatchService {

    @Autowired
    private ManagerBatchMapper managerBatchMapper;

    @Override
    public List<ManagerBatch> findAll() {
        return managerBatchMapper.selectAllBatches();
    }

    @Override
    public List<ManagerBatch> findAllUnquarantined() {
        return managerBatchMapper.selectAllUnquarantined();
    }
}

package com.briup.product_source.service.impl;

import com.briup.product_source.dao.ManagerAnimalMapper;
import com.briup.product_source.dao.ManagerBatchMapper;
import com.briup.product_source.dao.ManagerHurdlesMapper;
import com.briup.product_source.dao.ext.ManagerAnimalExtMapper;
import com.briup.product_source.exception.ServiceException;
import com.briup.product_source.pojo.ManagerAnimal;
import com.briup.product_source.pojo.ManagerBatch;
import com.briup.product_source.pojo.ManagerHurdles;
import com.briup.product_source.pojo.ext.ManagerAnimalExt;
import com.briup.product_source.result.ResultCode;
import com.briup.product_source.service.ManagerAnimalService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
public class ManagerAnimalServiceImpl implements ManagerAnimalService {

    
    @Autowired
    private ManagerAnimalMapper managerAnimalMapper;

    @Autowired
    private ManagerBatchMapper managerBatchMapper;

    @Autowired
    private ManagerHurdlesMapper managerHurdlesMapper;

    @Autowired
    private ManagerAnimalExtMapper managerAnimalExtMapper;

    @Override
    public PageInfo<ManagerAnimalExt> findByPage(Integer pageNum, int pageSize, String aHealthy, String aGender) {
        PageHelper.startPage(pageNum, pageSize);
        List<ManagerAnimalExt> animalRelated = managerAnimalExtMapper.selectAnimalRelated(aHealthy, aGender);
        PageInfo<ManagerAnimalExt> pageInfo = new PageInfo(animalRelated);
        return pageInfo;
    }

    @Override
    public void saveOrUpdate(ManagerAnimal animal) {
        String aBatchId = animal.getABatchId(); // 批次编号
        String aHurdlesIdNew = animal.getAHurdlesId(); // 栏圈编号
        // 参数校验
        if (!StringUtils.hasText(animal.getAWeight()) ||
                !StringUtils.hasText(animal.getAGender()) ||
                !StringUtils.hasText(animal.getAHealthy()) ||
                !StringUtils.hasText(animal.getAInoculate()) ||
                !StringUtils.hasText(aBatchId) ||
                !StringUtils.hasText(aHurdlesIdNew) ||
                animal.getATime() == null) {
            throw new ServiceException(ResultCode.PARAM_IS_EMPTY);
        }
        // 根据栏圈编号查询栏圈信息
        ManagerHurdles managerHurdlesNew = managerHurdlesMapper.selectByPrimaryKey(aHurdlesIdNew);
        if (managerHurdlesNew == null) {
            throw new ServiceException(ResultCode.FAIL);
        }
        Integer hSavedNew = managerHurdlesNew.getHSaved(); // 栏圈已存猪的数量
        Integer hMaxNew = managerHurdlesNew.getHMax(); // 栏圈最大容量
        String hFullNew = managerHurdlesNew.getHFull(); // 栏圈是否已满

        // 添加或者修改动物信息
        String aAnimalId = animal.getAAnimalId();
        if(StringUtils.hasText(aAnimalId)){
            // 有id->更新操作
            // 根据id查询动物信息
            ManagerAnimal managerAnimal = managerAnimalMapper.selectByPrimaryKey(aAnimalId);
            if (managerAnimal == null) {
                // 动物不存在
                throw new ServiceException(ResultCode.DATA_IS_EMPTY);
            }
            // 动物存在，修改动物信息
            int result = managerAnimalMapper.updateByPrimaryKey(animal);
            if (result == 0) {
                // 修改失败
                throw new ServiceException(ResultCode.FAIL);
            }
            // 修改成功，判断是否修改了栏圈信息
            String aHurdlesIdOld = managerAnimal.getAHurdlesId();
            ManagerHurdles managerHurdlesOld = managerHurdlesMapper.selectByPrimaryKey(aHurdlesIdOld);
            if (!aHurdlesIdNew.equals(aHurdlesIdOld)) {
                // 修改新的栏圈信息
                managerHurdlesNew.setHSaved(hSavedNew + 1);
                if (hSavedNew + result == hMaxNew) {
                    managerHurdlesNew.setHFull("已满");
                }
                if (managerHurdlesMapper.updateByPrimaryKey(managerHurdlesNew) == 0) {
                    throw new ServiceException(ResultCode.FAIL);
                }

                // 修改老的栏圈信息
                managerHurdlesOld.setHSaved(managerHurdlesOld.getHSaved() - 1);
                if ("已满".equals(managerHurdlesOld.getHFull())) {
                    managerHurdlesOld.setHFull("未满");
                }
                if (managerHurdlesMapper.updateByPrimaryKey(managerHurdlesOld) == 0) {
                    throw new ServiceException(ResultCode.FAIL);
                }
            }
        }else {
            // 无id->新增操作
            // 根据批次编号查询批次信息
            ManagerBatch managerBatch = managerBatchMapper.selectByPrimaryKey(aBatchId);
            if (managerBatch == null) {
                throw new ServiceException(ResultCode.FAIL);
            }
            // 批次存在，根据批次检疫状态，动态设置动物过程状态
            if ("已检疫".equals(managerBatch.getBQuarantine())) {
                animal.setAStatus("已检疫");
            }else {
                animal.setAStatus("养殖中");
            }
            // 执行添加
            animal.setAAnimalId(UUID.randomUUID().toString().replace("-",""));
            int result = managerAnimalMapper.insert(animal);
            if (result == 0) {
                // 添加失败
                throw new ServiceException(ResultCode.FAIL);
            }
            // 添加成功，修改栏圈信息
            managerHurdlesNew.setHSaved(hSavedNew + 1);
            if (hSavedNew + result == hMaxNew) {
                // 栏圈已满，修改h_Full字段
                managerHurdlesNew.setHFull("已满");
            }
            if (managerHurdlesMapper.updateByPrimaryKey(managerHurdlesNew) == 0) {
                throw new ServiceException(ResultCode.FAIL);
            };
        }
    }
}

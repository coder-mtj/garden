package com.bishe.garden.service.impl;

import com.bishe.garden.entity.GrapeCrop;
import com.bishe.garden.mapper.GrapeCropMapper;
import com.bishe.garden.service.GrapeCropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 葡萄作物服务实现类
 */
@Service
public class GrapeCropServiceImpl implements GrapeCropService {

    @Autowired
    private GrapeCropMapper grapeCropMapper;

    @Override
    @Transactional
    public boolean add(GrapeCrop crop) {
        // 设置创建时间
        crop.setCreateTime(new Date());
        crop.setUpdateTime(new Date());
        // 如果状态为空，默认设为启用(1)
        if (crop.getStatus() == null) {
            crop.setStatus(1);
        }
        // 如果作物类型为空，默认设为"葡萄"
        if (crop.getCropType() == null || crop.getCropType().isEmpty()) {
            crop.setCropType("葡萄");
        }
        return grapeCropMapper.insert(crop) > 0;
    }

    @Override
    @Transactional
    public boolean update(GrapeCrop crop) {
        // 设置更新时间
        crop.setUpdateTime(new Date());
        return grapeCropMapper.updateById(crop) > 0;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return grapeCropMapper.deleteById(id) > 0;
    }

    @Override
    public GrapeCrop getById(Long id) {
        return grapeCropMapper.selectById(id);
    }

    @Override
    public GrapeCrop getByCropCode(String cropCode) {
        return grapeCropMapper.selectByCropCode(cropCode);
    }

    @Override
    public GrapeCrop getByCropName(String cropName) {
        return grapeCropMapper.selectByCropName(cropName);
    }

    @Override
    public List<GrapeCrop> getAll() {
        return grapeCropMapper.selectAll();
    }

    @Override
    public List<GrapeCrop> getByCondition(String cropType, String variety, Integer status) {
        return grapeCropMapper.selectByCondition(cropType, variety, status);
    }

    @Override
    public List<GrapeCrop> getByPage(int pageNum, int pageSize, String cropType, String variety, Integer status) {
        // 计算分页起始位置
        int offset = (pageNum - 1) * pageSize;
        return grapeCropMapper.selectByPage(offset, pageSize, cropType, variety, status);
    }

    @Override
    public int count(String cropType, String variety, Integer status) {
        return grapeCropMapper.countByCondition(cropType, variety, status);
    }

    @Override
    @Transactional
    public boolean updateStatus(Long id, Integer status) {
        return grapeCropMapper.updateStatus(id, status) > 0;
    }
} 
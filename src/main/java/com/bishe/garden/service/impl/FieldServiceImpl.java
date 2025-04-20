package com.bishe.garden.service.impl;

import com.bishe.garden.entity.Field;
import com.bishe.garden.mapper.FieldMapper;
import com.bishe.garden.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 田块服务实现类
 */
@Service
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldMapper fieldMapper;

    @Override
    @Transactional
    public boolean add(Field field) {
        // 设置创建时间
        field.setCreateTime(new Date());
        field.setUpdateTime(new Date());
        // 如果状态为空，默认设为使用中(1)
        if (field.getStatus() == null) {
            field.setStatus(1);
        }
        return fieldMapper.insert(field) > 0;
    }

    @Override
    @Transactional
    public boolean update(Field field) {
        // 设置更新时间
        field.setUpdateTime(new Date());
        return fieldMapper.updateById(field) > 0;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return fieldMapper.deleteById(id) > 0;
    }

    @Override
    public Field getById(Long id) {
        return fieldMapper.selectById(id);
    }

    @Override
    public Field getByFieldCode(String fieldCode) {
        return fieldMapper.selectByFieldCode(fieldCode);
    }

    @Override
    public List<Field> getAll() {
        return fieldMapper.selectAll();
    }

    @Override
    public List<Field> getByCondition(String fieldName, String soilType, Integer status) {
        return fieldMapper.selectByCondition(fieldName, soilType, status);
    }

    @Override
    public List<Field> getByPage(int pageNum, int pageSize, String fieldName, String soilType, Integer status) {
        // 计算分页起始位置
        int offset = (pageNum - 1) * pageSize;
        return fieldMapper.selectByPage(offset, pageSize, fieldName, soilType, status);
    }

    @Override
    public int count(String fieldName, String soilType, Integer status) {
        return fieldMapper.countByCondition(fieldName, soilType, status);
    }

    @Override
    @Transactional
    public boolean updateStatus(Long id, Integer status) {
        return fieldMapper.updateStatus(id, status) > 0;
    }
} 
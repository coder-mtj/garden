package com.bishe.garden.service.impl;

import com.bishe.garden.entity.FieldCrop;
import com.bishe.garden.entity.Field;
import com.bishe.garden.entity.GrapeCrop;
import com.bishe.garden.mapper.FieldCropMapper;
import com.bishe.garden.mapper.FieldMapper;
import com.bishe.garden.mapper.GrapeCropMapper;
import com.bishe.garden.service.FieldCropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 田块作物关系服务实现类
 */
@Service
public class FieldCropServiceImpl implements FieldCropService {

    @Autowired
    private FieldCropMapper fieldCropMapper;
    
    @Autowired
    private FieldMapper fieldMapper;
    
    @Autowired
    private GrapeCropMapper grapeCropMapper;

    @Override
    @Transactional
    public boolean add(FieldCrop fieldCrop) {
        // 设置创建时间
        fieldCrop.setCreateTime(new Date());
        fieldCrop.setUpdateTime(new Date());
        // 如果状态为空，默认设为生长中(1)
        if (fieldCrop.getStatus() == null) {
            fieldCrop.setStatus(1);
        }
        return fieldCropMapper.insert(fieldCrop) > 0;
    }
    
    @Override
    @Transactional
    public int batchAdd(List<FieldCrop> fieldCrops) {
        if (fieldCrops == null || fieldCrops.isEmpty()) {
            return 0;
        }
        
        Date now = new Date();
        int successCount = 0;
        
        for (FieldCrop fieldCrop : fieldCrops) {
            // 设置创建时间和更新时间
            fieldCrop.setCreateTime(now);
            fieldCrop.setUpdateTime(now);
            // 如果状态为空，默认设为生长中(1)
            if (fieldCrop.getStatus() == null) {
                fieldCrop.setStatus(1);
            }
            
            if (fieldCropMapper.insert(fieldCrop) > 0) {
                successCount++;
            }
        }
        
        return successCount;
    }

    @Override
    @Transactional
    public boolean update(FieldCrop fieldCrop) {
        // 设置更新时间
        fieldCrop.setUpdateTime(new Date());
        return fieldCropMapper.updateById(fieldCrop) > 0;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return fieldCropMapper.deleteById(id) > 0;
    }
    
    @Override
    @Transactional
    public int batchDelete(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return 0;
        }
        
        int successCount = 0;
        for (Long id : ids) {
            if (fieldCropMapper.deleteById(id) > 0) {
                successCount++;
            }
        }
        
        return successCount;
    }

    @Override
    public FieldCrop getById(Long id) {
        return fieldCropMapper.selectById(id);
    }

    @Override
    public List<FieldCrop> getByFieldId(Long fieldId) {
        return fieldCropMapper.selectByFieldId(fieldId);
    }

    @Override
    public List<FieldCrop> getByCropId(Long cropId) {
        return fieldCropMapper.selectByCropId(cropId);
    }

    @Override
    public List<FieldCrop> getByYearAndIds(Integer plantingYear, Long fieldId, Long cropId) {
        return fieldCropMapper.selectByYearAndIds(plantingYear, fieldId, cropId);
    }

    @Override
    public List<FieldCrop> getAll() {
        return fieldCropMapper.selectAll();
    }

    @Override
    public List<FieldCrop> getByCondition(Long fieldId, Long cropId, Integer plantingYear, Integer status) {
        return fieldCropMapper.selectByCondition(fieldId, cropId, plantingYear, status);
    }

    @Override
    public List<FieldCrop> getByPage(int pageNum, int pageSize, Long fieldId, Long cropId, Integer plantingYear, Integer status) {
        // 计算分页起始位置
        int offset = (pageNum - 1) * pageSize;
        return fieldCropMapper.selectByPage(offset, pageSize, fieldId, cropId, plantingYear, status);
    }

    @Override
    public int count(Long fieldId, Long cropId, Integer plantingYear, Integer status) {
        return fieldCropMapper.countByCondition(fieldId, cropId, plantingYear, status);
    }

    @Override
    @Transactional
    public boolean updateStatus(Long id, Integer status) {
        return fieldCropMapper.updateStatus(id, status) > 0;
    }
    
    @Override
    public byte[] exportExcel(Long fieldId, Long cropId, Integer plantingYear, Integer status) throws Exception {
        // 查询符合条件的数据
        List<FieldCrop> fieldCrops = fieldCropMapper.selectByCondition(fieldId, cropId, plantingYear, status);
        
        // TODO: 实现Excel生成逻辑，这里简单返回一个空字节数组
        // 在实际实现中，可以使用POI或EasyExcel库生成Excel文件
        return new byte[0];
    }
    
    @Override
    public int importExcel(MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("导入文件不能为空");
        }
        
        // TODO: 实现Excel解析逻辑，这里简单返回0
        // 在实际实现中，可以使用POI或EasyExcel库解析Excel文件并保存数据
        return 0;
    }
    
    @Override
    public Map<Integer, Double> getYieldStatsByYear(Long fieldId, Long cropId) {
        // 获取符合条件的所有田块作物关系
        List<FieldCrop> fieldCrops;
        if (fieldId != null && cropId != null) {
            fieldCrops = fieldCropMapper.selectByCondition(fieldId, cropId, null, null);
        } else if (fieldId != null) {
            fieldCrops = fieldCropMapper.selectByFieldId(fieldId);
        } else if (cropId != null) {
            fieldCrops = fieldCropMapper.selectByCropId(cropId);
        } else {
            fieldCrops = fieldCropMapper.selectAll();
        }
        
        // 按年份统计产量
        Map<Integer, Double> yearlyStats = new HashMap<>();
        for (FieldCrop fieldCrop : fieldCrops) {
            if (fieldCrop.getPlantingYear() != null && fieldCrop.getExpectedYield() != null) {
                Integer year = fieldCrop.getPlantingYear();
                // 将BigDecimal转换为Double
                Double yield = fieldCrop.getExpectedYield().doubleValue();
                
                // 累加同一年份的产量
                if (yearlyStats.containsKey(year)) {
                    yearlyStats.put(year, yearlyStats.get(year) + yield);
                } else {
                    yearlyStats.put(year, yield);
                }
            }
        }
        
        // 按年份排序
        return yearlyStats.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }
    
    @Override
    public Map<String, Double> getAreaStatsByCrop(Integer plantingYear) {
        // 获取所有葡萄品种信息
        List<GrapeCrop> grapeCrops = grapeCropMapper.selectAll();
        Map<Long, String> cropNameMap = grapeCrops.stream()
                .collect(Collectors.toMap(GrapeCrop::getId, GrapeCrop::getCropName));
        
        // 查询符合条件的田块作物关系
        List<FieldCrop> fieldCrops;
        if (plantingYear != null) {
            fieldCrops = fieldCropMapper.selectByYearAndIds(plantingYear, null, null);
        } else {
            fieldCrops = fieldCropMapper.selectAll();
        }
        
        // 按作物类型统计种植面积
        Map<String, Double> areaStats = new HashMap<>();
        for (FieldCrop fieldCrop : fieldCrops) {
            if (fieldCrop.getCropId() != null && fieldCrop.getAreaSize() != null) {
                String cropName = cropNameMap.getOrDefault(fieldCrop.getCropId(), "未知作物");
                // 将BigDecimal转换为Double
                Double area = fieldCrop.getAreaSize().doubleValue();
                
                // 累加同一作物类型的种植面积
                if (areaStats.containsKey(cropName)) {
                    areaStats.put(cropName, areaStats.get(cropName) + area);
                } else {
                    areaStats.put(cropName, area);
                }
            }
        }
        
        // 按种植面积降序排序
        return areaStats.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }
    
    @Override
    public Map<String, Object> getFieldUsage(Long fieldId) {
        if (fieldId == null) {
            throw new IllegalArgumentException("田块ID不能为空");
        }
        
        // 获取田块信息
        Field field = fieldMapper.selectById(fieldId);
        if (field == null) {
            throw new IllegalArgumentException("田块不存在");
        }
        
        // 查询该田块的所有种植记录
        List<FieldCrop> fieldCrops = fieldCropMapper.selectByFieldId(fieldId);
        
        // 获取所有葡萄品种信息
        List<GrapeCrop> grapeCrops = grapeCropMapper.selectAll();
        Map<Long, String> cropNameMap = grapeCrops.stream()
                .collect(Collectors.toMap(GrapeCrop::getId, GrapeCrop::getCropName));
        
        // 田块总面积
        BigDecimal totalAreaBd = field.getArea();
        Double totalArea = totalAreaBd != null ? totalAreaBd.doubleValue() : 0.0;
        
        // 计算已使用面积
        double usedArea = 0.0;
        Map<Long, Double> cropAreaMap = new HashMap<>();
        for (FieldCrop fieldCrop : fieldCrops) {
            if (fieldCrop.getAreaSize() != null) {
                double area = fieldCrop.getAreaSize().doubleValue();
                usedArea += area;
                
                // 统计每种作物的种植面积
                Long cropId = fieldCrop.getCropId();
                if (cropId != null) {
                    if (cropAreaMap.containsKey(cropId)) {
                        cropAreaMap.put(cropId, cropAreaMap.get(cropId) + area);
                    } else {
                        cropAreaMap.put(cropId, area);
                    }
                }
            }
        }
        
        // 计算未使用面积和利用率
        double unusedArea = Math.max(0, totalArea - usedArea);
        double utilizationRate = totalArea > 0 ? usedArea / totalArea : 0;
        
        // 构建作物种植情况列表
        List<Map<String, Object>> cropsList = new ArrayList<>();
        for (Map.Entry<Long, Double> entry : cropAreaMap.entrySet()) {
            Long cropId = entry.getKey();
            Double plantArea = entry.getValue();
            String cropName = cropNameMap.getOrDefault(cropId, "未知作物");
            double proportion = usedArea > 0 ? plantArea / usedArea : 0;
            
            Map<String, Object> cropInfo = new HashMap<>();
            cropInfo.put("cropId", cropId);
            cropInfo.put("cropName", cropName);
            cropInfo.put("plantArea", plantArea);
            cropInfo.put("proportion", proportion);
            
            cropsList.add(cropInfo);
        }
        
        // 统计年度使用情况
        Map<Integer, Double> yearlyUsage = new HashMap<>();
        for (FieldCrop fieldCrop : fieldCrops) {
            if (fieldCrop.getPlantingYear() != null && fieldCrop.getAreaSize() != null) {
                Integer year = fieldCrop.getPlantingYear();
                Double area = fieldCrop.getAreaSize().doubleValue();
                
                if (yearlyUsage.containsKey(year)) {
                    yearlyUsage.put(year, yearlyUsage.get(year) + area);
                } else {
                    yearlyUsage.put(year, area);
                }
            }
        }
        
        // 构建最终返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("totalArea", totalArea);
        result.put("usedArea", usedArea);
        result.put("unusedArea", unusedArea);
        result.put("utilizationRate", utilizationRate);
        result.put("crops", cropsList);
        result.put("yearlyUsage", yearlyUsage);
        
        return result;
    }
} 
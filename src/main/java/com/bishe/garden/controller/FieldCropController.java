package com.bishe.garden.controller;

import com.bishe.garden.entity.FieldCrop;
import com.bishe.garden.service.FieldCropService;
import com.bishe.garden.common.Result;
import com.bishe.garden.common.PageResult;
import com.bishe.garden.util.TokenValidationUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 田块作物关系控制器
 */
@RestController
@RequestMapping("/fieldcrop")
public class FieldCropController {
    
    @Autowired
    private FieldCropService fieldCropService;
    
    @Autowired
    private TokenValidationUtil tokenValidationUtil;
    
    /**
     * 添加田块作物关系
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody FieldCrop fieldCrop, HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return validationResult;
            }
            
            boolean success = fieldCropService.add(fieldCrop);
            if (success) {
                return Result.success("添加成功");
            } else {
                return Result.error("添加失败");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 批量添加田块作物关系
     */
    @PostMapping("/batchAdd")
    public Result<?> batchAdd(@RequestBody List<FieldCrop> fieldCrops, HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return validationResult;
            }
            
            int successCount = fieldCropService.batchAdd(fieldCrops);
            return Result.success("成功添加" + successCount + "条记录");
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 更新田块作物关系
     */
    @PutMapping("/update")
    public Result<?> update(@RequestBody FieldCrop fieldCrop, HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return validationResult;
            }
            
            boolean success = fieldCropService.update(fieldCrop);
            if (success) {
                return Result.success("更新成功");
            } else {
                return Result.error("更新失败");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 删除田块作物关系
     */
    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Long id, HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return validationResult;
            }
            
            boolean success = fieldCropService.delete(id);
            if (success) {
                return Result.success("删除成功");
            } else {
                return Result.error("删除失败");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 批量删除田块作物关系
     */
    @DeleteMapping("/batchDelete")
    public Result<?> batchDelete(@RequestBody List<Long> ids, HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return validationResult;
            }
            
            int successCount = fieldCropService.batchDelete(ids);
            return Result.success("成功删除" + successCount + "条记录");
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 根据ID查询田块作物关系
     */
    @GetMapping("/get/{id}")
    public Result<FieldCrop> getById(@PathVariable Long id, HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return Result.error(validationResult.getMessage());
            }
            
            FieldCrop fieldCrop = fieldCropService.getById(id);
            if (fieldCrop != null) {
                return Result.success(fieldCrop);
            } else {
                return Result.error("记录不存在");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 根据田块ID查询关联的作物关系
     */
    @GetMapping("/getByFieldId/{fieldId}")
    public Result<List<FieldCrop>> getByFieldId(@PathVariable Long fieldId, HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return Result.error(validationResult.getMessage());
            }
            
            List<FieldCrop> fieldCrops = fieldCropService.getByFieldId(fieldId);
            return Result.success(fieldCrops);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 根据作物ID查询关联的田块关系
     */
    @GetMapping("/getByCropId/{cropId}")
    public Result<List<FieldCrop>> getByCropId(@PathVariable Long cropId, HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return Result.error(validationResult.getMessage());
            }
            
            List<FieldCrop> fieldCrops = fieldCropService.getByCropId(cropId);
            return Result.success(fieldCrops);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 根据年份、田块ID和作物ID查询田块作物关系
     */
    @GetMapping("/getByYearAndIds")
    public Result<List<FieldCrop>> getByYearAndIds(
            @RequestParam Integer plantingYear,
            @RequestParam(required = false) Long fieldId,
            @RequestParam(required = false) Long cropId,
            HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return Result.error(validationResult.getMessage());
            }
            
            List<FieldCrop> fieldCrops = fieldCropService.getByYearAndIds(plantingYear, fieldId, cropId);
            return Result.success(fieldCrops);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 查询所有田块作物关系
     */
    @GetMapping("/list")
    public Result<List<FieldCrop>> list(HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return Result.error(validationResult.getMessage());
            }
            
            List<FieldCrop> fieldCrops = fieldCropService.getAll();
            return Result.success(fieldCrops);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 条件查询田块作物关系
     */
    @GetMapping("/search")
    public Result<List<FieldCrop>> search(
            @RequestParam(required = false) Long fieldId,
            @RequestParam(required = false) Long cropId,
            @RequestParam(required = false) Integer plantingYear,
            @RequestParam(required = false) Integer status,
            HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return Result.error(validationResult.getMessage());
            }
            
            List<FieldCrop> fieldCrops = fieldCropService.getByCondition(fieldId, cropId, plantingYear, status);
            return Result.success(fieldCrops);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 分页查询
     */
    @GetMapping("/page")
    public Result<PageResult<FieldCrop>> page(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Long fieldId,
            @RequestParam(required = false) Long cropId,
            @RequestParam(required = false) Integer plantingYear,
            @RequestParam(required = false) Integer status,
            HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return Result.error(validationResult.getMessage());
            }
            
            List<FieldCrop> fieldCrops = fieldCropService.getByPage(pageNum, pageSize, fieldId, cropId, plantingYear, status);
            int total = fieldCropService.count(fieldId, cropId, plantingYear, status);
            
            PageResult<FieldCrop> pageResult = PageResult.of(fieldCrops, (long) total, pageNum, pageSize);
            return Result.success(pageResult);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 更新状态
     */
    @PutMapping("/updateStatus")
    public Result<?> updateStatus(
            @RequestParam Long id, 
            @RequestParam Integer status,
            HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return validationResult;
            }
            
            boolean success = fieldCropService.updateStatus(id, status);
            if (success) {
                return Result.success("状态更新成功");
            } else {
                return Result.error("状态更新失败");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 导出Excel
     */
    @GetMapping("/export")
    public ResponseEntity<Resource> exportExcel(
            @RequestParam(required = false) Long fieldId,
            @RequestParam(required = false) Long cropId,
            @RequestParam(required = false) Integer plantingYear,
            @RequestParam(required = false) Integer status,
            HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return ResponseEntity.badRequest().build();
            }
            
            byte[] excelData = fieldCropService.exportExcel(fieldId, cropId, plantingYear, status);
            ByteArrayResource resource = new ByteArrayResource(excelData);
            
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=fieldcrop_data.xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(excelData.length)
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * 导入Excel
     */
    @PostMapping("/import")
    public Result<?> importExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return validationResult;
            }
            
            int count = fieldCropService.importExcel(file);
            return Result.success("成功导入" + count + "条记录");
        } catch (Exception e) {
            return Result.error("导入失败：" + e.getMessage());
        }
    }
    
    /**
     * 按年度统计产量
     */
    @GetMapping("/statsByYear")
    public Result<Map<Integer, Double>> getYieldStatsByYear(
            @RequestParam(required = false) Long fieldId,
            @RequestParam(required = false) Long cropId,
            HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return Result.error(validationResult.getMessage());
            }
            
            Map<Integer, Double> stats = fieldCropService.getYieldStatsByYear(fieldId, cropId);
            return Result.success(stats);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 按作物类型统计种植面积
     */
    @GetMapping("/statsByCrop")
    public Result<Map<String, Double>> getAreaStatsByCrop(
            @RequestParam(required = false) Integer plantingYear,
            HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return Result.error(validationResult.getMessage());
            }
            
            Map<String, Double> stats = fieldCropService.getAreaStatsByCrop(plantingYear);
            return Result.success(stats);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 获取田块使用情况
     */
    @GetMapping("/fieldUsage/{fieldId}")
    public Result<Map<String, Object>> getFieldUsage(@PathVariable Long fieldId, HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return Result.error(validationResult.getMessage());
            }
            
            Map<String, Object> usageData = fieldCropService.getFieldUsage(fieldId);
            return Result.success(usageData);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
} 
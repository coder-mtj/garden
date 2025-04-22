package com.bishe.garden.controller;

import com.bishe.garden.entity.ToolUsageRecord;
import com.bishe.garden.service.ToolUsageRecordService;
import com.bishe.garden.common.Result;
import com.bishe.garden.common.PageResult;
import com.bishe.garden.util.TokenValidationUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 工具使用记录控制器
 */
@RestController
@RequestMapping("/toolusage")
public class ToolUsageRecordController {
    
    @Autowired
    private ToolUsageRecordService toolUsageRecordService;
    
    @Autowired
    private TokenValidationUtil tokenValidationUtil;
    
    /**
     * 添加使用记录
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody ToolUsageRecord record, HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return validationResult;
            }
            
            boolean success = toolUsageRecordService.save(record);
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
     * 更新使用记录
     */
    @PutMapping("/update")
    public Result<?> update(@RequestBody ToolUsageRecord record, HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return validationResult;
            }
            
            boolean success = toolUsageRecordService.update(record);
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
     * 删除使用记录
     */
    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Long id, HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return validationResult;
            }
            
            boolean success = toolUsageRecordService.delete(id);
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
     * 根据ID查询使用记录
     */
    @GetMapping("/get/{id}")
    public Result<ToolUsageRecord> getById(@PathVariable Long id, HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return Result.error(validationResult.getMessage());
            }
            
            ToolUsageRecord record = toolUsageRecordService.getById(id);
            if (record != null) {
                return Result.success(record);
            } else {
                return Result.error("记录不存在");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 根据工具ID查询使用记录
     */
    @GetMapping("/listByTool/{toolId}")
    public Result<List<ToolUsageRecord>> listByToolId(@PathVariable Long toolId, HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return Result.error(validationResult.getMessage());
            }
            
            List<ToolUsageRecord> records = toolUsageRecordService.listByToolId(toolId);
            return Result.success(records);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 根据员工ID查询使用记录
     */
    @GetMapping("/listByEmployee/{employeeId}")
    public Result<List<ToolUsageRecord>> listByEmployeeId(@PathVariable Long employeeId, HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return Result.error(validationResult.getMessage());
            }
            
            List<ToolUsageRecord> records = toolUsageRecordService.listByEmployeeId(employeeId);
            return Result.success(records);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 查询指定日期范围内的使用记录
     */
    @GetMapping("/listByDateRange")
    public Result<List<ToolUsageRecord>> listByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return Result.error(validationResult.getMessage());
            }
            
            List<ToolUsageRecord> records = toolUsageRecordService.listByDateRange(startDate, endDate);
            return Result.success(records);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 条件查询使用记录
     */
    @GetMapping("/search")
    public Result<List<ToolUsageRecord>> search(
            @RequestParam(required = false) Long toolId,
            @RequestParam(required = false) Long employeeId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(required = false) Integer status,
            HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return Result.error(validationResult.getMessage());
            }
            
            List<ToolUsageRecord> records = toolUsageRecordService.listByCondition(
                toolId, employeeId, startDate, endDate, status);
            return Result.success(records);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 分页查询使用记录
     */
    @GetMapping("/page")
    public Result<PageResult<ToolUsageRecord>> page(
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Long toolId,
            @RequestParam(required = false) Long employeeId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(required = false) Integer status,
            HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return Result.error(validationResult.getMessage());
            }
            
            PageResult<ToolUsageRecord> pageResult = toolUsageRecordService.page(
                pageNum, pageSize, toolId, employeeId, startDate, endDate, status);
            return Result.success(pageResult);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 统计各工具的使用时长
     */
    @GetMapping("/statisticsByTool")
    public Result<List<Map<String, Object>>> statisticsByTool(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return Result.error(validationResult.getMessage());
            }
            
            List<Map<String, Object>> statistics = toolUsageRecordService.statisticsByTool(startDate, endDate);
            return Result.success(statistics);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 统计各员工的工具使用情况
     */
    @GetMapping("/statisticsByEmployee")
    public Result<List<Map<String, Object>>> statisticsByEmployee(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return Result.error(validationResult.getMessage());
            }
            
            List<Map<String, Object>> statistics = toolUsageRecordService.statisticsByEmployee(startDate, endDate);
            return Result.success(statistics);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 更新使用记录状态
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
            
            boolean success = toolUsageRecordService.updateStatus(id, status);
            if (success) {
                return Result.success("状态更新成功");
            } else {
                return Result.error("状态更新失败");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
} 
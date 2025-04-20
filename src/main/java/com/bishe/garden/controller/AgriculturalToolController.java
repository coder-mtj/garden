package com.bishe.garden.controller;

import com.bishe.garden.entity.AgriculturalTool;
import com.bishe.garden.service.AgriculturalToolService;
import com.bishe.garden.common.Result;
import com.bishe.garden.common.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 农业工具控制器
 */
@RestController
@RequestMapping("/tools")
public class AgriculturalToolController {
    
    @Autowired
    private AgriculturalToolService agriculturalToolService;
    
    /**
     * 添加工具
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody AgriculturalTool tool) {
        try {
            boolean success = agriculturalToolService.save(tool);
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
     * 更新工具
     */
    @PutMapping("/update")
    public Result<?> update(@RequestBody AgriculturalTool tool) {
        try {
            boolean success = agriculturalToolService.update(tool);
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
     * 删除工具
     */
    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Long id) {
        try {
            boolean success = agriculturalToolService.delete(id);
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
     * 根据ID查询工具
     */
    @GetMapping("/get/{id}")
    public Result<AgriculturalTool> getById(@PathVariable Long id) {
        try {
            AgriculturalTool tool = agriculturalToolService.getById(id);
            if (tool != null) {
                return Result.success(tool);
            } else {
                return Result.error("工具不存在");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 根据工具编码查询工具
     */
    @GetMapping("/getByCode")
    public Result<AgriculturalTool> getByCode(@RequestParam String toolCode) {
        try {
            AgriculturalTool tool = agriculturalToolService.getByToolCode(toolCode);
            if (tool != null) {
                return Result.success(tool);
            } else {
                return Result.error("工具不存在");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 查询所有工具
     */
    @GetMapping("/list")
    public Result<List<AgriculturalTool>> list() {
        try {
            List<AgriculturalTool> tools = agriculturalToolService.listAll();
            return Result.success(tools);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 条件查询工具
     */
    @GetMapping("/search")
    public Result<List<AgriculturalTool>> search(
            @RequestParam(required = false) String toolName,
            @RequestParam(required = false) String toolType,
            @RequestParam(required = false) Integer status) {
        try {
            List<AgriculturalTool> tools = agriculturalToolService.listByCondition(toolName, toolType, status);
            return Result.success(tools);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 分页查询
     */
    @GetMapping("/page")
    public Result<PageResult<AgriculturalTool>> page(
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String toolName,
            @RequestParam(required = false) String toolType,
            @RequestParam(required = false) Integer status) {
        try {
            PageResult<AgriculturalTool> pageResult = agriculturalToolService.page(
                pageNum, pageSize, toolName, toolType, status);
            return Result.success(pageResult);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 查询需要维护的工具
     */
    @GetMapping("/needMaintenance")
    public Result<List<AgriculturalTool>> needMaintenance() {
        try {
            List<AgriculturalTool> tools = agriculturalToolService.listNeedMaintenance();
            return Result.success(tools);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 更新工具状态
     */
    @PutMapping("/updateStatus")
    public Result<?> updateStatus(@RequestParam Long id, @RequestParam Integer status) {
        try {
            boolean success = agriculturalToolService.updateStatus(id, status);
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
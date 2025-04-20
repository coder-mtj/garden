package com.bishe.garden.controller;

import com.bishe.garden.entity.Field;
import com.bishe.garden.service.FieldService;
import com.bishe.garden.common.Result;
import com.bishe.garden.common.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 田块控制器
 */
@RestController
@RequestMapping("/field")
public class FieldController {
    
    @Autowired
    private FieldService fieldService;
    
    /**
     * 添加田块
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody Field field) {
        try {
            boolean success = fieldService.add(field);
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
     * 更新田块
     */
    @PutMapping("/update")
    public Result<?> update(@RequestBody Field field) {
        try {
            boolean success = fieldService.update(field);
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
     * 删除田块
     */
    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Long id) {
        try {
            boolean success = fieldService.delete(id);
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
     * 根据ID查询田块
     */
    @GetMapping("/get/{id}")
    public Result<Field> getById(@PathVariable Long id) {
        try {
            Field field = fieldService.getById(id);
            if (field != null) {
                return Result.success(field);
            } else {
                return Result.error("田块不存在");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 根据田块编号查询田块
     */
    @GetMapping("/getByCode")
    public Result<Field> getByCode(@RequestParam String fieldCode) {
        try {
            Field field = fieldService.getByFieldCode(fieldCode);
            if (field != null) {
                return Result.success(field);
            } else {
                return Result.error("田块不存在");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 查询所有田块
     */
    @GetMapping("/list")
    public Result<List<Field>> list() {
        try {
            List<Field> fields = fieldService.getAll();
            return Result.success(fields);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 条件查询田块
     */
    @GetMapping("/search")
    public Result<List<Field>> search(
            @RequestParam(required = false) String fieldName,
            @RequestParam(required = false) String soilType,
            @RequestParam(required = false) Integer status) {
        try {
            List<Field> fields = fieldService.getByCondition(fieldName, soilType, status);
            return Result.success(fields);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 分页查询
     */
    @GetMapping("/page")
    public Result<PageResult<Field>> page(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String fieldName,
            @RequestParam(required = false) String soilType,
            @RequestParam(required = false) Integer status) {
        try {
            List<Field> fields = fieldService.getByPage(pageNum, pageSize, fieldName, soilType, status);
            int total = fieldService.count(fieldName, soilType, status);
            
            PageResult<Field> pageResult = PageResult.of(fields, (long) total, pageNum, pageSize);
            return Result.success(pageResult);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 更新田块状态
     */
    @PutMapping("/updateStatus")
    public Result<?> updateStatus(@RequestParam Long id, @RequestParam Integer status) {
        try {
            boolean success = fieldService.updateStatus(id, status);
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
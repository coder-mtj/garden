package com.bishe.garden.controller;

import com.bishe.garden.entity.GrapeCrop;
import com.bishe.garden.service.GrapeCropService;
import com.bishe.garden.common.Result;
import com.bishe.garden.common.PageResult;
import com.bishe.garden.util.TokenValidationUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 葡萄作物控制器
 */
@RestController
@RequestMapping("/crop")
public class GrapeCropController {
    
    @Autowired
    private GrapeCropService grapeCropService;
    
    @Autowired
    private TokenValidationUtil tokenValidationUtil;
    
    /**
     * 添加葡萄作物
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody GrapeCrop crop, HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return validationResult;
            }
            
            boolean success = grapeCropService.add(crop);
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
     * 更新葡萄作物
     */
    @PutMapping("/update")
    public Result<?> update(@RequestBody GrapeCrop crop, HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return validationResult;
            }
            
            boolean success = grapeCropService.update(crop);
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
     * 删除葡萄作物
     */
    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Long id, HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return validationResult;
            }
            
            boolean success = grapeCropService.delete(id);
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
     * 根据ID查询葡萄作物
     */
    @GetMapping("/get/{id}")
    public Result<GrapeCrop> getById(@PathVariable Long id, HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return Result.error(validationResult.getMessage());
            }
            
            GrapeCrop crop = grapeCropService.getById(id);
            if (crop != null) {
                return Result.success(crop);
            } else {
                return Result.error("葡萄作物不存在");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 根据作物编号查询葡萄作物
     */
    @GetMapping("/getByCode")
    public Result<GrapeCrop> getByCode(@RequestParam String cropCode, HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return Result.error(validationResult.getMessage());
            }
            
            GrapeCrop crop = grapeCropService.getByCropCode(cropCode);
            if (crop != null) {
                return Result.success(crop);
            } else {
                return Result.error("葡萄作物不存在");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 根据作物名称查询葡萄作物
     */
    @GetMapping("/getByName")
    public Result<GrapeCrop> getByName(@RequestParam String cropName, HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return Result.error(validationResult.getMessage());
            }
            
            GrapeCrop crop = grapeCropService.getByCropName(cropName);
            if (crop != null) {
                return Result.success(crop);
            } else {
                return Result.error("葡萄作物不存在");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 查询所有葡萄作物
     */
    @GetMapping("/list")
    public Result<List<GrapeCrop>> list(HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return Result.error(validationResult.getMessage());
            }
            
            List<GrapeCrop> crops = grapeCropService.getAll();
            return Result.success(crops);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 条件查询葡萄作物
     */
    @GetMapping("/search")
    public Result<List<GrapeCrop>> search(
            @RequestParam(required = false) String cropType,
            @RequestParam(required = false) String variety,
            @RequestParam(required = false) Integer status,
            HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return Result.error(validationResult.getMessage());
            }
            
            List<GrapeCrop> crops = grapeCropService.getByCondition(cropType, variety, status);
            return Result.success(crops);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 分页查询
     */
    @GetMapping("/page")
    public Result<PageResult<GrapeCrop>> page(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String cropType,
            @RequestParam(required = false) String variety,
            @RequestParam(required = false) Integer status,
            HttpServletRequest request) {
        try {
            // 验证token
            Result<?> validationResult = tokenValidationUtil.validateToken(request);
            if (validationResult != null) {
                return Result.error(validationResult.getMessage());
            }
            
            List<GrapeCrop> crops = grapeCropService.getByPage(pageNum, pageSize, cropType, variety, status);
            int total = grapeCropService.count(cropType, variety, status);
            
            PageResult<GrapeCrop> pageResult = PageResult.of(crops, (long) total, pageNum, pageSize);
            return Result.success(pageResult);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 更新葡萄作物状态
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
            
            boolean success = grapeCropService.updateStatus(id, status);
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
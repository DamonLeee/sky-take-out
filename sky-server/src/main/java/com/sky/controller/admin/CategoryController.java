package com.sky.controller.admin;


import com.github.pagehelper.Page;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@Api(tags = "分类接口")
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService CategoryService;


    /**
     * 添加分类
     * @param categoryDTO
     * @return
     */
    @ApiOperation(value = "添加分类")
    @PostMapping
    public Result save(@RequestBody CategoryDTO categoryDTO) {
        log.info("添加分类：{}", categoryDTO);
        CategoryService.save(categoryDTO);
        return Result.success();
    }

    /**
     * 分页查询
     * @return
     */
    @ApiOperation(value = "分页查询")
    @GetMapping("/page")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("分页查询：{}", categoryPageQueryDTO);
        PageResult pageResult = CategoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     *
     * @param status
     * @param id
     * @return
     */
    @ApiOperation(value = "修改分类状态")
    @PostMapping("/status/{status}")
    public Result updateStatus(@PathVariable Integer status,Long id) {
        log.info("修改分类状态：{}，{}", status, id);
        CategoryService.updateStatus(status, id);
        return Result.success();
    }

    /**
     * 修改分类
     * @param categoryDTO
     * @return
     */
    @ApiOperation(value = "修改分类")
    @PutMapping
    public Result update(@RequestBody CategoryDTO categoryDTO) {
        log.info("修改分类：{}", categoryDTO);
        CategoryService.update(categoryDTO);
        return Result.success();
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @ApiOperation(value = "删除分类")
    @DeleteMapping
    public Result delete(Long id) {
        log.info("删除分类：{}", id);
        CategoryService.delete(id);
        return Result.success();
    }

    /**
     * 根据type查询分类
     * @return
     */
    @ApiOperation(value = "查询分类类型")
    @GetMapping("/list")
    public Result<List<Category>> list(Integer type){
        log.info("查询分类类型：{}", type);
        List<Category> category = CategoryService.list(type);
        return Result.success(category);
    }

}

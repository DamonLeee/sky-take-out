package com.sky.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 添加分类
     * @param categoryDTO
     */
    public void save(CategoryDTO categoryDTO) {
        Category category = new Category();

        //对象属性拷贝
        BeanUtils.copyProperties(categoryDTO, category);

        //添加状态(默认状态正常,1正常 0禁用)
        category.setStatus(StatusConstant.ENABLE);

        //添加创建时间
       /* category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());

        //更新创建人和修改人
        category.setUpdateUser(BaseContext.getCurrentId());
        category.setCreateUser(BaseContext.getCurrentId());*/
        categoryMapper.insert(category);
    }

    /**
     * 分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());

        Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);

        // 获取总记录数
        long total = page.getTotal();
        // 获取查询结果列表
        List<Category> records = page.getResult();

        // 返回分页结果
        return new PageResult(total, records);
    }

    /**
     * 修改分类状态
     * @param status
     * @param id
     */
    public void updateStatus(Integer status, Long id) {
        Category category = Category.builder()
                .id(id)
                .status(status)
                .updateTime(LocalDateTime.now())
                .build();
        categoryMapper.update(category);

    }

    /**
     * 修改分类
     * @param categoryDTO
     */
    public void update(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);

        /*category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(BaseContext.getCurrentId());
*/
        categoryMapper.update(category);
    }

    /**
     * 删除分类
     * @param id
     */
    public void delete(Long id) {
        categoryMapper.deleteById(id);
    }

    /**
     * 根据类型查询分类
     * @param type
     */
    public List<Category> list(Integer type) {
        List<Category> category = categoryMapper.list(type);
        return category;
    }
}

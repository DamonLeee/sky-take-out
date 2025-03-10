package com.sky.mapper;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetMealDishMapper {

    /**
     * 根据菜品id集合查询套餐菜品id
     * @param dishIds
     * @return
     */
    List<Long> getSetMealDishIds(List<Long> dishIds);
}

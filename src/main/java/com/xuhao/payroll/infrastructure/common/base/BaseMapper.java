package com.xuhao.payroll.infrastructure.common.base;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BaseMapper<T, ID> {
    int deleteByPrimaryKey(ID id);
    int deleteById(ID id);
    int insert(T record);
    int insertSelective(T record);
    T selectByPrimaryKey(ID id);
    T selectById(ID id);
    List<T> selectAll();
    List<T> selectByCondition(T condition);
    int count();
    int updateByPrimaryKeySelective(T record);
    int updateByPrimaryKey(T record);
    int update(T record);
}
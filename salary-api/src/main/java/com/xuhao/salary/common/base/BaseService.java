package com.xuhao.payroll.infrastructure.common.base;

import java.util.List;

/**
 * 通用Service接口
 * 
 * @param <T> 实体类型
 * @param <K> 主键类型
 */
public interface BaseService<T, K> {
    /**
     * 新增
     * 
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean save(T entity);

    /**
     * 删除
     * 
     * @param id 主键
     * @return 是否成功
     */
    boolean delete(K id);

    /**
     * 更新
     * 
     * @param entity 实体对象
     * @return 是否成功
     */
    boolean update(T entity);

    /**
     * 根据ID查询
     * 
     * @param id 主键
     * @return 实体对象
     */
    T getById(K id);

    /**
     * 查询所有
     * 
     * @return 实体对象列表
     */
    List<T> getAll();

    /**
     * 条件查询
     * 
     * @param condition 查询条件
     * @return 实体对象列表
     */
    List<T> getByCondition(T condition);

    /**
     * 查询总数
     * 
     * @return 记录总数
     */
    long count();
}
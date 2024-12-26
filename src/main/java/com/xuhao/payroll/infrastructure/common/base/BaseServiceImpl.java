package com.xuhao.payroll.infrastructure.common.base;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基础服务实现类
 * 
 * @param <T> 实体类型
 * @param <K> 主键类型
 */
public abstract class BaseServiceImpl<T, K> implements BaseService<T, K> {
    @Autowired
    protected BaseMapper<T, K> baseMapper;

    @Override
    public boolean save(T entity) {
        return baseMapper.insert(entity) > 0;
    }

    @Override
    public boolean delete(K id) {
        return baseMapper.deleteById(id) > 0;
    }

    @Override
    public boolean update(T entity) {
        return baseMapper.update(entity) > 0;
    }

    @Override
    public T getById(K id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<T> getAll() {
        return baseMapper.selectAll();
    }

    @Override
    public List<T> getByCondition(T condition) {
        return baseMapper.selectByCondition(condition);
    }

    @Override
    public long count() {
        return baseMapper.count();
    }
}
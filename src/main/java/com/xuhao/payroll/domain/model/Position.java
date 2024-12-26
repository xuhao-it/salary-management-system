package com.xuhao.payroll.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 职位实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Position extends BaseEntity {
    /**
     * 职位ID
     */
    private Integer positionId;

    /**
     * 职位名称
     */
    private String positionName;

    /**
     * 职位描述
     */
    private String description;

    /**
     * 部门ID
     */
    private Integer deptId;

    /**
     * 状态（0：禁用，1：启用）
     */
    private Integer status;

    /**
     * 关联的部门信息
     */
    private Department department;
}
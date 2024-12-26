package com.xuhao.payroll.model;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 基础实体类
 */
@Data
public class BaseEntity {
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 更新人
     */
    private Integer updateBy;
}
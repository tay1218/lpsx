package com.example.lpsx.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 学校信息表 (school)
 */
@Data
@TableName("school")
public class School {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 学校名称 */
    private String name;

    /** 城市 */
    private String city;

    /** 行政区 */
    private String district;

    /** 课程体系 (IB/AP/A-Level/国内) */
    private String curriculum;

    /** 最低学费(万元) */
    private BigDecimal tuitionMin;

    /** 最高学费(万元) */
    private BigDecimal tuitionMax;

    /** 开设学段 */
    private String grades;

    /** 富文本介绍 */
    private String description;

    /** 标签 (逗号分隔) */
    private String tags;

    /** Logo URL */
    private String logoUrl;

    /** 是否启用: 1-启用, 0-禁用 (逻辑删除) */
    @TableLogic
    private Integer isActive;
}

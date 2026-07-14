package com.example.lpsx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 客户线索表 (customer_lead)
 */
@Data
@TableName("customer_lead")
public class CustomerLead {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 关联用户 ID (可为空) */
    private Integer userId;

    /** 家长姓名 */
    private String parentName;

    /** 当前年级 */
    private String childGrade;

    /** 目标课程体系 */
    private String targetCurriculum;

    /** 联系电话 */
    private String parentPhone;

    /** 状态: 0-待跟进, 1-已沟通, 2-已转化 */
    private Integer status;

    /** 创建时间 */
    private LocalDateTime createTime;
}

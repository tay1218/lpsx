package com.example.lpsx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户学校收藏表 (user_favorite)
 */
@Data
@TableName("user_favorite")
public class UserFavorite {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 用户 ID */
    private Integer userId;

    /** 学校 ID */
    private Integer schoolId;

    /** 创建时间 */
    private LocalDateTime createTime;
}

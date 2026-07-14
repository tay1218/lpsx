package com.example.lpsx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户信息表 (user)
 */
@Data
@TableName("user")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 微信唯一标识 */
    private String openid;

    /** 绑定手机号 */
    private String phone;

    /** 微信昵称 */
    private String nickname;

    /** 头像 URL */
    private String avatar;

    /** 创建时间 */
    private LocalDateTime createTime;
}

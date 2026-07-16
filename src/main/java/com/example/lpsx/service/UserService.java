package com.example.lpsx.service;

import java.util.List;

import com.example.lpsx.entity.CustomerLead;
import com.example.lpsx.entity.User;

/**
 * 用户服务
 */
public interface UserService {

    /** 获取我的咨询/留资记录 */
    List<CustomerLead> getLeadHistory(Integer userId);

    /** 获取用户信息 */
    User getProfile(Integer userId);

    /** 更新用户昵称/头像 */
    void updateProfile(Integer userId, String nickname, String avatarUrl);
}

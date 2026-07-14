package com.example.lpsx.service;

import com.example.lpsx.entity.CustomerLead;

import java.util.List;

/**
 * 用户服务
 */
public interface UserService {

    /**
     * 获取我的咨询/留资记录
     */
    List<CustomerLead> getLeadHistory(Integer userId);
}

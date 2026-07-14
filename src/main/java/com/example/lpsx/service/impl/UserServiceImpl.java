package com.example.lpsx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.lpsx.entity.CustomerLead;
import com.example.lpsx.mapper.CustomerLeadMapper;
import com.example.lpsx.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private CustomerLeadMapper customerLeadMapper;

    @Override
    public List<CustomerLead> getLeadHistory(Integer userId) {
        LambdaQueryWrapper<CustomerLead> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CustomerLead::getUserId, userId)
               .orderByDesc(CustomerLead::getCreateTime);
        return customerLeadMapper.selectList(wrapper);
    }
}

package com.example.lpsx.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.lpsx.entity.CustomerLead;
import com.example.lpsx.entity.User;
import com.example.lpsx.mapper.CustomerLeadMapper;
import com.example.lpsx.mapper.UserMapper;
import com.example.lpsx.service.UserService;

import jakarta.annotation.Resource;

/**
 * 用户服务实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private CustomerLeadMapper customerLeadMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public List<CustomerLead> getLeadHistory(Integer userId) {
        LambdaQueryWrapper<CustomerLead> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CustomerLead::getUserId, userId)
               .orderByDesc(CustomerLead::getCreateTime);
        return customerLeadMapper.selectList(wrapper);
    }

    @Override
    public User getProfile(Integer userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public void updateProfile(Integer userId, String nickname, String avatarUrl) {
        User user = new User();
        user.setId(userId);
        user.setNickname(nickname);
        user.setAvatar(avatarUrl);
        userMapper.updateById(user);
    }
}

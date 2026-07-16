package com.example.lpsx.service.impl;

import com.example.lpsx.common.exception.BusinessException;
import com.example.lpsx.entity.CustomerLead;
import com.example.lpsx.mapper.CustomerLeadMapper;
import com.example.lpsx.service.LeadService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 留资服务实现 — 含 24 小时防重逻辑
 */
@Service
public class LeadServiceImpl implements LeadService {

    @Resource
    private CustomerLeadMapper customerLeadMapper;

    @Override
    public void submit(Integer userId, String parentName, String childGrade, String targetSchool, String parentPhone) {
        // 防重：24 小时内同手机号是否已提交
        int count = customerLeadMapper.countByPhone24h(parentPhone);
        if (count > 0) {
            throw new BusinessException("24小时内已提交，请勿重复提交");
        }

        CustomerLead lead = new CustomerLead();
        lead.setUserId(userId);
        lead.setParentName(parentName);
        lead.setChildGrade(childGrade);
        lead.setTargetSchool(targetSchool);
        lead.setParentPhone(parentPhone);
        lead.setStatus(0); // 0-待跟进

        customerLeadMapper.insert(lead);
    }
}

package com.example.lpsx.service.impl;

import com.example.lpsx.entity.School;
import com.example.lpsx.mapper.SchoolMapper;
import com.example.lpsx.service.AssessmentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 择校匹配服务实现 — 纯 if-else 积分制，无外部 AI 调用
 */
@Service
public class AssessmentServiceImpl implements AssessmentService {

    @Resource
    private SchoolMapper schoolMapper;

    @Override
    public List<Integer> match(List<String> answers) {
        String district = answers.get(0);   // 行政区
        String curriculum = answers.get(1); // 课程体系
        String budget = answers.get(2);     // 学费预算
        String grades = answers.get(3);     // 学段

        // 查询所有启用的学校
        List<School> allSchools = schoolMapper.selectList(null);
        Map<Integer, Integer> scoreMap = new LinkedHashMap<>();

        for (School school : allSchools) {
            int score = 0;

            // 行政区匹配
            if (school.getDistrict() != null && school.getDistrict().contains(district)) {
                score += 3;
            }

            // 课程体系匹配
            if (school.getCurriculum() != null && school.getCurriculum().contains(curriculum)) {
                score += 3;
            }

            // 学费预算匹配 (假设 budget 格式为 "20万内" / "20-30万" / "30万以上")
            if (school.getTuitionMax() != null) {
                double maxFee = school.getTuitionMax().doubleValue();
                if (budget.contains("20万内") && maxFee <= 20) {
                    score += 3;
                } else if (budget.contains("20-30万") && maxFee >= 20 && maxFee <= 30) {
                    score += 3;
                } else if (budget.contains("30万以上") && maxFee > 30) {
                    score += 3;
                } else if (budget.contains("不限")) {
                    score += 1;
                }
            }

            // 学段匹配
            if (school.getGrades() != null && school.getGrades().contains(grades)) {
                score += 3;
            }

            if (score > 0) {
                scoreMap.put(school.getId(), score);
            }
        }

        // 按积分降序，取 Top 3
        return scoreMap.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}

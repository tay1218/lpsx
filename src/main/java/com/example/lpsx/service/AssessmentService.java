package com.example.lpsx.service;

import com.example.lpsx.entity.School;

import java.util.List;

/**
 * 择校匹配服务
 */
public interface AssessmentService {

    /**
     * 根据用户答题结果，返回匹配度最高的 3 所学校
     */
    List<School> match(List<String> answers);
}

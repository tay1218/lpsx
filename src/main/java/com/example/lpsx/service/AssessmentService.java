package com.example.lpsx.service;

import java.util.List;

/**
 * 择校匹配服务
 */
public interface AssessmentService {

    /**
     * 根据用户答题结果，匹配度最高的 3 所学校 ID
     *
     * @param answers 用户答案 [行政区, 课程体系, 学费预算, 学段]
     * @return 匹配的学校 ID 列表
     */
    List<Integer> match(List<String> answers);
}

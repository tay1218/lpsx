package com.example.lpsx.service;

/**
 * 留资服务
 */
public interface LeadService {

    /**
     * 提交客户留资信息
     *
     * @param userId           用户 ID
     * @param childGrade       当前年级
     * @param targetCurriculum 目标课程体系
     * @param parentPhone      联系电话
     */
    void submit(Integer userId, String childGrade, String targetCurriculum, String parentPhone);
}

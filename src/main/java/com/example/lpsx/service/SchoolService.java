package com.example.lpsx.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lpsx.entity.School;

/**
 * 学校服务
 */
public interface SchoolService {

    /**
     * 多条件分页查询学校列表（支持按名称搜索）
     */
    Page<School> listSchools(int page, int size, String city, String curriculum, String district, String grades, String name);

    /**
     * 学校详情
     */
    School getDetail(int id);

    /**
     * 批量对比学校
     */
    List<School> compare(List<Integer> schoolIds);
}

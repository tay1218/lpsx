package com.example.lpsx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lpsx.entity.School;

import java.util.List;

/**
 * 学校服务
 */
public interface SchoolService {

    /**
     * 多条件分页查询学校列表
     */
    Page<School> listSchools(int page, int size, String city, String curriculum, String district, String grades);

    /**
     * 学校详情
     */
    School getDetail(int id);

    /**
     * 批量对比学校
     */
    List<School> compare(List<Integer> schoolIds);
}

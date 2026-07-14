package com.example.lpsx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lpsx.entity.School;
import com.example.lpsx.mapper.SchoolMapper;
import com.example.lpsx.service.SchoolService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 学校服务实现
 */
@Service
public class SchoolServiceImpl implements SchoolService {

    @Resource
    private SchoolMapper schoolMapper;

    @Override
    public Page<School> listSchools(int page, int size, String city, String curriculum, String district, String grades) {
        LambdaQueryWrapper<School> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasText(city), School::getCity, city);
        wrapper.eq(StringUtils.hasText(curriculum), School::getCurriculum, curriculum);
        wrapper.eq(StringUtils.hasText(district), School::getDistrict, district);
        wrapper.like(StringUtils.hasText(grades), School::getGrades, grades);
        wrapper.orderByDesc(School::getId);

        return schoolMapper.selectPage(new Page<>(page, size), wrapper);
    }

    @Override
    public School getDetail(int id) {
        return schoolMapper.selectById(id);
    }

    @Override
    public List<School> compare(List<Integer> schoolIds) {
        return schoolMapper.selectBatchIds(schoolIds);
    }
}

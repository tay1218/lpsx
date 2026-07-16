package com.example.lpsx.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.lpsx.entity.School;
import com.example.lpsx.mapper.SchoolMapper;
import com.example.lpsx.service.HomeService;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HomeServiceImpl implements HomeService {

    @Resource
    private SchoolMapper schoolMapper;

    @Override
    public Map<String, Object> overview() {
        Map<String, Object> result = new LinkedHashMap<>();

        try {
            Long schoolCount = schoolMapper.selectCount(new LambdaQueryWrapper<>());
            result.put("schoolCount", schoolCount);
        } catch (Exception e) {
            log.error("查询学校总数失败", e);
            result.put("schoolCount", 0);
        }

        result.put("notices", Arrays.asList(
                "🔥 2026年上海国际学校最新排名已更新",
                "📢 浦东新区新增3所双语学校",
                "🎓 IB/AP/A-Level 课程体系对比指南上线"
        ));

        try {
            List<School> allSchools = schoolMapper.selectList(null);
            Collections.shuffle(allSchools);
            List<School> hotSchools = allSchools.stream().limit(10).collect(Collectors.toList());
            result.put("hotSchools", hotSchools);
        } catch (Exception e) {
            log.error("查询热门学校失败", e);
            result.put("hotSchools", new ArrayList<>());
        }

        List<Map<String, String>> shortcuts = new ArrayList<>();
        shortcuts.add(createShortcut("择校测评", "📝", "/pages/assessment/assessment"));
        shortcuts.add(createShortcut("学校库", "🏫", "/pages/school/school"));
        shortcuts.add(createShortcut("我的收藏", "❤️", "/pages/user/user"));
        shortcuts.add(createShortcut("留资咨询", "📞", "/pages/lead/lead"));
        result.put("shortcuts", shortcuts);

        return result;
    }

    private Map<String, String> createShortcut(String name, String icon, String path) {
        Map<String, String> item = new LinkedHashMap<>();
        item.put("name", name);
        item.put("icon", icon);
        item.put("path", path);
        return item;
    }
}

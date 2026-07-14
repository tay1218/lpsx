package com.example.lpsx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.lpsx.entity.School;
import com.example.lpsx.mapper.SchoolMapper;
import com.example.lpsx.service.HomeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 首页聚合服务实现
 * <p>
 * 公告 & 快捷入口为静态硬编码，不建表
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Resource
    private SchoolMapper schoolMapper;

    @Override
    public Map<String, Object> overview() {
        Map<String, Object> result = new LinkedHashMap<>();

        // 1. 学校总数
        Long schoolCount = schoolMapper.selectCount(new LambdaQueryWrapper<>());
        result.put("schoolCount", schoolCount);

        // 2. 滚动公告（静态）
        result.put("notices", Arrays.asList(
                "🔥 2026年上海国际学校最新排名已更新",
                "📢 浦东新区新增3所双语学校",
                "🎓 IB/AP/A-Level 课程体系对比指南上线"
        ));

        // 3. 热门学校（最新录入的前 6 所）
        LambdaQueryWrapper<School> hotWrapper = new LambdaQueryWrapper<>();
        hotWrapper.orderByDesc(School::getId).last("LIMIT 6");
        List<School> hotSchools = schoolMapper.selectList(hotWrapper);
        result.put("hotSchools", hotSchools);

        // 4. 快捷入口（静态 JSON）
        List<Map<String, String>> shortcuts = new ArrayList<>();
        shortcuts.add(createShortcut("择校测评", "📝", "/pages/assessment/index"));
        shortcuts.add(createShortcut("学校库", "🏫", "/pages/school/list"));
        shortcuts.add(createShortcut("我的收藏", "❤️", "/pages/favorite/list"));
        shortcuts.add(createShortcut("留资咨询", "📞", "/pages/lead/submit"));
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

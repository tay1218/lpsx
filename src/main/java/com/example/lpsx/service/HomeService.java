package com.example.lpsx.service;

import java.util.Map;

/**
 * 首页聚合服务
 */
public interface HomeService {

    /**
     * 首页聚合数据：学校数量、公告、热门学校、快捷入口
     */
    Map<String, Object> overview();
}

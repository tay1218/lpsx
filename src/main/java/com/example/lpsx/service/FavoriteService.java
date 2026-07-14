package com.example.lpsx.service;

import java.util.List;
import java.util.Map;

/**
 * 收藏服务
 */
public interface FavoriteService {

    /**
     * 切换收藏状态（有则取消，无则收藏）
     */
    void toggle(Integer userId, Integer schoolId);

    /**
     * 获取收藏列表（含学校名称）
     */
    List<Map<String, Object>> list(Integer userId);
}

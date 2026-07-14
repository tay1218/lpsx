package com.example.lpsx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.lpsx.entity.School;
import com.example.lpsx.entity.UserFavorite;
import com.example.lpsx.mapper.SchoolMapper;
import com.example.lpsx.mapper.UserFavoriteMapper;
import com.example.lpsx.service.FavoriteService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 收藏服务实现
 */
@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Resource
    private UserFavoriteMapper favoriteMapper;

    @Resource
    private SchoolMapper schoolMapper;

    @Override
    public void toggle(Integer userId, Integer schoolId) {
        LambdaQueryWrapper<UserFavorite> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFavorite::getUserId, userId)
               .eq(UserFavorite::getSchoolId, schoolId);
        UserFavorite exist = favoriteMapper.selectOne(wrapper);

        if (exist != null) {
            // 已收藏 → 取消
            favoriteMapper.deleteById(exist.getId());
        } else {
            // 未收藏 → 收藏
            UserFavorite fav = new UserFavorite();
            fav.setUserId(userId);
            fav.setSchoolId(schoolId);
            favoriteMapper.insert(fav);
        }
    }

    @Override
    public List<Map<String, Object>> list(Integer userId) {
        LambdaQueryWrapper<UserFavorite> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFavorite::getUserId, userId)
               .orderByDesc(UserFavorite::getCreateTime);
        List<UserFavorite> favorites = favoriteMapper.selectList(wrapper);

        List<Map<String, Object>> result = new ArrayList<>();
        for (UserFavorite fav : favorites) {
            School school = schoolMapper.selectById(fav.getSchoolId());
            if (school != null) {
                Map<String, Object> item = new LinkedHashMap<>();
                item.put("favoriteId", fav.getId());
                item.put("schoolId", school.getId());
                item.put("schoolName", school.getName());
                item.put("logoUrl", school.getLogoUrl());
                item.put("district", school.getDistrict());
                item.put("curriculum", school.getCurriculum());
                item.put("grades", school.getGrades());
                item.put("createTime", fav.getCreateTime());
                result.add(item);
            }
        }
        return result;
    }
}

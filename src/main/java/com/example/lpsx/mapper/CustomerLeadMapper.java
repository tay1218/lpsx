package com.example.lpsx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.lpsx.entity.CustomerLead;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 客户线索 Mapper
 */
@Mapper
public interface CustomerLeadMapper extends BaseMapper<CustomerLead> {

    /**
     * 24 小时内同手机号提交次数（防重）
     *
     * @param phone 联系电话
     * @return 24 小时内的提交数
     */
    @Select("SELECT COUNT(*) FROM customer_lead WHERE parent_phone = #{phone} AND create_time > DATE_SUB(NOW(), INTERVAL 24 HOUR)")
    int countByPhone24h(@Param("phone") String phone);
}

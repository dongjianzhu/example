package com.example.gome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.gome.entity.GomeOAuth2User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: dongjianzhu
 * @create: 2021-09-27 11:48
 **/
@Mapper
public interface GomeOAuth2UserMapper extends BaseMapper<GomeOAuth2User> {

    /**
     * 插入or更新
     * @param gomeOAuth2User  用户信息
     */
    void saveOrUpdate(GomeOAuth2User gomeOAuth2User);

}

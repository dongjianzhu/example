package com.example.gome.entity.mapper;

import com.example.gome.entity.GomeOAuth2User;
import gome.open.api.sdk.cloud.client.domain.bridge.client.vo.ShopInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author: dongjianzhu
 * @create: 2021-09-27 11:04
 **/
@Mapper
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    /**
     * 把shopInfo属性复制到GomeOAuth2User
     * @param shopInfo 商家信息
     * @return GomeOAuth2User
     */
    @Mapping(target = "name", source = "shopName")
    GomeOAuth2User shopToUser(ShopInfo shopInfo);

}

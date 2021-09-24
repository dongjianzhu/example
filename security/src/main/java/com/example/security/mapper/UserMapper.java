package com.example.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.security.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: dongjianzhu
 * @create: 2021-09-24 14:38
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {
}

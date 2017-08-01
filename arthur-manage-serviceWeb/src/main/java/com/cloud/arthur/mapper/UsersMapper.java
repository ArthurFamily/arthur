package com.cloud.arthur.mapper;

import com.cloud.arthur.bean.Users;
import com.cloud.arthur.config.mybatis.BaseMapper;

import java.util.List;

/**
 * Created by chenzhen on 2017/7/28.
 */
public interface UsersMapper extends BaseMapper<Users> {

    Users getByName(String name);

    List<Users> getUserList();
}

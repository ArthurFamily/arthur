package com.cloud.arthur.service;

import com.cloud.arthur.bean.Users;
import com.cloud.arthur.mapper.UsersMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenzhen on 2017/7/28.
 */
@Service
public class UsersService {

    @Autowired
    private UsersMapper usersMapper;

    public Users getUserByName(String name) {

        return usersMapper.getByName(name);
    }

    public List<Users> getUserList(int page, int pageSize) {

        PageHelper.startPage(page, pageSize);
        return usersMapper.getUserList();
    }
}

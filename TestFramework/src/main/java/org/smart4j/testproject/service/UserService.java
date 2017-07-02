package org.smart4j.testproject.service;

import org.smart4j.framework.annotations.Service;
import org.smart4j.framework.helper.DatabaseHelper;
import org.smart4j.testproject.model.User;

import java.util.List;

/**
 * Created by Yisa on 2017/7/2.
 */
@Service
public class UserService {

    /**
     * 获取客户列表
     */
    public List<User> getUserList() {
        String sql = "SELECT * FROM new_table";
        return DatabaseHelper.queryEntityList(User.class, sql);
    }

    /**
     * 获取客户
     */
    public User getUser(int id) {
        String sql = "SELECT * FROM new_table WHERE id = "+id;
        return DatabaseHelper.queryEntity(User.class,sql);
    }
}

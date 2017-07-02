package org.smart4j.testproject.controller;

import java.util.List;
import java.util.Map;


import org.smart4j.framework.annotations.Action;
import org.smart4j.framework.annotations.Controller;
import org.smart4j.framework.annotations.Inject;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;
import org.smart4j.testproject.model.User;
import org.smart4j.testproject.service.UserService;


/**
 * 处理客户管理相关请求
 */
@Controller
public class UserController {

    @Inject
    private UserService userService;

    /**
     * 显示客户基本信息
     */
    @Action("get:/index")
    public View show(Param param) {
        int id = 1;
        User user= userService.getUser(id);
        return new View("index.jsp").addModel("user", user);
    }


}
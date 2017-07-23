package com.yisaer.controller;

import com.yisaer.framework.annotations.Action;
import com.yisaer.framework.annotations.Controller;
import com.yisaer.framework.annotations.Inject;
import com.yisaer.framework.bean.Data;
import com.yisaer.framework.bean.Param;
import com.yisaer.framework.bean.View;
import com.yisaer.model.Customer;
import com.yisaer.service.CustomerService;

import java.util.List;
import java.util.Map;

/**
 * Created by Yisa on 2017/7/23.
 */
@Controller
public class CustomerControl {
    @Inject
    private CustomerService customerService;

    /**
     * Enter into Customer View
     */
    @Action("get:/customer")
    public View index(Param param) {
        List<Customer> customerList = customerService.getCustomerList();
        return new View("customer.jsp").addModel("customerList", customerList);
    }

    /**
     *  show Info
     */
    @Action("get:/customer_show")
    public View show(Param param) {
        long id = param.getLong("id");
        Customer customer = customerService.getCustomer(id);
        return new View("customer_show.jsp").addModel("customer", customer);
    }

    /**
     * 进入 创建客户 界面
     */
    @Action("get:/customer_create")
    public View create(Param param) {
        return new View("customer_create.jsp");
    }


    /**
     * Deal Edit Request
     */
    @Action("put:/customer_edit")
    public Data editSubmit(Param param) {
        long id = param.getLong("id");
        Map<String, Object> fieldMap = param.getFieldMap();
        boolean result = customerService.updateCustomer(id, fieldMap);
        return new Data(result);
    }

    /**
     * Deal Delete Request
     */
    @Action("delete:/customer_edit")
    public Data delete(Param param) {
        long id = param.getLong("id");
        boolean result = customerService.deleteCustomer(id);
        return new Data(result);
    }
}

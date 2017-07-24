package com.yisaer.service;

import com.yisaer.framework.annotations.Service;
import com.yisaer.framework.annotations.Transaction;
import com.yisaer.framework.helper.DatabaseHelper;
import com.yisaer.model.Customer;

import java.util.List;
import java.util.Map;

/**
 * Created by Yisa on 2017/7/23.
 */

@Service
public class CustomerService {
    /**
     *  Get CustomerList
     */

    @Transaction
    public List<Customer> getCustomerList() {
        String sql = "SELECT * FROM customer";
        return DatabaseHelper.queryEntityList(Customer.class, sql);
    }

    /**
     *  Get Customer
     */
    public Customer getCustomer(long id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        return DatabaseHelper.queryEntity(Customer.class, sql, id);
    }

    /**
     *  Create Customer
     */
    public boolean createCustomer(Map<String, Object> fieldMap) {
        return DatabaseHelper.insertEntity(Customer.class, fieldMap);
    }

    /**
     *  Update Customer
     */
    public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
        return DatabaseHelper.updateEntity(Customer.class, id, fieldMap);
    }

    /**
     *  Delete Customer
     */
    public boolean deleteCustomer(long id) {
        return DatabaseHelper.deleteEntity(Customer.class, id);
    }
}

package com.lanou.bookstore.admin.dao;

import com.lanou.bookstore.admin.domain.AdminBean;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * Created by dllo on 17/12/27.
 */
public class AdminDao {
    private GxQueryRunner runner = new GxQueryRunner();

    public AdminBean findByAdminname(String adminname){
        AdminBean bean = new AdminBean();
        String sql = " select * from admin where adminname = ? ";
        try {
            bean = runner.query(sql, new BeanHandler<AdminBean>(AdminBean.class),adminname);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }
}

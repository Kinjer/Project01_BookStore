package com.lanou.bookstore.user.dao;

import com.lanou.bookstore.user.domain.UserBean;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

/**
 * Created by dllo on 17/12/22.
 */
public class UserDao {
    private GxQueryRunner runner = new GxQueryRunner();

    public UserBean findByUsername(String username){
//        int count = 0;
        UserBean bean = new UserBean();
        String sql = " select * from USER where username = ? ";

        try {
//            Long num = runner.query(sql, new ScalarHandler<>(),username);
//            count = num.intValue();

            bean = runner.query(sql, new BeanHandler<UserBean>(UserBean.class),username);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public UserBean findByEmail(String email){
//        int count = 0;
        UserBean bean = new UserBean();
        String sql = " select * from USER where email = ? ";

        try {
//            Long num = runner.query(sql, new ScalarHandler<>(),email);
//            count = num.intValue();

            bean = runner.query(sql, new BeanHandler<UserBean>(UserBean.class), email);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public boolean add(UserBean userBean){
        int flag = 0;
        String sql = " insert into USER values (?,?,?,?,?,?) ";

        try {
            flag = runner.update(sql
                    ,userBean.getUid()
                    ,userBean.getUsername()
                    ,userBean.getPassword()
                    ,userBean.getEmail()
                    ,userBean.getCode()
                    ,userBean.isState()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (flag>0){
                return true;
            }else {
                return false;
            }
        }
    }


}

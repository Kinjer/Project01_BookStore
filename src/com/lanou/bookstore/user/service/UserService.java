package com.lanou.bookstore.user.service;

import com.lanou.bookstore.user.dao.UserDao;
import com.lanou.bookstore.user.domain.UserBean;
import com.lanou.bookstore.user.domain.VerifyBean;

/**
 * Created by dllo on 17/12/22.
 */
public class UserService {
    private UserDao userDao = new UserDao();
    private String msg;

    public VerifyBean regist(UserBean userBean) {
        VerifyBean verifyBean = new VerifyBean();
        msg = null;
        msg = verifyUsername(userBean);
        //通过用户名验证
        if (msg == null) {
            msg = verifyEmail(userBean);
            //通过邮箱验证
            if (msg == null) {
                msg = add(userBean);
                verifyBean.setVerifyMsg(msg);
                verifyBean.setPassVerify(true);
            } else {
                verifyBean.setVerifyMsg(msg);
                verifyBean.setPassVerify(false);
            }
        } else {
            verifyBean.setVerifyMsg(msg);
            verifyBean.setPassVerify(false);
        }

        return verifyBean;
    }

    public String verifyUsername(UserBean userBean) {
        String username = userBean.getUsername();
//        int result = userDao.findByUsername(username);
        UserBean resultBean = userDao.findByUsername(username);

        if (resultBean != null) {
            msg = "用户名已被注册!";
        }
        return msg;
    }

    public String verifyEmail(UserBean userBean) {
        String email = userBean.getEmail();
//        int result = userDao.findByEmail(email);
        UserBean resultBean = userDao.findByEmail(email);
        if (resultBean != null) {
            msg = "Email已被注册!";
        }
        return msg;
    }

    public String add(UserBean userBean) {
        boolean result = userDao.add(userBean);
        if (result) {
            msg = "注册成功!";
        } else {
            msg = "注册失败, 请联系管理员";
        }
        return msg;
    }

    public String userLogin(UserBean userBean) {

        msg = null;
        UserBean resultBean = userDao.findByUsername(userBean.getUsername());
        if (resultBean == null) {
            msg = "用户不存在";
        } else {
            if (!userBean.getPassword().equals(resultBean.getPassword())) {
                msg = "密码错误";
            }
        }
        return msg;
    }


}

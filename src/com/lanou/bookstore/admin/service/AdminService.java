package com.lanou.bookstore.admin.service;

import com.lanou.bookstore.admin.dao.AdminDao;
import com.lanou.bookstore.admin.domain.AdminBean;

/**
 * Created by dllo on 17/12/27.
 */
public class AdminService {
    private AdminDao adminDao = new AdminDao();

    public String adminLogin(AdminBean adminBean) {
        String msg = "";
        msg = null;
        AdminBean resultBean = adminDao.findByAdminname(adminBean.getAdminname());
        if (resultBean == null) {
            msg = "用户不存在";
        } else {
            if (!adminBean.getPassword().equals(resultBean.getPassword())) {
                msg = "密码错误";
            }
        }
        return msg;
    }
}

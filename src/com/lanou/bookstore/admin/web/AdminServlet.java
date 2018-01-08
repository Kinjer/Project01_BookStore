package com.lanou.bookstore.admin.web;

import com.lanou.bookstore.admin.domain.AdminBean;
import com.lanou.bookstore.admin.service.AdminService;
import com.lanou.commons.CommonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by dllo on 17/12/27.
 */
@WebServlet(name = "AdminServlet", urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    private AdminService service = new AdminService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf8");
        request.setCharacterEncoding("utf8");

        String method = request.getParameter("method");
        switch (method){
            case "login":
                login(request,response);
                break;
            case "adminExit":
                adminExit(request,response);
            default:
                break;
        }
    }

    private void adminExit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("sessionadmin");
        request.setAttribute("msg","您已经退出");
        request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request,response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AdminBean adminBean = new AdminBean();
        adminBean = CommonUtils.toBean(request.getParameterMap(), AdminBean.class);

        String user = adminBean.getAdminname();
        String pwd = adminBean.getPassword();

        Cookie userCookie = new Cookie("admin", URLEncoder.encode(user,"utf8"));
        Cookie pwdCookie = new Cookie("adminpwd",URLEncoder.encode(pwd,"utf8"));

        response.addCookie(userCookie);
        response.addCookie(pwdCookie);

        String msg = service.adminLogin(adminBean);
        if (msg == null){
            HttpSession session = request.getSession();
            session.setAttribute("sessionadmin",user);

            request.getRequestDispatcher("/adminjsps/admin/index.jsp").forward(request,response);
        }else {
            request.setAttribute("msg",msg);
            request.getRequestDispatcher("/adminjsps/login.jsp").forward(request,response);
        }
    }
}

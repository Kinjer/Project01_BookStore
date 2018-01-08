package com.lanou.bookstore.user.web;

import com.lanou.bookstore.cart.domain.Cart;
import com.lanou.bookstore.cart.domain.CartItem;
import com.lanou.bookstore.user.domain.UserBean;
import com.lanou.bookstore.user.domain.VerifyBean;
import com.lanou.bookstore.user.service.UserService;
import com.lanou.commons.CommonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 17/12/22.
 */
@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    private UserService service = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf8");
        request.setCharacterEncoding("utf8");

        String method = request.getParameter("method");
        switch (method) {
            case "regist":
                regist(request, response);
                break;
            case "login":
                login(request, response);
                break;
            case "userExit":
                userExit(request, response);
            default:
                break;
        }

    }

    private void userExit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("sessionuser");
        request.setAttribute("msg", "您已经退出");
        request.getRequestDispatcher("/jsps/msg.jsp").forward(request, response);

    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserBean userBean = new UserBean();
        userBean = CommonUtils.toBean(request.getParameterMap(), UserBean.class);

        String user = userBean.getUsername();
        String pwd = userBean.getPassword();

        //是否记住账号密码
        String isRemember = request.getParameter("rememberMe");
        if (isRemember != null && isRemember.equals("re")) {
            Cookie userCookie = new Cookie("user", URLEncoder.encode(user, "utf8"));
//        userCookie.setMaxAge(60*60);
            Cookie pwdCookie = new Cookie("pwd", URLEncoder.encode(pwd, "utf8"));
//        pwdCookie.setMaxAge(60*60);

            response.addCookie(userCookie);
            response.addCookie(pwdCookie);
        } else {
            boolean result = checkUserInCookie(request, user, pwd);
            if (result) {
                Cookie userCookie = new Cookie("user", URLEncoder.encode(user, "utf8"));
                Cookie pwdCookie = new Cookie("pwd", URLEncoder.encode(pwd, "utf8"));

                response.addCookie(userCookie);
                response.addCookie(pwdCookie);
            } else {
                Cookie userCookie = new Cookie("user", "");
                userCookie.setMaxAge(0);
                Cookie pwdCookie = new Cookie("pwd", "");
                pwdCookie.setMaxAge(0);

                response.addCookie(userCookie);
                response.addCookie(pwdCookie);
            }
        }

        String msg = service.userLogin(userBean);
        if (msg == null) {
            HttpSession session = request.getSession();
            session.setAttribute("sessionuser", user);
//            session.setAttribute("sessionpwd",pwd);

//            //登陆成功后在session中添加一辆购物车
//            Map<String,CartItem> cartItemMap = new HashMap<>();
//            Cart cart = new Cart(cartItemMap);
//            session.setAttribute("cart",cart);

            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/jsps/user/login.jsp").forward(request, response);
        }


    }

    public boolean checkUserInCookie(HttpServletRequest request, String user, String pwd) throws UnsupportedEncodingException {
        String userName = "";
        String password = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    userName = URLDecoder.decode(cookie.getValue(), "utf-8");
                } else if (cookie.getName().equals("pwd")) {
                    password = URLDecoder.decode(cookie.getValue(), "utf-8");
                }
            }
        }
        if (user.equals(userName) && pwd.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    private void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserBean userBean = new UserBean();
        userBean = CommonUtils.toBean(request.getParameterMap(), UserBean.class);
        userBean.setUid(CommonUtils.uuid());

        VerifyBean verifyBean = service.regist(userBean);
        String msg = verifyBean.getVerifyMsg();
        request.setAttribute("msg", msg);
        if (verifyBean.isPassVerify()) {
            HttpSession session = request.getSession();
            session.setAttribute("sessionuser", userBean.getUsername());
            request.getRequestDispatcher("/jsps/msg.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/jsps/user/regist.jsp").forward(request, response);
        }

    }
}

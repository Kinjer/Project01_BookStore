package com.lanou.bookstore.order.web;

import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.service.OrderService;
import com.lanou.bookstore.user.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by dllo on 17/12/27.
 */
@WebServlet(name = "AdminOrderServlet", urlPatterns = "/adminOrder")
public class AdminOrderServlet extends HttpServlet {
    private OrderService service = new OrderService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf8");
        request.setCharacterEncoding("utf8");

        String method = request.getParameter("method");
        switch (method) {
            case "myOrders":
                myOrders(request, response);
                break;
            case "sendGoods":
                sendGoods(request, response);
                break;
            default:
                break;
        }
    }

    private void sendGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");
        boolean result = service.sendGoods(oid);
        if (result) {
            List<Order> orderList = service.queryMyOrdersByState(1);
            request.setAttribute("myOrderList", orderList);
        } else {
            request.setAttribute("sendFlag", "发货失败!");
        }
        request.getRequestDispatcher("/adminjsps/admin/order/list.jsp").forward(request, response);

    }


    private void myOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int state = Integer.valueOf(request.getParameter("state"));
        List<Order> orderList = service.queryMyOrdersByState(state);
        request.setAttribute("myOrderList", orderList);
        request.getRequestDispatcher("/adminjsps/admin/order/list.jsp").forward(request, response);


    }
}

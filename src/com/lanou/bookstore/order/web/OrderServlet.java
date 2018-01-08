package com.lanou.bookstore.order.web;

import com.lanou.bookstore.cart.domain.Cart;
import com.lanou.bookstore.cart.domain.CartItem;
import com.lanou.bookstore.cart.domain.NewCart;
import com.lanou.bookstore.cart.service.NewCartService;
import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.domain.OrderItem;
import com.lanou.bookstore.order.service.OrderService;
import com.lanou.bookstore.user.dao.UserDao;
import com.lanou.bookstore.user.service.UserService;
import com.lanou.commons.CommonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dllo on 17/12/23.
 */
@WebServlet(name = "OrderServlet", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
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
            case "addOrder":
                addOrder(request, response);
                break;
            case "myOrders":
                myOrders(request, response);
                break;
            case "loadOrder":
                loadOrder(request, response);
                break;
            case "payment":
                payment(request, response);
                break;
            case "confirm":
                confirm(request, response);
                break;
            default:
                break;
        }
    }

    private void confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");
        boolean result = service.confirm(oid);
        if (result) {
            request.setAttribute("msg", "订单号: " + oid + "  确认成功, 交易完成!");
        } else {
            request.setAttribute("msg", "确认失败!");
        }
        request.getRequestDispatcher("/jsps/msg.jsp").forward(request, response);
    }

    private void payment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = request.getParameter("address");
        String oid = request.getParameter("oid");
        String price = request.getParameter("price");
        boolean result = service.payment(address, oid);
        if (result) {
            request.setAttribute("msg", "订单号: " + oid + "  支付金额: " + price + "元  支付成功!");
        } else {
            request.setAttribute("msg", "支付失败!");
        }
        request.getRequestDispatcher("/jsps/msg.jsp").forward(request, response);
    }

    private void loadOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");
        Order order = service.loadOrder(oid);
        request.setAttribute("orderInfo", order);
        request.getRequestDispatcher("/jsps/order/desc.jsp").forward(request, response);

    }

    private void myOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDao userDao = new UserDao();
        String username = (String) session.getAttribute("sessionuser");
        if (username != null) {
            String uid = userDao.findByUsername((String) session.getAttribute("sessionuser")).getUid();
            List<Order> orderList = service.myOrders(uid);
            request.setAttribute("myOrderList", orderList);
            request.getRequestDispatcher("/jsps/order/list.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/jsps/user/login.jsp").forward(request, response);

        }

    }

//    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        Cart cart = (Cart) session.getAttribute("cart");
//        UserDao userDao = new UserDao();
//        String uid = userDao.findByUsername((String) session.getAttribute("sessionuser")).getUid();
//        String oid = CommonUtils.uuid();
//        double sumPrice = Double.valueOf(request.getParameter("sum"));
//        //获取当前时间作为下订单时间
//        Date date = new Date();
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String ordertime = df.format(date);
//
//        Order order = new Order(oid, uid, "", ordertime, 0, sumPrice);
//        boolean addResult = service.add(order, cart);
//        //订单生成成功后, 将Cart中的信息传给jsp
//        if (addResult) {
//            List<OrderItem> oiList = new ArrayList<>();
//            for (CartItem cartItem : cart.getCartMap().values()) {
//                int count = cartItem.getCount();
//                double subtotal = cartItem.getBookBean().getPrice() * cartItem.getCount();
//                String bname = cartItem.getBookBean().getBname();
//                String author = cartItem.getBookBean().getAuthor();
//                String image = cartItem.getBookBean().getImage();
//                double price = cartItem.getBookBean().getPrice();
//                OrderItem orderItem = new OrderItem(count, subtotal, bname, author, image, price);
//                oiList.add(orderItem);
//            }
//            order.setOrderItemList(oiList);
////            order.setCart(cart);
//            request.setAttribute("orderInfo", order);
//            request.getRequestDispatcher("/jsps/order/desc.jsp").forward(request, response);
//            cart.clearCart();
//            session.setAttribute("cart", cart);
//        } else {
//            request.setAttribute("msg", "生成订单失败");
//            request.getRequestDispatcher("/jsps/msg.jsp").forward(request, response);
//        }
//
//    }

    private void addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        NewCartService newCartService = new NewCartService();
        UserDao userDao = new UserDao();
        String uid = userDao.findByUsername((String) session.getAttribute("sessionuser")).getUid();
        List<NewCart> carList = newCartService.getCartList(uid);

//        if (carList.size() != 0) {

        String oid = CommonUtils.uuid();
        double sumPrice = Double.valueOf(request.getParameter("sum"));
        //获取当前时间作为下订单时间
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ordertime = df.format(date);

        Order order = new Order(oid, uid, "", ordertime, 0, sumPrice);
        boolean addResult = service.add(order, carList);
        //订单生成成功后, 将Cart中的信息传给jsp
        if (addResult) {
            List<OrderItem> oiList = new ArrayList<>();
            for (NewCart cart : carList) {
                int count = cart.getCount();
                double subtotal = cart.getPrice() * cart.getCount();
                String bname = cart.getBname();
                String author = cart.getAuthor();
                String image = cart.getImage();
                double price = cart.getPrice();
                OrderItem orderItem = new OrderItem(count, subtotal, bname, author, image, price);
                oiList.add(orderItem);
            }
            order.setOrderItemList(oiList);

            newCartService.clear(uid);//将购物车清空

            request.setAttribute("orderInfo", order);
            request.getRequestDispatcher("/jsps/order/desc.jsp").forward(request, response);


        } else {
            request.setAttribute("msg", "生成订单失败");
            request.getRequestDispatcher("/jsps/msg.jsp").forward(request, response);
        }
    }

//    }

}

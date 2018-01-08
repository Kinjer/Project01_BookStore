package com.lanou.bookstore.cart.web;

import com.lanou.bookstore.book.domain.BookBean;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.bookstore.cart.domain.Cart;
import com.lanou.bookstore.cart.domain.CartItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/12/23.
 */
@WebServlet(name = "CartServlet", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf8");
        request.setCharacterEncoding("utf8");

        String method = request.getParameter("method");
        switch (method){
            case "addCart":
                addCart(request,response);
                break;
            case "clearCart":
                clearCart(request,response);
                break;
            case "deleteFromCart":
                deleteFromCart(request,response);
                break;
            default:
                break;
        }
    }

    private void deleteFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        String bid = request.getParameter("bid");
        cart.deleteFormCart(bid);
        session.setAttribute("cart",cart);
        request.getRequestDispatcher("/jsps/cart/list.jsp").forward(request,response);
    }

    private void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        cart.clearCart();
        session.setAttribute("cart",cart);
        request.getRequestDispatcher("/jsps/cart/list.jsp").forward(request,response);
    }

    private void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("sessionuser");
        if (username != null) {
            Cart cart = (Cart) session.getAttribute("cart");
            String bid = request.getParameter("bid");
            int count = Integer.valueOf(request.getParameter("count"));

            BookService bookService = new BookService();
            BookBean book = bookService.showBookDesc(bid);

            CartItem cartItem = new CartItem(count, book);

            Map<String, CartItem> cartMap = cart.getCartMap();
            cartMap.put(bid, cartItem);
            cart.setCartMap(cartMap);
            session.setAttribute("cart", cart);

            request.getRequestDispatcher("/jsps/cart/list.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("/jsps/user/login.jsp").forward(request, response);
//            PrintWriter out = response.getWriter();
//            out.write("<script>window.parent.location.href='/jsps/user/login.jsp'</script>");
        }
    }
}

package com.lanou.bookstore.cart.web;

import com.lanou.bookstore.book.domain.BookBean;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.bookstore.cart.domain.NewCart;
import com.lanou.bookstore.cart.service.NewCartService;
import com.lanou.commons.CommonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by dllo on 17/12/26.
 */
@WebServlet(name = "NewCartServlet", urlPatterns = "/newCart")
public class NewCartServlet extends HttpServlet {
    private NewCartService service = new NewCartService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf8");
        request.setCharacterEncoding("utf8");

        String method = request.getParameter("method");
        switch (method) {
            case "addCart":
                addCart(request, response);
                break;
            case "clearCart":
                clearCart(request, response);
                break;
            case "deleteFromCart":
                deleteFromCart(request, response);
                break;
            case "showCart":
                showCart(request, response);
                break;
            default:
                break;
        }
    }

    private void showCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("sessionuser");
        if (username != null) {
            String uid = service.findUidByUsername(username);
            List<NewCart> carList = service.getCartList(uid);
            request.setAttribute("carListByUid", carList);
            request.getRequestDispatcher("/jsps/cart/list.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/jsps/user/login.jsp").forward(request, response);
//            PrintWriter out = response.getWriter();
//            out.write("<script>window.parent.location.href='/jsps/user/login.jsp'</script>");
        }

    }

    private void deleteFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carid = request.getParameter("carid");

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("sessionuser");
        String uid = service.findUidByUsername(username);
        boolean result = service.deleteOne(carid);
        if (result) {
            List<NewCart> carList = service.getCartList(uid);
            request.setAttribute("carListByUid", carList);

            request.getRequestDispatcher("/jsps/cart/list.jsp").forward(request, response);

        }

    }

    private void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("sessionuser");
        String uid = service.findUidByUsername(username);
        boolean result = service.clear(uid);
        if (result) {
            List<NewCart> carList = service.getCartList(uid);
            request.setAttribute("carListByUid", carList);

            request.getRequestDispatcher("/jsps/cart/list.jsp").forward(request, response);

        }
    }

    private void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("sessionuser");
        if (username != null) {
            String bid = request.getParameter("bid");
            int count = Integer.valueOf(request.getParameter("count"));
            String uid = service.findUidByUsername(username);

            BookService bookService = new BookService();
            BookBean book = bookService.showBookDesc(bid);

            service.verifyBookFromCart(uid, book.getBname(), count, book);

            List<NewCart> carList = service.getCartList(uid);
            request.setAttribute("carListByUid", carList);

            request.getRequestDispatcher("/jsps/cart/list.jsp").forward(request, response);

        } else {
            request.getRequestDispatcher("/jsps/user/login.jsp").forward(request, response);
//            PrintWriter out = response.getWriter();
//            out.write("<script>window.parent.location.href='/jsps/user/login.jsp'</script>");
        }
    }
}

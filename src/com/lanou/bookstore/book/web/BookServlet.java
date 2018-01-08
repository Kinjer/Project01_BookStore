package com.lanou.bookstore.book.web;

import com.lanou.bookstore.book.domain.BookBean;
import com.lanou.bookstore.book.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dllo on 17/12/22.
 */
@WebServlet(name = "BookServlet", urlPatterns = "/booklist")
public class BookServlet extends HttpServlet {
    private BookService service = new BookService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf8");
        request.setCharacterEncoding("utf8");

        String method = request.getParameter("method");
        switch (method){
            case "showBookList":
                showBookList(request,response);
                break;
            case "showBookDesc":
                showBookDesc(request,response);
            default:
                break;
        }
    }

    private void showBookDesc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid = request.getParameter("bid");
        BookBean bookBean = service.showBookDesc(bid);

        request.setAttribute("bookInfo",bookBean);
        request.getRequestDispatcher("/jsps/book/desc.jsp").forward(request,response);

    }

    private void showBookList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        List<BookBean> bookBeanList = service.showBookList(category);

        request.setAttribute("bookList",bookBeanList);
        request.getRequestDispatcher("/jsps/book/list.jsp").forward(request,response);
    }
}

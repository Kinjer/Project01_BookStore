package com.lanou.bookstore.category.web;

import com.lanou.bookstore.category.domain.CategoryBean;
import com.lanou.bookstore.category.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dllo on 17/12/25.
 */
@WebServlet(name = "CategoryServlet", urlPatterns = "/category")
public class CategoryServlet extends HttpServlet {
    private CategoryService service = new CategoryService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf8");
        request.setCharacterEncoding("utf8");

        String method = request.getParameter("method");
        switch (method){
            case "bookCategory":
                bookCategory(request,response);
                break;
            default:
                break;
        }
    }

    private void bookCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CategoryBean> categoryBean = service.getBookCategory();
        request.setAttribute("categoryBean",categoryBean);
        request.getRequestDispatcher("/jsps/left.jsp").forward(request,response);
    }
}

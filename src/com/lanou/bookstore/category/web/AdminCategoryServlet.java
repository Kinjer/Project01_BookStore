package com.lanou.bookstore.category.web;

import com.lanou.bookstore.book.domain.BookBean;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.bookstore.category.domain.CategoryBean;
import com.lanou.bookstore.category.service.CategoryService;
import com.lanou.commons.CommonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dllo on 17/12/27.
 */
@WebServlet(name = "AdminCategoryServlet", urlPatterns = "/adminCategory")
public class AdminCategoryServlet extends HttpServlet {
    private CategoryService service = new CategoryService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf8");
        request.setCharacterEncoding("utf8");

        String method = request.getParameter("method");
        switch (method) {
            case "showCategoryList":
                showCategoryList(request, response);
                break;
            case "addCategory":
                addCategory(request, response);
                break;
            case "queryByCid":
                queryByCid(request, response);
                break;
            case "editCategory":
                editCategory(request, response);
                break;
            case "delCategory":
                delCategory(request,response);
                break;
            default:
                break;
        }
    }

    private void delCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        BookService bookService = new BookService();
        List<BookBean> bookBeanList = bookService.showBookList(cid);
        if (bookBeanList.size() == 0){
            boolean result = service.delCategory(cid);
            if (result) {
                request.setAttribute("msg", "删除分类成功!");
            } else {
                request.setAttribute("msg", "删除分类失败!");
            }
        }else {
            request.setAttribute("msg", "此分类中还有图书, 无法删除!");
        }
        List<CategoryBean> categoryList = service.showCategoryList();
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("/adminjsps/admin/category/list.jsp").forward(request, response);
    }

    private void editCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryBean bean = CommonUtils.toBean(request.getParameterMap(), CategoryBean.class);
        boolean result = service.editCategory(bean);
        if (result) {
            request.setAttribute("msg", "修改分类成功!");
        } else {
            request.setAttribute("msg", "修改分类失败!");
        }
        request.getRequestDispatcher("/adminjsps/admin/category/mod.jsp").forward(request, response);
    }

    private void queryByCid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        CategoryBean bean = service.queryByCid(cid);
        request.setAttribute("categoryInfo", bean);
        request.getRequestDispatcher("/adminjsps/admin/category/mod.jsp").forward(request, response);
    }

    private void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryBean bean = CommonUtils.toBean(request.getParameterMap(), CategoryBean.class);
        boolean result = service.addCategory(bean);
        if (result) {
            request.setAttribute("msg", "添加分类成功!");
        } else {
            request.setAttribute("msg", "添加分类失败!");
        }
        request.getRequestDispatcher("/adminjsps/admin/category/add.jsp").forward(request, response);

    }

    private void showCategoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CategoryBean> categoryList = service.showCategoryList();
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("/adminjsps/admin/category/list.jsp").forward(request, response);
    }
}

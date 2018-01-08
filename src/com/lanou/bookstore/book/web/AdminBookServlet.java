package com.lanou.bookstore.book.web;

import com.lanou.bookstore.book.domain.BookBean;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.bookstore.category.domain.CategoryBean;
import com.lanou.bookstore.category.service.CategoryService;
import com.lanou.commons.CommonUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by dllo on 17/12/27.
 */
@WebServlet(name = "AdminBookServlet", urlPatterns = "/adminBook")
public class AdminBookServlet extends HttpServlet {
    private BookService service = new BookService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf8");
        request.setCharacterEncoding("utf8");

        String method = request.getParameter("method");
        switch (method) {
            case "showBookList":
                showBookList(request, response);
                break;
            case "showBookDesc":
                showBookDesc(request, response);
                break;
            case "loadAddBookCg":
                loadAddBookCg(request, response);
                break;
            case "addBook":
                addBook(request, response);
                break;
            case "mod":
                mod(request, response);
                break;
            case "del":
                del(request,response);
                break;
            default:
                break;
        }
    }

    private void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookBean bookBean = CommonUtils.toBean(request.getParameterMap(), BookBean.class);
        boolean result = service.delBook(bookBean);
        if (result) {
            request.setAttribute("msg", "操作成功!");
            request.setAttribute("delFlag", "delFlag");
        } else {
            request.setAttribute("msg", "操作失败!");
        }
        request.getRequestDispatcher("/adminjsps/admin/book/desc.jsp").forward(request, response);
    }

    private void mod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookBean bookBean = CommonUtils.toBean(request.getParameterMap(), BookBean.class);
        String strPrice = request.getParameter("price");
        double price = Double.valueOf(strPrice.replace("元",""));
        bookBean.setPrice(price);
        boolean result = service.modBook(bookBean);
        if (result) {
            BookBean bean = service.showBookDesc(bookBean.getBid());
            request.setAttribute("bookInfo", bean);
            CategoryService cgService = new CategoryService();
            List<CategoryBean> categoryList = cgService.showCategoryList();
            request.setAttribute("categoryList", categoryList);
            request.setAttribute("msg", "操作成功!");
        } else {
            request.setAttribute("msg", "操作失败!");
        }
        request.getRequestDispatcher("/adminjsps/admin/book/desc.jsp").forward(request, response);
    }


    private void loadAddBookCg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryService cgService = new CategoryService();
        List<CategoryBean> categoryList = cgService.showCategoryList();
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
    }

    private void showBookDesc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid = request.getParameter("bid");
        BookBean bookBean = service.showBookDesc(bid);
        CategoryService cgService = new CategoryService();
        List<CategoryBean> categoryList = cgService.showCategoryList();
        request.setAttribute("categoryList", categoryList);

        request.setAttribute("bookInfo", bookBean);
        request.getRequestDispatcher("/adminjsps/admin/book/desc.jsp").forward(request, response);
    }

    private void showBookList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BookBean> bookBeanList = service.showBookList("0");//0代表查询所有图书
        request.setAttribute("bookList", bookBeanList);
        request.getRequestDispatcher("/adminjsps/admin/book/list.jsp").forward(request, response);

    }


    private void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        BookBean bookBean = CommonUtils.toBean(request.getParameterMap(),BookBean.class);

        //文件上传的三部曲
        //创建工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //创建解析器
        ServletFileUpload sfu = new ServletFileUpload(factory);
        //设置上传文件的大小
        sfu.setFileSizeMax(2 * 1024 * 1024);
        //解析request
        try {
            List<FileItem> list = sfu.parseRequest(request);
            BookBean b = new BookBean();
            b.setBid(CommonUtils.uuid());
            String bname = list.get(0).getString("utf-8");
            b.setBname(bname);
            String price = list.get(2).getString("utf-8");
            b.setPrice(Double.valueOf(price));
            String author = list.get(3).getString("utf-8");
            b.setAuthor(author);
            String cid = list.get(4).getString("utf-8");
            b.setCid(cid);

            //设置图片保存的目录
            String path = this.getServletContext().getRealPath("/book_img");
//            String path="/Users/dllo/Desktop/JavaCode/BookStore/web/book_img";
            //得到文件名称
            String fileName = CommonUtils.uuid() + "_" + list.get(1).getName();
            //校验图片的格式
            if (!fileName.toLowerCase().endsWith("jpg")) {
                System.out.println("图片格式不是jpg");
                request.setAttribute("msg", "你的图片格式不是jpg格式");
                request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
                return;
            }
            File fileDir = new File(path);
            if (!fileDir.exists()) {
                System.out.println("不存在" + path);
            }
            //使用目录和文件名称创建目标文件
            File f = new File(path, fileName);
            //保存文件
            list.get(1).write(f);
            //校验图片的尺寸
//            Image image=new ImageIcon(f.getAbsolutePath()).getImage();
//            if(image.getWidth(null)>200 || image.getHeight(null)>200){
//                f.delete();
//                request.setAttribute("msg", "图片的尺寸太大");
//                request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
//
//            }
            //设置book的属性
            b.setImage("book_img/" + fileName);
            service.addBook(b);
            request.setAttribute("msg", "添加成功");
            request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);

        } catch (FileUploadException e) {
            if (e instanceof FileUploadBase.FileSizeLimitExceededException) {

                request.setAttribute("msg", "你的图片大于1.5M");
                request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
                return;
            }

            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}

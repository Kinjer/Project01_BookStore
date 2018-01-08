package com.lanou.bookstore.book.dao;

import com.lanou.bookstore.book.domain.BookBean;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/12/22.
 */
public class BookDao {
    private GxQueryRunner runner = new GxQueryRunner();

    public List<BookBean> showBookList(String category) {
        List<BookBean> bookBeanList = new ArrayList<>();

        StringBuffer sql = new StringBuffer(" select * from book ");

        try {
            if (!category.equals("0")) {
                sql.append(" where cid = ? and delflag = 0 ");
                bookBeanList = runner.query(sql.toString(), new BeanListHandler<BookBean>(BookBean.class), category);
            } else {
                sql.append(" where delflag = 0 ");
                bookBeanList = runner.query(sql.toString(), new BeanListHandler<BookBean>(BookBean.class));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookBeanList;
    }

    public BookBean showBookDesc(String bid) {
        BookBean bookBean = new BookBean();
        String sql = " select * from book where bid = ? ";
        try {
            bookBean = runner.query(sql, new BeanHandler<BookBean>(BookBean.class), bid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookBean;
    }

    public boolean addBook(BookBean bean) {
        int flag = 0;
        String sql = " insert into book values(?,?,?,?,?,?) ";
        try {
            flag = runner.update(sql
                    , bean.getBid()
                    , bean.getBname()
                    , bean.getPrice()
                    , bean.getAuthor()
                    , bean.getImage()
                    , bean.getCid()
                    , 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (flag > 0) {
                return true;
            } else {
                return false;
            }
        }

    }

    public boolean modBook(BookBean bean) {
        int flag = 0;
        String sql = " update book set bname = ?, price = ?, author = ?, cid = ? where bid = ? ";
        try {
            flag = runner.update(sql
                    , bean.getBname()
                    , bean.getPrice()
                    , bean.getAuthor()
                    , bean.getCid()
                    , bean.getBid());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (flag > 0) {
                return true;
            } else {
                return false;
            }
        }

    }

    public boolean delBook(BookBean bean) {
        int flag = 0;
        String sql = " update book set delflag = 1 where bid = ? ";
        try {
            flag = runner.update(sql, bean.getBid());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (flag > 0) {
                return true;
            } else {
                return false;
            }
        }

    }

}

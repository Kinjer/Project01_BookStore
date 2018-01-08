package com.lanou.bookstore.book.service;

import com.lanou.bookstore.book.dao.BookDao;
import com.lanou.bookstore.book.domain.BookBean;

import java.util.List;

/**
 * Created by dllo on 17/12/22.
 */
public class BookService {
    private BookDao bookDao = new BookDao();

    public List<BookBean> showBookList(String category){
        return bookDao.showBookList(category);
    }

    public BookBean showBookDesc(String bid){
        return bookDao.showBookDesc(bid);
    }

    public boolean addBook(BookBean bean) {
        return bookDao.addBook(bean);
    }

    public boolean modBook(BookBean bean) {
        return bookDao.modBook(bean);
    }

    public boolean delBook(BookBean bean) {
        return bookDao.delBook(bean);
    }
}

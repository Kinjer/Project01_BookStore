package com.lanou.bookstore.cart.domain;

import com.lanou.bookstore.book.domain.BookBean;

/**
 * Created by dllo on 17/12/23.
 */
public class CartItem {
    private int count;
    private BookBean bookBean;

    public CartItem() {
    }

    public CartItem(int count, BookBean bookBean) {
        this.count = count;
        this.bookBean = bookBean;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BookBean getBookBean() {
        return bookBean;
    }

    public void setBookBean(BookBean bookBean) {
        this.bookBean = bookBean;
    }
}

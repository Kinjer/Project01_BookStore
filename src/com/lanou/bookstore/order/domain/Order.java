package com.lanou.bookstore.order.domain;


import com.lanou.bookstore.cart.domain.Cart;

import java.util.List;

/**
 * Created by dllo on 17/12/23.
 */
public class Order {
    private String oid,uid,address,ordertime;
    private int state;
    private double price;
    private Cart cart;

    private List<OrderItem> orderItemList;

    public Order() {
    }

    public Order(String oid, String uid, String address, String ordertime, int state, double price) {
        this.oid = oid;
        this.uid = uid;
        this.address = address;
        this.ordertime = ordertime;
        this.state = state;
        this.price = price;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}

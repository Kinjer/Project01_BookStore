package com.lanou.bookstore.order.domain;

/**
 * Created by dllo on 17/12/23.
 */
public class OrderItem {
    private String iid,oid,bid;
    private int count;
    private double subtotal;

    private String bname,author,image;
    private double price;

    public OrderItem() {
    }

    public OrderItem(String iid, String oid, String bid, int count, double subtotal) {
        this.iid = iid;
        this.oid = oid;
        this.bid = bid;
        this.count = count;
        this.subtotal = subtotal;
    }

    public OrderItem(int count, double subtotal, String bname, String author, String image, double price) {
        this.count = count;
        this.subtotal = subtotal;
        this.bname = bname;
        this.author = author;
        this.image = image;
        this.price = price;
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

package com.lanou.bookstore.cart.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 17/12/23.
 */
public class Cart {
    private Map<String,CartItem> cartMap;

    public Cart() {
    }

    public Cart(Map<String, CartItem> cartMap) {
        this.cartMap = cartMap;
    }

    public Map<String, CartItem> getCartMap() {
        return cartMap;
    }

    public void setCartMap(Map<String, CartItem> cartMap) {
        this.cartMap = cartMap;
    }

    //清空购物车
    public void clearCart() {
//        this.cartMap = new HashMap<>();
        this.cartMap.clear();
    }

    //删除购物车中指定条目
    public void deleteFormCart(String bid){
        this.cartMap.remove(bid);
    }

}

package com.lanou.bookstore.order.service;

import com.lanou.bookstore.cart.domain.Cart;
import com.lanou.bookstore.cart.domain.CartItem;
import com.lanou.bookstore.cart.domain.NewCart;
import com.lanou.bookstore.order.dao.OrderDao;
import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.domain.OrderItem;
import com.lanou.commons.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/12/23.
 */
public class OrderService {
    private OrderDao orderDao = new OrderDao();

    public boolean add(Order order, List<NewCart> cartList) {
        boolean result = false;
        //添加订单
        boolean addResult = orderDao.addOrder(order);
        //如果订单添加成功
        if (addResult) {
            //添加该订单中所有条目
            List<OrderItem> orderItemList = new ArrayList<>();
            for (NewCart cart : cartList) {
                String iid = CommonUtils.uuid();
                String oid = order.getOid();
                String bid = cart.getBid();
                int count = cart.getCount();
                double subtotal = cart.getPrice() * cart.getCount();
                OrderItem orderItem = new OrderItem(iid,oid,bid,count,subtotal);
                orderItemList.add(orderItem);
            }
//            Map<String, CartItem> cartItemMap = cart.getCartMap();
//            for (CartItem cartItem : cartItemMap.values()) {
//                String iid = CommonUtils.uuid();
//                String oid = order.getOid();
//                String bid = cartItem.getBookBean().getBid();
//                int count = cartItem.getCount();
//                double subtotal = cartItem.getBookBean().getPrice() * cartItem.getCount();
//                OrderItem orderItem = new OrderItem(iid,oid,bid,count,subtotal);
//                orderItemList.add(orderItem);
//
//            }
            result = orderDao.addOrderItemList(orderItemList);
        }

        return result;
    }

    public List<Order> myOrders(String uid){
        return orderDao.myOrders(uid);
    }

//    public List<OrderItem> myOrderItems(String oid){
//        return orderDao.myOrderItems(oid);
//    }

    public Order loadOrder(String oid) {
        return orderDao.loadOrder(oid);
    }

    public boolean payment(String address, String oid){
        return orderDao.payment(address,oid);
    }

    public boolean confirm(String oid){
        return orderDao.confirm(oid);
    }

    public List<Order> queryMyOrdersByState(int state) {
        return orderDao.queryMyOrdersByState(state);
    }

    public boolean sendGoods(String oid) {
        return orderDao.sendGoods(oid);
    }

}

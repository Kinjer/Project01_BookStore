package com.lanou.bookstore.order.dao;

import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.domain.OrderItem;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/12/23.
 */
public class OrderDao {
    private GxQueryRunner runner = new GxQueryRunner();

    public boolean addOrder(Order order) {
        String sql = " insert into orders values(?,?,?,?,?,?) ";
        int flag = 0;
        try {
            flag = runner.update(sql
                    , order.getOid()
                    , order.getOrdertime()
                    , order.getPrice()
                    , order.getState()
                    , order.getUid()
                    , order.getAddress());
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

    public boolean addOrderItemList(List<OrderItem> orderItemList) {
//        int flag = 0;
        boolean result = true;
        String sql = " insert into orderitem values(?,?,?,?,?) ";
        for (OrderItem orderItem : orderItemList) {
            try {
                runner.update(sql
                        , orderItem.getIid()
                        , orderItem.getCount()
                        , orderItem.getSubtotal()
                        , orderItem.getOid()
                        , orderItem.getBid());
            } catch (SQLException e) {
                e.printStackTrace();
                result = false;
            }
        }
        return result;
    }

    public List<Order> queryMyOrdersByState(int state) {
        List<Order> orderList = new ArrayList<>();
        StringBuffer sql = new StringBuffer(" select * from orders ");
        try {
            if (state != 9999) {
                sql.append(" where state = ? ORDER BY ordertime ");
                orderList = runner.query(sql.toString(), new BeanListHandler<Order>(Order.class), state);
            }else {
                sql.append(" ORDER BY ordertime ");
                orderList = runner.query(sql.toString(), new BeanListHandler<Order>(Order.class));
            }


            if (orderList.size() > 0) {
                //循环遍历每个订单,为每个订单添加自己的所有条目
                for (Order order : orderList) {
                    List<OrderItem> orderItemList = myOrderItems(order.getOid());
                    order.setOrderItemList(orderItemList);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;

    }

    public List<Order> myOrders(String uid) {
        List<Order> orderList = new ArrayList<>();
        String sql = " select * from orders where uid = ? ORDER BY ordertime DESC";

        try {
            orderList = runner.query(sql, new BeanListHandler<Order>(Order.class), uid);

            if (orderList.size() > 0) {
                //循环遍历每个订单,为每个订单添加自己的所有条目
                for (Order order : orderList) {
                    List<OrderItem> orderItemList = myOrderItems(order.getOid());
                    order.setOrderItemList(orderItemList);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;

    }

    public List<OrderItem> myOrderItems(String oid) {
        List<OrderItem> orderItemList = new ArrayList<>();
        String sql = " select oi.oid,oi.COUNT,oi.subtotal,oi.bid,b.bname,b.price,b.author,b.image from orderitem oi LEFT JOIN book b on oi.bid = b.bid where oi.oid = ? ";

        try {
            orderItemList = runner.query(sql, new BeanListHandler<OrderItem>(OrderItem.class), oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItemList;

    }

    public Order loadOrder(String oid) {
        Order order = new Order();
        String sql = " select * from orders where oid = ? ";

        try {
            order = runner.query(sql, new BeanHandler<Order>(Order.class), oid);
            List<OrderItem> orderItemList = myOrderItems(order.getOid());
            order.setOrderItemList(orderItemList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    public boolean payment(String address, String oid) {
        String sql = " update orders set address = ?, state = 1 where oid = ? ";
        int flag = 0;
        try {
            flag = runner.update(sql, address, oid);
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

    public boolean confirm(String oid) {
        String sql = " update orders set state = 3 where oid = ? ";
        int flag = 0;
        try {
            flag = runner.update(sql, oid);
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

    public boolean sendGoods(String oid) {
        String sql = " update orders set state = 2 where oid = ? ";
        int flag = 0;
        try {
            flag = runner.update(sql, oid);
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

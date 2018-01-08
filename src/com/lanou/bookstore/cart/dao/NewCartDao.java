package com.lanou.bookstore.cart.dao;

import com.lanou.bookstore.cart.domain.Cart;
import com.lanou.bookstore.cart.domain.NewCart;
import com.lanou.bookstore.user.domain.UserBean;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/12/26.
 */
public class NewCartDao {
    private GxQueryRunner runner = new GxQueryRunner();

    public String findUidByUsername(String username) {
        String uid = "";
        String sql = " select uid from USER where username = ? ";

        try {
            uid = runner.query(sql, new BeanHandler<UserBean>(UserBean.class), username).getUid();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uid;
    }

    public boolean addCart(NewCart cart) {
        String sql = " insert into cart values(?,?,?,?,?,?,?,?,?) ";
        int count = 0;
        try {
            count = runner.update(sql
                    , cart.getCarid()
                    , cart.getBname()
                    , cart.getPrice()
                    , cart.getAuthor()
                    , cart.getImage()
                    , cart.getCount()
                    , cart.getUid()
                    , cart.getFlag()
                    , cart.getBid());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        }

    }

    public List<NewCart> getCartList(String uid) {
        List<NewCart> cartList = new ArrayList<>();
        String sql = " select * from cart where uid = ? and flag = 1 ";
        try {
            cartList = runner.query(sql, new BeanListHandler<NewCart>(NewCart.class), uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartList;
    }

    public boolean clear(String uid) {
        String sql = " update cart set flag = 0 where uid = ? ";
        int count = 0;
        try {
            count = runner.update(sql, uid);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean deleteOne(String carid) {
        String sql = " update cart set flag = 0 where carid = ? ";
        int count = 0;
        try {
            count = runner.update(sql, carid);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public NewCart verifyBookFromCart(String uid, String bname) {
        String sql = " select * from cart where uid = ? and bname = ? and flag = 1 ";
        NewCart cart = new NewCart();
        try {
            cart = runner.query(sql, new BeanHandler<NewCart>(NewCart.class), uid, bname);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }

    public boolean updateCount(int count, String uid, String bname) {
        String sql = " update cart set count = ? where bname = ? and uid = ? and flag = 1 ";
        int flag = 0;
        try {
            flag = runner.update(sql, count, bname, uid);
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

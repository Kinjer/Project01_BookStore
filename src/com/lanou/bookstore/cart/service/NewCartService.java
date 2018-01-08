package com.lanou.bookstore.cart.service;

import com.lanou.bookstore.book.domain.BookBean;
import com.lanou.bookstore.cart.dao.NewCartDao;
import com.lanou.bookstore.cart.domain.NewCart;
import com.lanou.commons.CommonUtils;

import java.util.List;

/**
 * Created by dllo on 17/12/26.
 */
public class NewCartService {
    private NewCartDao dao = new NewCartDao();

    public String findUidByUsername(String username){
        return dao.findUidByUsername(username);
    }

    public boolean addCart(NewCart cart) {
        return dao.addCart(cart);
    }

    public List<NewCart> getCartList(String uid) {
        return dao.getCartList(uid);
    }

    public boolean clear(String uid){
        return dao.clear(uid);
    }
    public boolean deleteOne(String carid){
        return dao.deleteOne(carid);
    }

    public void verifyBookFromCart(String uid, String bname, int count, BookBean book) {
        NewCart verifyResult = dao.verifyBookFromCart(uid, bname);
        if (verifyResult != null) { //返回值不为null, 添加的数在cart表中已存在, 更新count
            int finalCount = count + verifyResult.getCount();
            updateCount(finalCount, uid, book.getBname());
        } else { //返回值为null, 插入cart表
            NewCart cart = new NewCart();
            cart.setCarid(CommonUtils.uuid());
            cart.setBname(book.getBname());
            cart.setPrice(book.getPrice());
            cart.setAuthor(book.getAuthor());
            cart.setImage(book.getImage());
            cart.setCount(count);
            cart.setUid(uid);
            cart.setFlag(1); //1代表该条数据没有从购物车中删除, 0代表删除
            cart.setBid(book.getBid());
            addCart(cart);
        }
    }

    public boolean updateCount(int count, String uid, String bname){
        return dao.updateCount(count,uid,bname);
    }

}

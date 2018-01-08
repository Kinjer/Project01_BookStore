package com.lanou.bookstore.category.dao;

import com.lanou.bookstore.category.domain.CategoryBean;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/12/25.
 */
public class CategoryDao {
    private GxQueryRunner runner = new GxQueryRunner();

    public List<CategoryBean> getBookCategory(){
        String sql = " select * from category ";
        List<CategoryBean> categoryBean = new ArrayList<>();
        try {
            categoryBean = runner.query(sql, new BeanListHandler<CategoryBean>(CategoryBean.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryBean;
    }

    public List<CategoryBean> showCategoryList(){
        String sql = " select * from category ";
        List<CategoryBean> categoryBeanList = new ArrayList<>();
        try {
            categoryBeanList = runner.query(sql, new BeanListHandler<CategoryBean>(CategoryBean.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryBeanList;
    }

    public boolean addCategory(CategoryBean categoryBean){
        int flag = 0;
        String sql = " insert into category values(?,?) ";
        try {
            flag = runner.update(sql,categoryBean.getCid(),categoryBean.getCname());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (flag>0){
                return true;
            }else {
                return false;
            }
        }

    }

    public CategoryBean queryByCid(String cid){
        CategoryBean categoryBean = new CategoryBean();
        String sql = " select * from category where cid = ? ";
        try {
            categoryBean = runner.query(sql, new BeanHandler<CategoryBean>(CategoryBean.class),cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryBean;
    }

    public boolean editCategory(CategoryBean bean){
        int flag = 0;
        String sql = " update category set cname = ? where cid = ? ";
        try {
            flag = runner.update(sql,bean.getCname(),bean.getCid());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (flag>0){
                return true;
            }else {
                return false;
            }
        }
    }

    public boolean delCategory(String cid){
        int flag = 0;
        String sql = " delete from category where cid = ? ";
        try {
            flag = runner.update(sql,cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (flag>0){
                return true;
            }else {
                return false;
            }
        }
    }
}

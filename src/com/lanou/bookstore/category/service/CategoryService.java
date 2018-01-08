package com.lanou.bookstore.category.service;

import com.lanou.bookstore.category.dao.CategoryDao;
import com.lanou.bookstore.category.domain.CategoryBean;
import com.lanou.commons.CommonUtils;

import java.util.List;

/**
 * Created by dllo on 17/12/25.
 */
public class CategoryService {
    private CategoryDao dao = new CategoryDao();

    public List<CategoryBean> getBookCategory(){
        return dao.getBookCategory();
    }

    public List<CategoryBean> showCategoryList(){
        return dao.showCategoryList();
    }

    public boolean addCategory(CategoryBean categoryBean){
        categoryBean.setCid(CommonUtils.uuid());
        return dao.addCategory(categoryBean);
    }

    public CategoryBean queryByCid(String cid){
        return dao.queryByCid(cid);
    }
    public boolean editCategory(CategoryBean bean){
        return dao.editCategory(bean);
    }
    public boolean delCategory(String cid){
        return dao.delCategory(cid);
    }
}

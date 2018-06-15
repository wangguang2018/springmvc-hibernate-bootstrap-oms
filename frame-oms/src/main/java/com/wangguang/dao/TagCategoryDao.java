package com.wangguang.dao;


import com.wangguang.model.BaseDao;
import com.wangguang.model.entity.Tag;
import com.wangguang.model.entity.TagCategory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagCategoryDao extends BaseDao<TagCategory,Integer> {

    @Query("select c from TagCategory c where c.flag=1")
    List<TagCategory> findAll();

}

package com.iguangtech.api.service;

import com.wangguang.model.entity.Product;
import com.wangguang.model.repositories.ProductDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Service - 娃娃
 */
@Service
public class ProductService {

    @Resource
    private ProductDao productDao;


    /**
     * 查询娃娃
     *
     * @param productId 编号
     * @return 娃娃
     */
    @Transactional(readOnly = true)
    public Product findById(Integer productId) {
        return productDao.findById(productId);
    }
}

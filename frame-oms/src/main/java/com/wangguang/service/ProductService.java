package com.wangguang.service;


import com.wangguang.model.BaseDao;
import com.wangguang.model.entity.Product;
import com.wangguang.model.enums.EnumFlag;
import com.wangguang.model.repositories.ProductDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductService extends BaseService<Product,Integer> {

    private ProductDao productDao;

    @Override
    @Resource
    public void setBaseDao(BaseDao<Product, Integer> baseDao) {
        this.baseDao = baseDao;
        productDao = (ProductDao) this.baseDao;
    }

    /**
     * 获取所有有效的娃娃
     * @return
     */
    public List<Product> findAllProducts(){
        return productDao.findAllByFlag(EnumFlag.Normal.value);
    }

    public List<Product> findAllProductsByAgentId(Integer agentId){
        return productDao.findAllByFlagAndAgentId(EnumFlag.Normal.value,agentId);
    }

    /**
     * 获取各种类型的所有产品
     * @param agentId
     * @return
     */
    public List<Product> findProductsByAgentIdAndType(Integer agentId,byte type){
        return productDao.findAllByFlagAndAgentIdAndType(EnumFlag.Normal.value,agentId,type);
    }



    @Transactional
    public void deleteProduct(Integer[] ids) {
        for(int id : ids){
            productDao.updateProductFlag((byte)-1,id);
        }
    }
}

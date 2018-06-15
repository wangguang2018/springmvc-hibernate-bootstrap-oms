package com.wangguang.service;


import com.wangguang.dao.TagDao;
import com.wangguang.dao.TagMachineDao;
import com.wangguang.model.BaseDao;
import com.wangguang.model.entity.Tag;
import com.wangguang.model.entity.TagMachine;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class TagService extends BaseService<Tag,Integer> {

    private TagDao tagDao;

    @Resource
    private TagMachineDao tagMachineDao;

    @Override
    @Resource
    public void setBaseDao(BaseDao<Tag, Integer> baseDao) {
        this.baseDao = baseDao;
        tagDao = (TagDao) this.baseDao;
    }


    @Transactional
    public void saveTag(Tag tag, String machineIds){

        tagDao.save(tag);

        if(StringUtils.isNotBlank(machineIds)) {
            String[] machineIdsArr = machineIds.split(",", 0);
            if(machineIdsArr != null && machineIdsArr.length > 0) {
                //先删除以前的数据，在添加数据
                if(tag.getId()!=null){
                    tagMachineDao.deleteByTag(tag.getId());
                }
                for (String machineId : machineIdsArr) {
                    TagMachine tagMachine = new TagMachine();
                    tagMachine.setMachineId(Integer.valueOf(machineId));
                    tagMachine.setTagId(tag.getId());
                    tagMachine.setCreateTime(new Date());
                    tagMachineDao.save(tagMachine);
                }
            }
        }else{
            tagMachineDao.deleteByTag(tag.getId());
        }
    }

    @Transactional
    public void deleteIds(Integer[] ids){
        for(int id : ids){
            //先删除关系数据
            tagMachineDao.deleteByTag(id);
            tagDao.delete(id);
        }
    }

}

package com.wangguang.service;


import com.wangguang.dao.ActivityDao;
import com.wangguang.entity.Activity;
import com.wangguang.model.BaseDao;
import com.wangguang.model.enums.ActivityEnum;
import com.wangguang.model.enums.EnumFlag;
import com.wangguang.model.repositories.JpaDao;
import com.wangguang.services.CommonService;
import com.wangguang.web.JsonMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ActivityService extends BaseService<Activity, Integer> {

    private ActivityDao activityDao;


    @Resource
    private CommonService commonService;



    @Resource
    private JpaDao jpaDao;

    @Override
    @Resource
    public void setBaseDao(BaseDao<Activity, Integer> baseDao) {
        this.activityDao = (ActivityDao) baseDao;
        this.baseDao = baseDao;
    }

    private Activity translate(Activity old,Activity activity){
        activity.setId(old.getId());
        activity.setCreateTime(old.getCreateTime());
        activity.setFlag(old.getFlag());
        activity.setType(old.getType());
        return  activity;
    }

    @Transactional
    public synchronized JsonMap saveActivity(Activity activity){
        Date now = commonService.getCurrentTime();
        if(activity.getId()!=null && activity.getId()>0){
            Activity old = activityDao.findOne(activity.getId());
            this.translate(old,activity);
        }else{
            activity.setCreateTime(now);
            activity.setType(ActivityEnum.WUFU.value);
            activity.setFlag(EnumFlag.Normal.value);
        }
        activity.setUpdateTime(now);
        activityDao.save(activity);
        return new JsonMap(0,"保存成功");
    }



}

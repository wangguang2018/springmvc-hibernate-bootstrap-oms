package com.wangguang.service;


import com.wangguang.dao.SignOptionDao;
import com.wangguang.entity.SignOption;
import com.wangguang.model.BaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SignOptionService extends BaseService<SignOption,Integer> {


    private SignOptionDao signOptionDao;

    @Override
    @Resource
    public void setBaseDao(BaseDao<SignOption, Integer> baseDao) {
        this.signOptionDao = (SignOptionDao) baseDao;
        this.baseDao = baseDao;
    }

    public List<SignOption> findAll(){
        return signOptionDao.findAll();
    }

    public List<SignOption> findAllByAgentId(Integer agentId){
        return signOptionDao.findByAgentId(agentId);
    }

    public void save(List<SignOption> signOptions,Integer agentId){
        for(SignOption option : signOptions){
            SignOption o = signOptionDao.findOne(option.getId());
            if(agentId != null)
                o.setAgentId(agentId);
            if(o != null){
                o.setPoints(option.getPoints());
                signOptionDao.save(o);
            }
        }
    }
}

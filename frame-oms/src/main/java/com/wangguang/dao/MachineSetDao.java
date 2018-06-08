package com.wangguang.dao;


import com.wangguang.model.BaseDao;
import com.wangguang.model.entity.MachineSet;

public interface MachineSetDao extends BaseDao<MachineSet,Integer> {

    MachineSet findBymachineId(Integer machineId);
}

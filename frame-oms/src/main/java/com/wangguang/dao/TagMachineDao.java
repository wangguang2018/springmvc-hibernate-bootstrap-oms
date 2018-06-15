package com.wangguang.dao;


import com.wangguang.model.BaseDao;
import com.wangguang.model.entity.TagMachine;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagMachineDao extends BaseDao<TagMachine,Integer> {


    @Modifying
    @Query("delete TagMachine m where m.tagId=?1")
    void deleteByTag(Integer id);

    @Query(value = "select m.sn from dm_tag_machine tm left join dm_machine m on tm.machine_id = m.id where tm.tag_id=?1",nativeQuery = true)
    List<String> getMachineSn(Integer id);

}

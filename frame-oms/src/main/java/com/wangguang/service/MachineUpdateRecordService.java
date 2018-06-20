package com.wangguang.service;

import com.wangguang.dao.MachineUpdateRecordDao;
import com.wangguang.model.BaseDao;
import com.wangguang.model.entity.MachineUpdateRecord;
import com.wangguang.model.enums.EnumFlag;
import com.wangguang.services.CommonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class MachineUpdateRecordService extends BaseService<MachineUpdateRecord, Integer> {

    private MachineUpdateRecordDao machineUpdateRecordDao;

    @Resource
    private AccountService accountService;

    @Resource
    private CommonService commonService;

    @Override
    @Resource
    public void setBaseDao(BaseDao<MachineUpdateRecord, Integer> baseDao) {
        this.machineUpdateRecordDao = (MachineUpdateRecordDao) baseDao;
        this.baseDao = baseDao;
    }


    /**
     * 保存
     *
     * @param record
     */
    @Transactional
    public void save(MachineUpdateRecord record, String machineIds) {
        if(StringUtils.isNotBlank(machineIds)) {
            String[] machineIdsArr = machineIds.split(",", 0);
            Date now = commonService.getCurrentTime();
            if (machineIdsArr != null && machineIdsArr.length > 0) {
                for(int a=0;a<machineIdsArr.length;a++){
                    MachineUpdateRecord rec = new MachineUpdateRecord();
                    rec.setId(null);
                    rec.setContent(record.getContent());
                    rec.setTitle(record.getTitle());
                    rec.setUpdateUrl(record.getUpdateUrl());
                    rec.setVersionCode(record.getVersionCode());
                    rec.setMachineId(Integer.valueOf(machineIdsArr[a]));
                    rec.setFlag(EnumFlag.Normal.value);
                    rec.setCreateTime(now);
                    rec.setUpdateTime(now);
                    machineUpdateRecordDao.save(rec);
                }
            }
        }
    }




    private String generateUUIDKey() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }



}

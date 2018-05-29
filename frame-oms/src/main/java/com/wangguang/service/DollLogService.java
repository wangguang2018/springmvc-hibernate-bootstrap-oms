package com.wangguang.service;


import com.google.common.collect.Lists;
import com.wangguang.dao.DollLogDao;
import com.wangguang.entity.DollLog;
import com.wangguang.model.BaseDao;
import com.wangguang.model.enums.EnumStatisticsSearchType;
import com.wangguang.model.repositories.JpaDao;
import com.wangguang.web.JsonMap;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class DollLogService extends BaseService<DollLog,Integer> {

    private DollLogDao dollLogDao;

    @PersistenceContext
    private EntityManager entityManager;


    @Resource
    private JpaDao jpaDao;


    @Override
    @Resource
    public void setBaseDao(BaseDao<DollLog, Integer> baseDao) {
        dollLogDao = (DollLogDao) baseDao;
        this.baseDao = baseDao;
    }



    /**
     * 未抓取和抓取统计
     */
    @Transactional
    public JsonMap catchTotal(Integer searchType, Integer agentId, Date startDate, Date endDate){

        String sql = "";


        JsonMap map = new JsonMap();

        if(endDate!=null && startDate!=null && startDate.getTime()>endDate.getTime()){
            map.put("code",2);
            return map;
        }
        String startSqlPart = " ";
        String endSqlPart = " ";

        if(startDate != null){
            startSqlPart += " and d.create_time >= ?2";
        }
        if(endDate != null){
            endSqlPart += " and d.create_time <= ?3";
        }

        if(EnumStatisticsSearchType.Diamond.value==searchType.intValue()){
            sql = " SELECT mt.id,IFNULL(dd.psum,0),mt.sn  from (SELECT m.id,m.sn from dm_machine m  where   m.agent_id = ?1 and  m.flag = 1 ) mt " +
                    "LEFT JOIN (SELECT sum(d.product_price) psum,d.machine_id maid from dm_doll_log d "+
                    " where d.agent_id = ?1 and d.flag=1 "+startSqlPart+endSqlPart+" GROUP BY d.machine_id ) dd " +
                    "on mt.id=dd.maid ";
        }else if(EnumStatisticsSearchType.Catch.value==searchType.intValue()){
            sql = "SELECT dd1.id maid,dd1.tt1 fail,dd2.tt2 success,dd1.sn from " +
                    "(SELECT mt.id,IFNULL(dd.totalOne,0) tt1,mt.sn from " +
                    "(SELECT m.id,m.sn from dm_machine m  where   m.agent_id = ?1 and  m.flag = 1 ) mt  LEFT JOIN " +
                    "(SELECT COUNT(d.id) totalOne ,d.machine_id idOne  from dm_doll_log d where d.agent_id=?1 and d.`status`=1 and d.flag=1 "+startSqlPart+endSqlPart+" GROUP BY d.machine_id ) dd " +
                    "on mt.id=dd.idOne) dd1 LEFT JOIN " +
                    "(SELECT mt.id,IFNULL(dd.totalOne,0)  tt2,mt.sn from " +
                    "(SELECT m.id,m.sn from dm_machine m  where   m.agent_id = ?1 and  m.flag = 1 ) mt LEFT JOIN " +
                    "(SELECT COUNT(d.id) totalOne ,d.machine_id idOne  from dm_doll_log d where d.agent_id=?1 and d.`status`=0 and d.flag=1 "+startSqlPart+endSqlPart+" GROUP BY d.machine_id ) dd " +
                    "on mt.id=dd.idOne) dd2 ON dd1.id = dd2.id " ;
        }

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1,agentId);
        if(startDate != null){
            query.setParameter(2,startDate);
        }
        if(endDate != null){
            query.setParameter(3,endDate);
        }
        List<Object> catchs = query.getResultList();
        List<String> sns = Lists.newArrayList();
        List<BigInteger> failRecord = Lists.newArrayList();
        List<BigInteger> successRecord = Lists.newArrayList();
        List<BigDecimal> prices = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(catchs)){
            if(EnumStatisticsSearchType.Diamond.value==searchType.intValue()){
                for(Object obj : catchs){
                    Object[] objs = (Object[]) obj;
                    BigDecimal pri = (BigDecimal)objs[1];
                    String sn = objs[2]==null?"":objs[2].toString();
                    prices.add(pri);
                    sns.add(sn);
                }

            }else if(EnumStatisticsSearchType.Catch.value==searchType.intValue()){
                for(Object obj : catchs){
                    Object[] objs = (Object[]) obj;
                    BigInteger fail = (BigInteger)objs[1];
                    BigInteger success = (BigInteger)objs[2];
                    String sn = objs[2]==null?"":objs[3].toString();
                    failRecord.add(fail);
                    successRecord.add(success);
                    sns.add(sn);
                }
            }

        }
        map.put("sns",sns);
        map.put("failRecord",failRecord);
        map.put("successRecord",successRecord);
        map.put("prices",prices);
        return  map;
    }


    /**
     *
     * @param searchType
     * @param agentId
     * @param startDate
     * @param endDate
     * @param pageNum
     * @return
     */
    public Page<Map> page(Integer searchType,Integer agentId, Date startDate, Date endDate, Integer pageNum) {

        String startSqlPart = " ";
        String endSqlPart = " ";

        if(startDate != null){
            startSqlPart += " and UNIX_TIMESTAMP(d.create_time) >= "+startDate.getTime()/1000;
        }
        if(endDate != null){
            endSqlPart += " and UNIX_TIMESTAMP(d.create_time) <= "+endDate.getTime()/1000;
        }


        String sql = "SELECT ww1.id,ww1.sn,ww1.`name`, ww1.tot1, ww5.fail,ww5.success, ww5.success/(ww5.fail+ww5.success) sucRate from   " +
                "(SELECT ww6.id,IFNULL(ww4.psum,0) tot1,ww6.sn, p.`name` from   " +
                "(SELECT m.id,m.sn,m.product_id from dm_machine m  where   m.agent_id = "+agentId+" and  m.flag = 1 ) ww6   " +
                " LEFT JOIN dm_product p on p.id=ww6.product_id  LEFT JOIN (SELECT sum(d.product_price) psum,d.machine_id maid from dm_doll_log d where d.agent_id = "+agentId+"  and d.flag=1    "+startSqlPart+endSqlPart+"  " +
                "GROUP BY d.machine_id ) ww4 on ww6.id=ww4.maid ) ww1 LEFT JOIN (SELECT ww2.id maid,ww2.tt1 fail,ww3.tt2 success,ww2.sn from    " +
                "(SELECT ww6.id,IFNULL(ww4.totalOne,0) tt1,ww6.sn from (SELECT m.id,m.sn from dm_machine m  where   m.agent_id = "+agentId+" and  m.flag = 1 ) ww6   " +
                "LEFT JOIN  (SELECT COUNT(d.id) totalOne ,d.machine_id ww7  from dm_doll_log d where d.agent_id= "+agentId+" and d.`status`=1 and d.flag=1   "+startSqlPart+endSqlPart+"  " +
                " GROUP BY d.machine_id ) ww4  ON ww6.id = ww4.ww7 ) ww2 LEFT JOIN    " +
                "(SELECT ww6.id,IFNULL(ww4.totalOne,0)  tt2,ww6.sn from  (SELECT m.id,m.sn from dm_machine m    " +
                "where   m.agent_id = "+agentId+"  and  m.flag = 1 ) ww6  LEFT JOIN   " +
                "(SELECT COUNT(d.id) totalOne ,d.machine_id ww7  from dm_doll_log d where d.agent_id= "+agentId+" and d.`status`=0 and d.flag=1  "+startSqlPart+endSqlPart+"    " +
                " GROUP BY d.machine_id ) ww4  ON ww6.id = ww4.ww7 ) ww3 ON ww2.id = ww3.id) ww5 ON ww1.id = ww5.maid ";





        return jpaDao.queryAsPageTwo(pageNum, sql, Map.class);
    }


}

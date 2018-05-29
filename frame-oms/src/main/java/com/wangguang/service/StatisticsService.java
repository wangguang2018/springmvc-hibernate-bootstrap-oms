package com.wangguang.service;


import com.wangguang.dao.ChargeLogDao;
import com.wangguang.dao.MachineDao;
import com.wangguang.dao.member.MemberDao;
import com.wangguang.services.CommonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class StatisticsService {
    @Resource
    private MemberDao memberDao;
    @Resource
    private ChargeLogDao chargeLogDao;
    @Resource
    private MachineDao machineDao;
    @Resource
    private CommonService commonService;


    /**
     * 今日新增用户
     *
     * @return
     */
    public Integer todayNewMember(Integer agentId) {
        Date today = commonService.getCurrentDate();
        Long count = null;
        if (agentId == null)
            count = memberDao.countByRegisterTime(today);
        else
            count = memberDao.countByRegisterTime(today, agentId);
        return count == null ? 0 : count.intValue();
    }

    /**
     * 总用户
     *
     * @param agentId
     * @return
     */
    public Integer totalMember(Integer agentId) {
        Long count = null;
        if (agentId == null)
            count = memberDao.countMember();
        else
            count = memberDao.countMember(agentId);
        return count == null ? 0 : count.intValue();
    }

    /**
     * 总机器数量
     *
     * @param agentId
     * @return
     */
    public Integer totalMachine(Integer agentId) {
        Long count = null;
        if (agentId == null)
            count = machineDao.countMachine();
        else
            count = machineDao.countMachine(agentId);
        return count == null ? 0 : count.intValue();
    }

    /**
     * 总充值金额
     *
     * @param agentId
     * @return
     */
    public BigDecimal totalChargeMoney(Integer agentId) {
        BigDecimal price = null;
        if (agentId == null)
            price = chargeLogDao.sumPrice();
        else
            price = chargeLogDao.sumPrice(agentId);
        return price == null ? new BigDecimal(0) : price;
    }


    /**
     * 月充值记录
     *
     * @param agentId
     * @return
     */
    public List<BigDecimal> monthlyCharge(Integer agentId) {
        List<BigDecimal> logs = new ArrayList<>();
        Calendar today = Calendar.getInstance();
        today.setTime(commonService.getCurrentDate());
        int maxDay = today.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= maxDay; i++) {
            today.set(Calendar.DAY_OF_MONTH,i);
            BigDecimal price = null;
            if(agentId == null){
                price = chargeLogDao.sumPriceByDate(today.getTime());
            }else{
                price = chargeLogDao.sumPriceByDate(today.getTime(),agentId);
            }
            logs.add(price == null ? new BigDecimal(0) : price);
        }
        return logs;
    }


    /**
     * 代理商各个机器月充值记录
     *
     * @param agentId
     * @return
     */
    public List<BigDecimal> monthCharge(Integer agentId) {
        List<BigDecimal> logs = new ArrayList<>();
        Calendar today = Calendar.getInstance();
        today.setTime(commonService.getCurrentDate());
        int maxDay = today.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= maxDay; i++) {
            today.set(Calendar.DAY_OF_MONTH,i);
            BigDecimal price = null;
            if(agentId == null){
                price = chargeLogDao.sumPriceByDate(today.getTime());
            }else{
                price = chargeLogDao.sumPriceByDate(today.getTime(),agentId);
            }
            logs.add(price == null ? new BigDecimal(0) : price);
        }
        return logs;
    }

    /**
     * 代理商各个机器月充值记录
     *
     * @param agentId
     * @return
     */
    public BigDecimal agentMonthCharge(Integer agentId) {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.DAY_OF_MONTH,1);
        today.set(Calendar.HOUR_OF_DAY,0);
        today.set(Calendar.MINUTE,0);
        today.set(Calendar.SECOND,0);
        Date firstDate = today.getTime();
        return  chargeLogDao.agentSumPriceByDate(firstDate,agentId);
    }


    /**
     * 代理商各个机器年充值记录
     *
     * @param agentId
     * @return
     */
    public List<BigDecimal> yearCharge(Integer agentId) {
        List<BigDecimal> logs = new ArrayList<>();
        Calendar today = Calendar.getInstance();
        today.setTime(commonService.getCurrentDate());
        int maxDay = today.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= maxDay; i++) {
            today.set(Calendar.DAY_OF_MONTH,i);
            BigDecimal price = null;
            if(agentId == null){
                price = chargeLogDao.sumPriceByDate(today.getTime());
            }else{
                price = chargeLogDao.sumPriceByDate(today.getTime(),agentId);
            }
            logs.add(price == null ? new BigDecimal(0) : price);
        }
        return logs;
    }
}

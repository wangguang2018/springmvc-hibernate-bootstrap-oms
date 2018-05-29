package com.wangguang.service;


import com.wangguang.common.bo.Constant;
import com.wangguang.dao.AgentDao;
import com.wangguang.dao.UserRepository;
import com.wangguang.entity.Agent;
import com.wangguang.entity.SignOption;
import com.wangguang.enums.EnumFlag;
import com.wangguang.model.BaseDao;
import com.wangguang.model.enums.EnumAgentType;
import com.wangguang.model.enums.EnumDefault;
import com.wangguang.model.enums.EnumGender;
import com.wangguang.model.repositories.JpaDao;
import com.wangguang.model.repositories.base.SystemParamDao;
import com.wangguang.model.sys.Param;
import com.wangguang.model.sys.Role;
import com.wangguang.model.sys.User;
import com.wangguang.services.CommonService;
import com.wangguang.services.ExceptionCode;
import com.wangguang.web.JsonMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AgentService extends BaseService<Agent, Integer> {

    private AgentDao agentDao;

    @Resource
    private AccountService accountService;

    @Resource
    private RoleService roleService;

    @Resource
    private CommonService commonService;

    @Resource
    private SignOptionService signOptionService;

    @Resource
    private SystemParamDao systemParamDao;

    @Resource
    private UserRepository userDao;

    @Resource
    private JpaDao jpaDao;

    @Override
    @Resource
    public void setBaseDao(BaseDao<Agent, Integer> baseDao) {
        this.agentDao = (AgentDao) baseDao;
        this.baseDao = baseDao;
    }


    /**
     * 保存代理商
     *
     * @param agent
     */
    @Transactional
    public JsonMap saveAgent(Agent agent, String account) {

        synchronized (this){
            Agent oldAgent = null;

            if(agent.getId() != null && agent.getId()>0){
                oldAgent = agentDao.findOne(agent.getId());
            }

            //校验邮件，手机号，后台帐号的唯一性
            User accountUser = userDao.findByAccount(account.trim());
            if((accountUser!=null && (agent.getId()==null || agent.getId()<0)) ||
                    (accountUser!=null && oldAgent!=null && oldAgent.getAdminId()!=accountUser.getId())){
                return new JsonMap(ExceptionCode.ACCOUNT_EXISTED);
            }
            User userForPhone = userDao.findByMobile(agent.getPhone().trim());
            if(userForPhone!=null && (agent.getId()==null || agent.getId()<0) ||
                    (userForPhone!=null && oldAgent!=null && oldAgent.getAdminId()!=accountUser.getId()) ){
                return new JsonMap(ExceptionCode.EXISTS_MEMBER_ACCOUNT);
            }
            if(StringUtils.isNotEmpty(agent.getMail())){
                User userForEmail = userDao.findByEmail(agent.getMail().trim());
                if((userForEmail != null && (agent.getId()==null || agent.getId()<0)) ||
                        (userForEmail!=null && oldAgent!=null && oldAgent.getAdminId()!=accountUser.getId())){
                    return new JsonMap(ExceptionCode.EMAIL_EXISTED);
                }
            }


        boolean flag = agent.getId() == null;
        if (agent.getId() == null) {
            agent.setKey(generateUUIDKey());
            User user = createAgentAdminUser(account, agent);
            agent.setAdminId(user.getId());
            agent.setStatus(EnumFlag.Normal.value);
            agent.setCreateTime(commonService.getCurrentTime());
        } else {
            Agent dbAgent = agentDao.findOne(agent.getId());
            agent.setKey(dbAgent.getKey());
            agent.setStatus(dbAgent.getStatus());
            agent.setAdminId(dbAgent.getAdminId());
            agent.setUpdateTime(commonService.getCurrentTime());
        }
        agentDao.save(agent);
        if(flag&&agent.getPid().intValue()==0){
            createSignDefaultOption(agent.getId());
            createSysparam(agent.getId());
        }
        return new JsonMap(0,"保存成功");

        }

    }

    /**
     * 生成代理商默认的签到配置
     * @param agentId
     */
    private void createSignDefaultOption(Integer agentId) {
        for (int i = 1;i <= 7;i++) {
            SignOption option = new SignOption();
            option.setDayId(i);
            option.setPoints(0);
            option.setDesc("第"+i+"天");
            option.setAgentId(agentId);
            option.setCreateTime(commonService.getCurrentTime());
            signOptionService.save(option);
        }
    }


    /**
     * 生成代理商默认的基础参数配置
     * @param agentId
     */
    private void createSysparam(Integer agentId) {
        List<Param> paramList = systemParamDao.getinit();
        for(Param param : paramList){
            param.setAgentId(agentId);
            systemParamDao.save(param);
        }

    }


    private User createAgentAdminUser(String account, Agent agent) {
        User entity = new User();
        entity.setAccount(account);
        entity.setFullname(agent.getName());
        entity.setNickname(agent.getContact());
        entity.setPassword("123456");
        entity.setEmail(agent.getMail()==null?"":agent.getMail());
        entity.setGender(EnumGender.Unknown.getValue());
        entity.setPhone(agent.getPhone());
        Role role = null;
        if(EnumAgentType.daili.getValue()==agent.getType().intValue()){
            role = roleService.findRole(2);//代理商角色
        }else if(EnumAgentType.qudao.getValue()== agent.getType().intValue()){
            role = roleService.findRole(3);//渠道商角色
        }
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        entity.setRoleList(roles);
        accountService.save(entity);
        return entity;
    }

    private String generateUUIDKey() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    @Transactional
    public void disable(Integer[] ids) {
        for (Integer id : ids) {
            agentDao.updateStatus(EnumDefault.No.getStatus(), id);
            accountService.updateStatus(agentDao.findOne(id).getAdminId(), Constant.Status.DISABLE);
        }
    }

    @Transactional
    public void enable(Integer[] ids) {
        for (Integer id : ids) {
            agentDao.updateStatus(EnumDefault.Yes.getStatus(), id);
            accountService.updateStatus(agentDao.findOne(id).getAdminId(), Constant.Status.Enable);
        }
    }

    public List<Agent> findAll(){
        return agentDao.findAll();
    }

    public Agent findByKey(String key) {
        return agentDao.findByKey(key);
    }

    public Agent findByAdminId(Integer adminId) {
        return agentDao.findByAdminId(adminId);
    }

    public Agent findById(Integer agentId){
        return agentDao.findOne(agentId);
    }


}

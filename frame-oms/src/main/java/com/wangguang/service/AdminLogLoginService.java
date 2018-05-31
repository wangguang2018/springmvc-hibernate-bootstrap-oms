package com.wangguang.service;


import com.wangguang.model.BaseDao;
import com.wangguang.model.log.AdminLogLogin;
import com.wangguang.model.repositories.log.AdminLogLoginDao;
import com.wangguang.model.sys.User;
import com.wangguang.services.CommonService;
import com.wangguang.web.common.exception.WebRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 *
 *  登录记录管理 service
 */

@Service
public class AdminLogLoginService extends BaseService<AdminLogLogin,Integer> {

    private AdminLogLoginDao adminLogLoginDao;

    @Resource
    private CommonService commonService;


    @Resource
    @Override
    public void setBaseDao(BaseDao<AdminLogLogin, Integer> baseDao) {
        this.baseDao = baseDao;
        adminLogLoginDao =( AdminLogLoginDao) baseDao;
    }


    /**
     * 查询登录
     */
    @Transactional(readOnly = true)
    public AdminLogLogin findLogLogin(Integer id) {
        if (id == null) {
            return null;
        }
        return adminLogLoginDao.findOne(id);
    }


    /**
     *  保存 记录
     * @param request
     * @param user
     */
    @Transactional
    public void saveLogLogin(HttpServletRequest request,User user) {

        if (null == user) {
            throw new WebRequestException("不存在的管理员");
        }
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        ip = ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
        AdminLogLogin adminLogLogin = new AdminLogLogin();
        adminLogLogin.setIp(ip);
        adminLogLogin.setCreateTime(commonService.getCurrentTime());
        adminLogLogin.setUserId(user.getId());
        adminLogLoginDao.save(adminLogLogin);
    }


}

package com.wangguang.service;


import com.wangguang.model.BaseDao;
import com.wangguang.model.repositories.base.SystemParamDao;
import com.wangguang.model.sys.Param;
import com.wangguang.services.CommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Service - 系统参数
 */
@Service
public class ParamService extends BaseService<Param, Integer> {

	private SystemParamDao systemParamDao;

	@Resource
	private CommonService commonService;

	@Resource
	@Override
	public void setBaseDao(BaseDao<Param, Integer> baseDao) {
		this.baseDao = baseDao;
		systemParamDao = (SystemParamDao)baseDao;
	}

	/**
	 * 查询系统参数信息
	 * 
	 * @param id
	 *            系统参数编号
	 * @return 系统参数信息
	 */
	@Transactional(readOnly = true)
	public Param findParam(Integer id) {
		if (id == null) {
			return null;
		}
		return systemParamDao.findOne(id);
	}

	/**
	 * 查询参数值
	 *
	 * @param key 参数
	 * @return 值
	 */
	@Transactional(readOnly = true)
	public String findByKey(String key,Integer agentId) {
		Param param = systemParamDao.findSysParamByKeyAndAgentId(key,agentId);

		if (param != null) {
			return param.getValue();
		}

		return "";
	}

	/**
	 * 保存系统参数
	 * 
	 * @param param
	 *            系统参数
	 * @return 系统参数
	 */
	@Override
	@Transactional
	public Param save(Param param) {
		return systemParamDao.save(param);
	}

	/**
	 * 删除参数
	 */
	@Transactional
	public void delete(Integer[] ids){
		Param param;
		for (Integer id:ids){
			param=systemParamDao.findOne(id);
			if (param==null){
				continue;
			}
			systemParamDao.delete(id);
		}
	}


	@Transactional(readOnly = true)
	public Param findParamByKey(String key,Integer agentId){
		Param param = systemParamDao.findSysParamByKeyAndAgentId(key,agentId);
		if (param != null) {
			return param;
		}
		return null;
	}
}

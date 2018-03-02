package com.wangguang.service.impl;

import com.wangguang.entity.User;
import com.wangguang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service - 商家信息
 *
 * @author peakren
 * @since 2017-10-29
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	public void say(){
		System.out.println("say.........");
	}

}


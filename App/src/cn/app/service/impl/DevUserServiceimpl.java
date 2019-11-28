package cn.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.app.dao.DevUserDao;
import cn.app.pojo.DevUser;
import cn.app.service.DevUserService;

@Service
public class DevUserServiceimpl implements DevUserService{

	@Resource
	private DevUserDao devUserDao;
	@Override
	public DevUser login(String devCode, String devPassword) {
		return devUserDao.getByDevCodeAndDevPassword(devCode, devPassword);
	}

}

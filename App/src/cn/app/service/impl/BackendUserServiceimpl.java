package cn.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.app.dao.BackendUserDao;
import cn.app.pojo.BackendUser;
import cn.app.service.BackendUserService;
@Service
public class BackendUserServiceimpl implements BackendUserService{

	@Resource
	private BackendUserDao backendUserDao;
	@Override
	public BackendUser login(String userCode, String userPassword) {
		return backendUserDao.getByuserCodeAnduserPassword(userCode, userPassword);
	}

}

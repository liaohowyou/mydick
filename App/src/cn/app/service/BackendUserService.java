package cn.app.service;

import cn.app.pojo.BackendUser;

public interface BackendUserService {
	//根据用户名和密码获得管理者信息
	public BackendUser login(String userCode,String userPassword);

}

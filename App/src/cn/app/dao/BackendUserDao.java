package cn.app.dao;

import org.apache.ibatis.annotations.Param;

import cn.app.pojo.BackendUser;

public interface BackendUserDao {
	  //根据用户名和密码获得管理者信息
	public BackendUser getByuserCodeAnduserPassword(@Param("userCode")String userCode,@Param("userPassword")String userPassword);
}

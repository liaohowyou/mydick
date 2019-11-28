package cn.app.dao;

import org.apache.ibatis.annotations.Param;

import cn.app.pojo.DevUser;

public interface DevUserDao {
     //根据用户名和密码获得开发者信息
	public DevUser getByDevCodeAndDevPassword(@Param("devCode")String devCode,@Param("devPassword")String devPassword);
}

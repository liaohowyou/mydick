package cn.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.app.pojo.AppVersion;

public interface AppVersionDao {
	   //查询历史版本信息
	   public List<AppVersion> getby(Integer id);
	   //新增版本信息
	   public Integer versionAdd(AppVersion appVersion);
	   //获得ID
	   public Integer getByid(Integer appId);
	   //查询版本信息
	   public AppVersion getUpdateid(Integer id);
	   //修改版本信息
	   public Integer updateall(AppVersion appVersion);
	   //后台查询历史版本信息
	   public AppVersion getidall(Integer id);
}

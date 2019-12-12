package cn.app.service;

import java.util.List;

import cn.app.pojo.AppVersion;

public interface AppVersionService {

	public List<AppVersion> getby(Integer id);

	public Integer versionAdd(AppVersion appVersion);

	public Integer getByid(Integer appId);

	// 查询修改信息
	public AppVersion getUpdateid(Integer id);

	// 修改版本信息
	public Integer updateall(AppVersion appVersion);
	
	//后台查询历史版本信息
	   public AppVersion getidall(Integer id);
}



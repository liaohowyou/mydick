package cn.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.app.dao.AppVersionDao;
import cn.app.pojo.AppVersion;
import cn.app.service.AppVersionService;

@Service
public class AppVersionServiceimpl implements AppVersionService{

	@Resource
	private AppVersionDao appVersionDao;
	@Override
	public List<AppVersion> getby(Integer id) {
		return appVersionDao.getby(id);
	}
	@Override
	public Integer versionAdd(AppVersion appVersion) {
		return appVersionDao.versionAdd(appVersion);
	}
	@Override
	public Integer getByid(Integer appId) {
		return appVersionDao.getByid(appId);
	}
	@Override
	public AppVersion getUpdateid(Integer id) {
		return appVersionDao.getUpdateid(id);
	}
	@Override
	public Integer updateall(AppVersion appVersion) {
		return appVersionDao.updateall(appVersion);
	}
	@Override
	public AppVersion getidall(Integer id) {
		return appVersionDao.getidall(id);
	}

}

package cn.app.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.app.dao.AppInfoDao;
import cn.app.pojo.AppInfo;
import cn.app.pojo.AppVersion;
import cn.app.pojo.Page;
import cn.app.service.AppInfoService;

@Service
public class AppInfoServiceimpl implements AppInfoService {

	@Resource
	private AppInfoDao appinfoDao;

	@Override
	public void getPage(String softwareName, Integer status, Integer flatformId, Integer categoryLevel1,
			Integer categoryLevel2, Integer categoryLevel3, Integer devId, Page page) {
		page.setList(appinfoDao.getAppinfoList(softwareName, status, flatformId, categoryLevel1, categoryLevel2,
				categoryLevel3, devId, page));
		page.setRows(appinfoDao.getappinfocount());
	}

	@Override
	public Integer appinfoAdd(String softwareName, String APKName, String supportROM, String interfaceLanguage,
			Integer softwareSize, Integer downloads, Integer status, Integer categoryLevel1, Integer categoryLevel2,
			Integer categoryLevel3, Integer devId, String appInfo, String logoPicPath, String logoLocPath) {
		return appinfoDao.appinfoAdd(softwareName, APKName, supportROM, interfaceLanguage, softwareSize, downloads,
				status, categoryLevel1, categoryLevel2, categoryLevel3, devId, appInfo, logoPicPath, logoLocPath);
	}

	@Override
	public AppInfo getAppInfo(String APKName) {
		return appinfoDao.getAppInfo(APKName);
	}

	@Override
	public Integer appinfoUpdate(AppInfo appInfo) {
		return appinfoDao.appinfoUpdate(appInfo);
	}

	@Override
	public AppInfo getIdAll(Integer appId) {
		return appinfoDao.getIdAll(appId);
	}

	@Override
	public Integer update_id(AppVersion appVersion) {
		return appinfoDao.update_id(appVersion);
	}

	@Override
	public Integer deleteall(Integer id) {
		return appinfoDao.deleteall(id);
	}

	@Override
	public Integer updatestatuc(Integer id,Integer status) {
		return appinfoDao.updatestatuc(id,status);
	}

	@Override
	public boolean appsysUpdatestatus(Integer id, Integer status) {
		Integer appinfo = appinfoDao.appsysUpdatestatus(id, status);
		if (appinfo > 0) {
			return true;
		}
		    return false;

	}

	@Override
	public Integer delfileimg(Integer id) {
		return appinfoDao.delfileimg(id);
	}

	@Override
	public AppInfo getall(Integer id, Integer status) {
		return appinfoDao.getall(id, status);
	}

	

}

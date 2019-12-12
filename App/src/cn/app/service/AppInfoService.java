package cn.app.service;

import org.apache.ibatis.annotations.Param;

import cn.app.pojo.AppInfo;
import cn.app.pojo.AppVersion;
import cn.app.pojo.Page;

public interface AppInfoService {

	public void getPage(String softwareName, Integer status, Integer flatformId, Integer categoryLevel1,
			Integer categoryLevel2, Integer categoryLevel3, Integer devId, Page page);

	// 新增
	public Integer appinfoAdd(String softwareName, String APKName, String supportROM, String interfaceLanguage,
			Integer softwareSize, Integer downloads, Integer status, Integer categoryLevel1, Integer categoryLevel2,
			Integer categoryLevel3, Integer devId, String appInfo, String logoPicPath, String logoLocPath);

	public AppInfo getAppInfo(String APKName);

	// 修改
	public Integer appinfoUpdate(AppInfo appInfo);

	// 根据ID得到信息
	public AppInfo getIdAll(Integer id);

	public Integer update_id(AppVersion appVersion);

	// 删除APP应用信息
	public Integer deleteall(Integer id);
	
	//查看审核信息
	   public AppInfo getall(Integer id,Integer status);

	// 修改状态信息
	public Integer updatestatuc(Integer id, Integer status);

	// 上架，下架获取ID和状态ID
	public boolean appsysUpdatestatus(Integer id, Integer status);

	// 修改删除图片img
	public Integer delfileimg(Integer id);
}

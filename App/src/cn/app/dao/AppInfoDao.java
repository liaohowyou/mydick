package cn.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.app.pojo.AppInfo;
import cn.app.pojo.AppVersion;
import cn.app.pojo.Page;

public interface AppInfoDao {
   //显示信息
   public List<AppInfo> getAppinfoList(@Param("softwareName")String softwareName,
		   @Param("status")Integer status,
		   @Param("flatformId")Integer flatformId,
		   @Param("categoryLevel1")Integer categoryLevel1,
		   @Param("categoryLevel2")Integer categoryLevel2,
		   @Param("categoryLevel3")Integer categoryLevel3,
		   @Param("devId")Integer devId,
		   @Param("page")Page page);
   //获得count总数
   public Integer getappinfocount();
   
   //判断apkName是否有数据
   public AppInfo getAppInfo(String APKName);
   
    //新增信息
   public Integer appinfoAdd(@Param("softwareName")String softwareName,
		   @Param("APKName")String APKName,
		   @Param("supportROM")String supportROM,
		   @Param("interfaceLanguage")String interfaceLanguage,
		   @Param("softwareSize")Integer softwareSize,
		   @Param("downloads")Integer downloads,
		   @Param("flatformId")Integer flatformId,
		   @Param("categoryLevel1")Integer categoryLevel1,
		   @Param("categoryLevel2")Integer categoryLevel2,
		   @Param("categoryLevel3")Integer categoryLevel3,
		   @Param("status")Integer status,
		   @Param("appInfo")String appInfo,
		   @Param("logoPicPath")String logoPicPath,
		   @Param("logoLocPath")String logoLocPath);
   
   //修改信息
   public Integer appinfoUpdate(AppInfo appInfo);
   //根据ID得到信息
   public AppInfo getIdAll(Integer id);
   
   //修改ID
   public Integer update_id(AppVersion appVersion);
   
   //删除APP应用信息
   public Integer deleteall(Integer id);
   
   //查看审核信息
   public AppInfo getall(@Param("id")Integer id,@Param("status")Integer status);
   
   //修改审核信息
   public Integer updatestatuc(@Param("id")Integer id,@Param("status")Integer status);
   
   //上架，下架获取ID和状态ID
   public Integer appsysUpdatestatus(@Param("id")Integer id,@Param("status")Integer status);
   
	// 修改删除图片img
	public Integer delfileimg(Integer id);
}

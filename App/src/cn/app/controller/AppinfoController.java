package cn.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.chainsaw.Main;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

import cn.app.pojo.AppCategory;
import cn.app.pojo.AppInfo;
import cn.app.pojo.AppVersion;
import cn.app.pojo.DataDictionary;
import cn.app.pojo.Page;
import cn.app.service.AppCategoryService;
import cn.app.service.AppInfoService;
import cn.app.service.AppVersionService;
import cn.app.service.DataDictionaryService;
import cn.app.util.Tool;

@Controller
@RequestMapping("/appinfo")
public class AppinfoController {
	@Resource
	private AppCategoryService appCategoryService;
	@Resource
	private AppInfoService appinfoService;
	@Resource
	private DataDictionaryService dataDictionaryService;
	@Resource
	private AppVersionService appVersionService;

	
	// 跳转去到APP应用管理的APP维护页面
	@RequestMapping("/beforelist")
	public String beforelist(Model model) {
		Integer pageNo = 1;
		Page page = new Page();
		page.setPageNo(pageNo);
		appinfoService.getPage(null, null, null, null, null, null, null, page);
		model.addAttribute("page", page);
		// 显示一级分类
		List<AppCategory> AppCategorylist = appCategoryService.getByparentId(null);
		model.addAttribute("categoryLevel1List", AppCategorylist);
		// 显示App状态
		List<DataDictionary> dataDictionaryList = dataDictionaryService.getByvalueName("APP_STATUS");
		model.addAttribute("statusList", dataDictionaryList);
		// 显示所属平台
		List<DataDictionary> flatFormList = dataDictionaryService.getByvalueName2("APP_FLATFORM");
		model.addAttribute("flatFormList", flatFormList);
		return "developer/appinfolist";
	}

	// 根据分类查找二级，三级分类
	@RequestMapping("/categoryLinkage")
	@ResponseBody
	public Object categoryLinkage(String pid) {
		List<AppCategory> oneCategory = appCategoryService.getByparentId(pid == "" ? null : Integer.parseInt(pid));
		return oneCategory;
	}

	// 根据条件进行模糊查询
	@RequestMapping("/appinfofuzzy")
	public String appinfofuzzy(@RequestParam(value = "querySoftwareName", required = false) String querySoftwareName,
			@RequestParam(value = "queryStatus", required = false) Integer queryStatus,
			@RequestParam(value = "queryFlatformId", required = false) Integer queryFlatformId,
			@RequestParam(value = "queryCategoryLevel1", required = false) Integer queryCategoryLevel1,
			@RequestParam(value = "queryCategoryLevel2", required = false) Integer queryCategoryLevel2,
			@RequestParam(value = "queryCategoryLevel3", required = false) Integer queryCategoryLevel3,
			@RequestParam(value = "pageIndex", required = false) Integer pageIndex, Model model) {
		if (querySoftwareName == "" || queryStatus == null || queryFlatformId == null || queryCategoryLevel1 == null
				|| queryCategoryLevel2 == null || queryCategoryLevel3 == null) {
			Integer pageNo = pageIndex;
			Page page = new Page();
			page.setPageNo(pageNo);
			appinfoService.getPage(querySoftwareName, queryStatus, queryFlatformId, queryCategoryLevel1,
					queryCategoryLevel2, queryCategoryLevel3, null, page);
			model.addAttribute("page", page);
		}
		// 显示一级分类
		List<AppCategory> AppCategorylist = appCategoryService.getByparentId(null);
		model.addAttribute("categoryLevel1List", AppCategorylist);
		// 显示App状态
		List<DataDictionary> dataDictionaryList = dataDictionaryService.getByvalueName("APP_STATUS");
		model.addAttribute("statusList", dataDictionaryList);
		// 显示所属平台
		List<DataDictionary> flatFormList = dataDictionaryService.getByvalueName2("APP_FLATFORM");
		model.addAttribute("flatFormList", flatFormList);
		return "developer/appinfolist";
	}

	@RequestMapping("/doappinfoaddS")
	@ResponseBody
	public Object doappinfoaddS(String tcode) {
		// 获得所属平台
		List<DataDictionary> list = dataDictionaryService.getByvalueName2(tcode);
		return list;
	}

	@RequestMapping("/categorylevellist")
	@ResponseBody
	public Object categorylevellist(String pid) {
		// 获得一,二级分类
		List<AppCategory> AppCategorylist = appCategoryService.getByparentId(pid == "" ? null : Integer.parseInt(pid));
		return AppCategorylist;
	}

	// 跳转去到开发者的APPinfoadd页面
	@RequestMapping("/doappinfoadd")
	public Object doappinfoadd(Model model) {
		return "developer/appinfoadd";
	}

	// 添加操作
	@RequestMapping("/appinfoaddsave")
	public String doadd(String softwareName, String APKName, String supportROM, String interfaceLanguage,
			Integer softwareSize, Integer downloads, Integer flatformId, Integer categoryLevel1, Integer categoryLevel2,
			Integer categoryLevel3, Integer status, String appInfo, MultipartFile a_logoPicPath, HttpServletRequest req,Model model)
					throws IllegalStateException, IOException {
		// 获取文件名
		String FileName = a_logoPicPath.getOriginalFilename();
		// 本地路径                              
		String clpPath = "F:/Git/mydick/App/WebContent/statics/loadFile/";
		// 获取项目名
		String rootPath = req.getContextPath();
		// 获取服务器路径
		String path = req.getServletContext().getRealPath("/");
		// 获取后缀
		String suffix = FilenameUtils.getExtension(FileName);
		// 拼接新的文件名
		String newFileName = Tool.createUUID() + "." + suffix;
		// 相对路径，用于存放到数据库
		String rePath = rootPath + "/statics/loadFile/" + newFileName;
		// 上传路径，图片在项目本地的路径
		String fullPath = clpPath + newFileName;
		a_logoPicPath.transferTo(new File(fullPath));
		Integer row = appinfoService.appinfoAdd(softwareName, APKName, supportROM, interfaceLanguage, softwareSize,
				downloads, status, categoryLevel1, categoryLevel2, categoryLevel3, status, appInfo, rePath, fullPath);
		return "redirect:/appinfo/beforelist.do";
	}

	// 判断是否有相同的APKName
	@RequestMapping("/apkexist")
	@ResponseBody
	public Object apkNameIsExist(@RequestParam String APKName) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (StringUtils.isNullOrEmpty(APKName)) {
			resultMap.put("APKName", "empty");
		} else {
			AppInfo appInfo = appinfoService.getAppInfo(APKName);
			if (appInfo != null) {
				resultMap.put("APKName", "exist");
			} else {
				resultMap.put("APKName", "noexist");
			}
		}
		return JSONArray.toJSONString(resultMap);
	}

	// 跳转到appinfomodify修改页面
	@RequestMapping("/appinfomodify")
	public String click(Integer id, Model model) {
		AppInfo appInfo = appinfoService.getIdAll(id);
		model.addAttribute("appInfo", appInfo);
		return "developer/appinfomodify";
	}

	// 显示所属平台
	@RequestMapping("/datadictionarylist")
	@ResponseBody
	public Object appinfoUpdate(String tcode) {
		List<DataDictionary> list = dataDictionaryService.getByvalueName(tcode);
		return list;
	}

	// 显示三级分类
	@RequestMapping("/Updatecategorylevellist")
	@ResponseBody
	public Object Updatecategorylevellist(String pid) {
		List<AppCategory> list = appCategoryService.getByparentId(pid == "" ? null : Integer.parseInt(pid));
		return list;
	}

	// 修改
	@RequestMapping("/appinfoUpdate")
	public String appinfoUpdate(AppInfo appInfo) {
		Integer row = appinfoService.appinfoUpdate(appInfo);
		return "redirect:beforelist.do";
	}

	// 跳转去到查看信息页面
	@RequestMapping("/appview")
	public String appview(Integer id, Model model) {
		AppInfo appInfo = appinfoService.getIdAll(id);
		model.addAttribute("appInfo", appInfo);
		//历史版本信息
		List<AppVersion> appVersionList = appVersionService.getby(id);
		model.addAttribute("appVersionList", appVersionList);
		System.out.println(appVersionList.toString());
		return "developer/appinfoview";
	}

	// 跳转去到新增版本页面
	@RequestMapping("/appversionadd")
	public String appversionadd(Integer appId, Model model) {
		List<AppVersion> appVersionList = appVersionService.getby(appId);
		model.addAttribute("appVersionList", appVersionList);
		System.out.println(appVersionList.toString());
		model.addAttribute("appId", appId);
		return "developer/appversionadd";
	}

	// 新增版本信息
	@RequestMapping("/addversionsave")
	public String addversionsave(Integer appId, AppVersion appVersion, HttpServletRequest req,
			MultipartFile a_downloadLink, Model model) throws IllegalStateException, IOException {
		// 文件后缀
		String back = FilenameUtils.getExtension(a_downloadLink.getOriginalFilename());
		// 拼接新的文件名
		String newFileName = Tool.createUUID() + "." + back;
		// 获得网站运行的根目录
		String Path = req.getServletContext().getRealPath("/");
		// 完整路径
		String rootPath = "statics\\loadFile\\" + newFileName;
		String fullPath = Path + rootPath;
		a_downloadLink.transferTo(new File(fullPath));
		// 相对路径
		String url = req.getContextPath() + "statics\\loadFile\\";

		Integer row = appVersionService.versionAdd(appVersion);
		Integer id = appVersionService.getByid(appVersion.getAppId());
		appVersion.setId(id);
		if (row > 0) {
			Integer show = appinfoService.update_id(appVersion);
		}
		return "redirect:/appinfo/beforelist.do";
	}

	// 跳转到修改版本页面
	@RequestMapping("/appversionmodify")
	public String appversionmodify(Integer vid, Model model) {
		System.out.println(vid + "--");
		List<AppVersion> appVersionList = appVersionService.getby(vid);
		model.addAttribute("appVersionList", appVersionList);

		AppVersion appVersion = appVersionService.getUpdateid(vid);
		model.addAttribute("appVersion", appVersion);
		model.addAttribute("id", vid);
		System.out.println("===--=" + vid);
		return "developer/appversionmodify";
	}

	// 修改版本信息
	@RequestMapping("/appversionmodifysave")
	public String appversionmodifysave(AppVersion appVersion, Integer id) {
		System.out.println("id为" + id);
		Integer row = appVersionService.updateall(appVersion);
		if (row > 0) {
			System.out.println("修改成功");
			System.out.println(appVersion.toString());
		} else {
			System.out.println("修改失败");
		}
		return "redirect:/appinfo/beforelist.do";
	}

	// 删除APP应用并删除相关的版本信息
	@RequestMapping("/delapp")
	@ResponseBody
	public Object delapp(Integer id) {
		Integer row = appinfoService.deleteall(id);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		if (row > 0) {
			hashMap.put("delResult", "true");
			System.out.println("删除成功");
		} else {
			hashMap.put("delResult", "false");
			System.out.println("删除失败");
		}
		return JSONArray.toJSONString(hashMap);
	}

	// 上架，下架
	@RequestMapping("{appId}/sale{status}")
	@ResponseBody
	public Object sale(@PathVariable("appId") Integer appId, @PathVariable("status") String status,
			HttpSession session) {
		Integer sta = 0;
		if (status.equals("open"))
			sta = 4;
		else
			sta = 5;
		boolean row = appinfoService.appsysUpdatestatus(appId, sta);
		return row;
	}
	//返回上一级
	@RequestMapping("/click")
	public String click(){
		return "redirect:/appinfo/beforelist.do";
	}
	
	//修改删除图片img
	@RequestMapping("/delfile")
	public String delfile(Integer id){
		Integer row = appinfoService.delfileimg(id);
		if(row > 0){
			System.out.println("删除img成功");
		}else{
			System.out.println("删除img失败");
		}
		return "";
	}
}

package cn.app.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.app.pojo.AppCategory;
import cn.app.pojo.AppInfo;
import cn.app.pojo.AppVersion;
import cn.app.pojo.DataDictionary;
import cn.app.pojo.Page;
import cn.app.service.AppCategoryService;
import cn.app.service.AppInfoService;
import cn.app.service.AppVersionService;
import cn.app.service.DataDictionaryService;

/*
 * 后台APP
 */
@RequestMapping("/back")
@Controller
public class BackinfoController {

	@Resource
	private AppInfoService appInfoService;
	@Resource
	private DataDictionaryService dataDictionaryService;
	@Resource
	private AppCategoryService appCategoryService;
	@Resource
	private AppVersionService appVersionService;

	// 跳转去到后台用户管理页面
	@RequestMapping("/list")
	public String list(Model model) {
		Integer pageNo = 1;// Integer.parseInt(pageIndex);
		Page page = new Page();
		page.setPageNo(pageNo);
		appInfoService.getPage(null, null, null, null, null, null, null, page);
		model.addAttribute("page", page);
		// 显示一级分类
		List<AppCategory> AppCategorylist = appCategoryService.getByparentId(null);
		model.addAttribute("categorylevelS", AppCategorylist);
		// 显示所属平台
		List<DataDictionary> flatFormList = dataDictionaryService.getByvalueName2("APP_FLATFORM");
		model.addAttribute("flatFormList", flatFormList);
		return "backend/applist";
	}

	// 根据条件进行模糊查询
	@RequestMapping("/backappinfo")
	public String appinfofuzzy(@RequestParam(value = "querySoftwareName", required = false) String querySoftwareName,
			@RequestParam(value = "queryStatus", required = false) Integer queryStatus,
			@RequestParam(value = "queryFlatformId", required = false) Integer queryFlatformId,
			@RequestParam(value = "queryCategoryLevel1", required = false) Integer queryCategoryLevel1,
			@RequestParam(value = "queryCategoryLevel2", required = false) Integer queryCategoryLevel2,
			@RequestParam(value = "queryCategoryLevel3", required = false) Integer queryCategoryLevel3,
			@RequestParam(value = "pageIndex", required = false) Integer pageIndex, Model model) {
		if (querySoftwareName == "" || queryStatus == null || queryFlatformId == null || queryCategoryLevel1 == null
				|| queryCategoryLevel2 == null || queryCategoryLevel3 == null) {
			System.out.println();
			Integer pageNo = pageIndex;
			Page page = new Page();
			page.setPageNo(pageNo);
			System.out.println(querySoftwareName);
			appInfoService.getPage(querySoftwareName, queryStatus, queryFlatformId, queryCategoryLevel1,
					queryCategoryLevel2, queryCategoryLevel3, null, page);
			model.addAttribute("page", page);
		}
		// 显示一级分类
		List<AppCategory> AppCategorylist = appCategoryService.getByparentId(null);
		model.addAttribute("categorylevelS", AppCategorylist);
		// 显示所属平台
		List<DataDictionary> flatFormList = dataDictionaryService.getByvalueName2("APP_FLATFORM");
		model.addAttribute("flatFormList", flatFormList);
		return "backend/applist";
	}

	// 根据分类查找二级，三级分类
	@RequestMapping("/categoryLinkage")
	@ResponseBody
	public Object categoryLinkage(String pid) {
		System.out.println("----");
		List<AppCategory> list = appCategoryService.getByparentId(pid == "" ? null : Integer.parseInt(pid));
		System.out.println(list);
		return list;
	}

	// 跳转去到审核页面
	@RequestMapping("/check")
	public String check(Integer aid, Model model) {
		AppInfo appInfo = appInfoService.getIdAll(aid);
		model.addAttribute("appInfo", appInfo);
		// 显示所属平台
		List<DataDictionary> flatFormList = dataDictionaryService.getByvalueName2("APP_FLATFORM");
		model.addAttribute("flatFormList", flatFormList);
		// 显示历史版本信息
		AppVersion appVersion = appVersionService.getidall(aid);
		model.addAttribute("appVersion", appVersion);
		return "backend/appcheck";
	}

	// 审核通过和审核未通过
	@RequestMapping("/checksave")
	public String checksave(Integer id, Integer status) {
		Integer row = appInfoService.updatestatuc(id, status);
		return "redirect:/back/list.do";
	}
}

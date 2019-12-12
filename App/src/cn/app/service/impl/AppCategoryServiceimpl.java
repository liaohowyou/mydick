package cn.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.app.dao.AppCategoryDao;
import cn.app.pojo.AppCategory;
import cn.app.service.AppCategoryService;

@Service
public class AppCategoryServiceimpl implements AppCategoryService{

	@Resource
	private AppCategoryDao appCategoryDao;
	
	@Override
	public List<AppCategory> getByparentId(Integer parentId) {
		return appCategoryDao.getByparentId(parentId);
	}


}

package cn.app.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.app.pojo.AppCategory;

public interface AppCategoryService {

	 //根据父极ID找到子级ID
	public List<AppCategory> getByparentId(Integer parentId);
}

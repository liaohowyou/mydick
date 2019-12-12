package cn.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.app.pojo.AppCategory;

public interface AppCategoryDao {
    //根据父极ID找到子级ID
	public List<AppCategory> getByparentId(@Param("parentId")Integer parentId);
}

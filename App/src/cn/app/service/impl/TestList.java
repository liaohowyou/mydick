package cn.app.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import cn.app.dao.AppVersionDao;
import cn.app.pojo.AppVersion;


public class TestList {
	@Resource
	private AppVersionDao appVersionDao;
	@Test
	public void test() {

		try {
			Integer list=appVersionDao.getByid(58);
//			for (AppVersion appVersion : list) {
//				System.out.println(appVersion.getId());
//			}
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

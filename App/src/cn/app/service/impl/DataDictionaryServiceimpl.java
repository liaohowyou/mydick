package cn.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.app.dao.DataDictionaryDao;
import cn.app.pojo.DataDictionary;
import cn.app.service.DataDictionaryService;
@Service
public class DataDictionaryServiceimpl implements DataDictionaryService{

	@Resource
	private DataDictionaryDao dataDictionaryDao;
	@Override
	public List<DataDictionary> getByvalueName(String typeCode) {
		return dataDictionaryDao.getByvalueName(typeCode);
	}
	@Override
	public List<DataDictionary> getByvalueName2(String typeCode) {
		return dataDictionaryDao.getByvalueName(typeCode);
	}
	
 
}

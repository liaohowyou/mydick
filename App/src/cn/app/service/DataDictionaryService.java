package cn.app.service;

import java.util.List;

import cn.app.pojo.DataDictionary;

public interface DataDictionaryService {
    public List<DataDictionary> getByvalueName(String typeCode);

  //查询所属平台的信息
    public List<DataDictionary> getByvalueName2(String typeCode);
}

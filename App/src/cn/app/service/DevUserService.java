package cn.app.service;

import cn.app.pojo.DevUser;

public interface DevUserService {
  //开发者登录验证方法
  public DevUser login(String devCode,String devPassword);
}

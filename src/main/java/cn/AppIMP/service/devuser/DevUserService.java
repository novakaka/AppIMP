package cn.AppIMP.service.devuser;

import cn.AppIMP.pojo.DevUser;

public interface DevUserService {
    public DevUser Login(String devCode, String password);
}

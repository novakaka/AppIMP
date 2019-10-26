package cn.AppIMP.mapper.devuser;

import cn.AppIMP.pojo.DevUser;
import org.apache.ibatis.annotations.Param;
/*
* 根据devCode 获取用户记录
* */
public interface DevUserMapper {
	public DevUser getLoginUser(@Param("devCode") String devCode);
}

package cn.AppIMP.service.devuser;

import cn.AppIMP.mapper.devuser.DevUserMapper;
import cn.AppIMP.pojo.DevUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class DevUserImpl implements DevUserService {
    @Resource
    private DevUserMapper mapper;
    @Override
    public DevUser Login(String devCode, String password) {

        DevUser devUser = null;
       devUser= mapper.getLoginUser(devCode);
       if(devUser !=null){
           if(!devUser.getDevPassword().equals(password)){
               devUser =null;
           }
       }
        return devUser;
    }
}
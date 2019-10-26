package cn.AppIMP.controller.develop;

import cn.AppIMP.pojo.DevUser;
import cn.AppIMP.service.devuser.DevUserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.xml.registry.infomodel.User;


@Controller
@RequestMapping("/dev")
@SessionAttributes(value = {"error"},types = {User.class})
public class DevLoginController {
    @Resource
    private DevUserService devUserService;
    private Logger logger = Logger.getLogger(String.valueOf(DevLoginController.class));

    @RequestMapping("/login.do")
    public  String login(){
        logger.debug("logger.debug");
       return "devLogin";
    }

    @RequestMapping("/loginVerify.do")
    public  String loginVerify(DevUser user, HttpSession session){
        logger.debug("loginVerify======");
        DevUser user1 = devUserService.Login(user.getDevCode(),user.getDevPassword());
        if(user1!=null){
            session.setAttribute("user",user1);
            return "redirect:/dev/security.do";
        }else {
            session.setAttribute("error","用户名或密码不正确");
            return "devLogin";
        }
    }

    @RequestMapping("/security.do")
    public  String security(HttpSession session){

        if(session.getAttribute("user")==null){
            return "redirect:/dev/login.do";
        }else {
            return "developer/main";
        }
    }
    @RequestMapping("/loginOut.do")
    public  String loginOut(HttpSession session,DevUser user){
        logger.debug("loginOut===");
        session.removeAttribute("user");
        return "devLogin";

    }

}

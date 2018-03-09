package cn.edu.buaa.rec.controller;

import cn.edu.buaa.rec.model.SysUser;
import cn.edu.buaa.rec.service.SysUserService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: suruo
 * @Email: csuruo@gmail.com
 * @Date: Created on 上午12:29 2018/01/22
 * @Modified by:
 */

@Controller
@RequestMapping("/")
//@EnableAutoConfiguration
public class DefaultController {

    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    @Qualifier("SysUserService")
    private SysUserService sysUserService;

    /**
     * 加上（@ResponseBody）返回字符串
     *
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String index(Model model) {
        System.out.println("hello");
        return "index.html";
    }

    /**
     * 注册新系统用户
     * 在newSysUser中检测用户名是否重复
     *
     * @param sysUserInfo
     * @return
     */
    @RequestMapping(value = "/logup", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> register(@Valid @RequestBody Map<String, Object> sysUserInfo) {

        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(sysUserInfo);


        SysUser sysUser = new SysUser(jsonObject.getString("UserName"), jsonObject.getString("Phone"), jsonObject.getString("Email"), jsonObject.getString("Password"));

        return sysUserService.newSysUser(sysUser);
    }

    /**
     * 系统用户登录
     *
     * @param sysUserInfo
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(@Valid @RequestBody Map<String, Object> sysUserInfo) {
        Map<String, Object> m = new HashMap<>();
        //    从前端拿来用户输入的账户名和密码
        String name = (String) sysUserInfo.get("UserName");
        String pword = (String) sysUserInfo.get("Password");
        logger.info(name + "-" + pword);
        if (sysUserInfo != null && name != null & pword != null) {
            SysUser ulo = sysUserService.getByName(name);
            System.out.println("---" + ulo);
            if (ulo != null) {
                if (pword.equals(ulo.getPassword())) {
                    m.put("Msg", "Success!");
                    m.put("SysUserID", ulo.getId());
                } else {
                    m.put("Msg", "用户名与密码不符");
                }
            } else {
                m.put("Msg", "不存在该用户 @@");
            }
        } else {
            m.put("Msg", "用户名与密码不符");
        }
        return m;
    }

    /**
     * 预留的系统主页接口
     * 打算写所有系统信息的主页面
     * 暂时没有设计
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String home(Model model) {

        return "index.html";
    }

    /**
     * 验证是否存在同名用户
     *
     * @param sysUserInfo
     * @return
     */
    @RequestMapping(value = "/nameExi", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> nameExi(@Valid @RequestBody Map<String, Object> sysUserInfo) {

        HashMap<String, Object> m = new HashMap<>();
        m.put("Msg", "Success");
        return m;
    }
}

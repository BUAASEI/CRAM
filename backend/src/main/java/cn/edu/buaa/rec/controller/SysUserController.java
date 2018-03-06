package cn.edu.buaa.rec.controller;

import cn.edu.buaa.rec.model.Domain;
import cn.edu.buaa.rec.model.Project;
import cn.edu.buaa.rec.model.SysUser;
import cn.edu.buaa.rec.service.*;
import cn.edu.buaa.rec.service.impl.MailServiceImpl;
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
import java.util.List;
import java.util.Map;


/**
 * @Description:
 * @Author: suruo
 * @Email: csuruo@gmail.com
 * @Date: Created on 上午12:05 2018/01/31
 * @Modified by:
 */

@Controller
@RequestMapping("/sysuser")
public class SysUserController {
    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    //      这个注解是必须的吗？还是只需要get
    @Autowired
    @Qualifier("SysUserService")
    private SysUserService sysUserService;
    @Autowired
    @Qualifier("DomainService")
    private DomainService domainService;
    @Autowired
    @Qualifier("ProjectService")
    private ProjectService projectService;
    @Autowired
    @Qualifier("UserProjectManService")
    private UserProjectManService userProjectManService;
    @Autowired
    @Qualifier("UserProjectRoleService")
    private UserProjectRoleService userProjectRoleService;
    @Autowired
    @Qualifier("MailService")
    private MailServiceImpl mailService;

    //    跳转到个人中心界面，默认显示其参与的项目
    //    还没写完全，这只是scene的，应该还加上项目上方的其它信息：用户名
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> homePage(@Valid @RequestBody Map<String, Object> userInfo) {
        //      返回的是参与的项目的简介界面
        //        添加 user_project表
        System.out.println("已进入 /sysuser/home 接口");
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(userInfo);
        System.out.println("UserId： " + jsonObject.getLong("UserId"));
        Map<String, Object> m = new HashMap<>();
        m.put("Msg", sysUserService.selectById(jsonObject.getLong("UserId")).getName());
        return m;
    }

    //    修改用户信息
    //    目前只根据id进行修改，不能修改用户名
    @RequestMapping(value = "/modinfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modifyInformation(@Valid @RequestBody Map<String, Object> sysUserInfo) {

        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(sysUserInfo);
        Long sysUserId = jsonObject.getLong("UserId");
        String sysUserName = jsonObject.getString("UserName");
        SysUser sysUser = new SysUser(sysUserId, sysUserName, jsonObject.getString("Phone"), jsonObject.getString("Email"), jsonObject.getString("Password"));
        System.out.println(sysUser.getEmail());
        return sysUserService.modSysUserInfo(sysUser);
    }

    /*
        新建领域
        信息包括：1）领域名称; 2）领域描述; 3）创建者id
    */
    @RequestMapping(value = "/credom", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> createDomain(@Valid @RequestBody Map<String, Object> domainInfo) {

        System.out.println(domainInfo);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(domainInfo);
        Domain domain = new Domain(jsonObject.getString("DomainName"), jsonObject.getString("Description"), jsonObject.getLong("CreatorId"));

        if (domain.getName() == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("Msg", "领域名不可为空");
            return error;
        }

        return domainService.newDomain(domain);
    }

    /*
        新建项目
        信息包括：1）项目名称； 2）项目描述； 3）项目所属领域; 4）创建者
    */
    @RequestMapping("/crepro")
    @ResponseBody
    public Map<String, Object> createProject(@Valid @RequestBody Map<String, Object> projectInfo) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(projectInfo);
        Project project = new Project(jsonObject.getString("ProjectName"), jsonObject.getString("Description"),
                jsonObject.getLong("DomainId"), jsonObject.getLong("CreatorId"));

        System.out.println(project.toString());

        return projectService.newProject(project);
    }

    /*
        站内信
        查看所管理的项目的项目管理员和角色申请
    */
    @RequestMapping(value = "/mail", method = RequestMethod.POST)
    @ResponseBody
    public void showResult(@Valid @RequestBody Map<String, Object> userIdMap) {
        String userIdS = (String) userIdMap.get("userId");
        Long userId = Long.parseLong(userIdS);
        Map<String, Object> map = mailService.getProApplyName(userId);
        for (String key : map.keySet())
            System.out.println(key);
        System.out.println(map);
    }

    /*
        查看管理的项目
        传入参数：SysUserId，展示信息包括：1）项目名称； 2）项目简介； 3）所属领域
    */
    @RequestMapping("/proman")
    @ResponseBody
    public List<Map<String, Object>> projectManagedInfo(@Valid @RequestBody Map<String, Object> projectManagedInfo) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(projectManagedInfo);
        Long userId = jsonObject.getLong("UserId");
        return userProjectManService.manProject(userId);
    }

    /*
        查看参与的项目
        展示信息包括：1）项目名称； 2）项目简介；  3）所属领域
    */
    @RequestMapping("/propar")
    @ResponseBody
    public List<Map<String, Object>> projectParticipateInfo(@Valid @RequestBody Map<String, Object> projectParticipateInfo) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(projectParticipateInfo);
        Long userId = jsonObject.getLong("UserId");
        return userProjectRoleService.parProject(userId);
    }

    /*
    *   查看其余项目
    *   展示信息包括：1）项目名称； 2）项目简介；  3）所属领域
    * */
    @RequestMapping("/proall")
    @ResponseBody
    public List<Map<String, Object>> projectAllInfo(Model model) {
        return projectService.allProject();
    }
}

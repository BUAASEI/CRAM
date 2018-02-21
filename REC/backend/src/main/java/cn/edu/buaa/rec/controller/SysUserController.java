package cn.edu.buaa.rec.controller;

import cn.edu.buaa.rec.model.Domain;
import cn.edu.buaa.rec.model.Project;
import cn.edu.buaa.rec.model.SysUser;
import cn.edu.buaa.rec.service.*;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
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

    @RequestMapping("/")
    public String rootPage(Model model) {
        //      暂时不清楚这里应该返回哪个页面
        return null;
    }

    //    修改用户信息
    //    暂时不做
    @RequestMapping("/modinfo")
    @ResponseBody
    public Map<String, Object> modifyInformation(@Valid @RequestBody Map<String, Object> sysUserInfo) {

        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(sysUserInfo);
        Long sysUserId = jsonObject.getLong("SysUserId");
        String sysUserName = jsonObject.getString("SysUserName");
        SysUser sysUser = new SysUser(sysUserId, sysUserName, jsonObject.getString("Phone"), jsonObject.getString("Email"), jsonObject.getString("Password"));
                System.out.println(sysUser.getName());
        return sysUserService.modSysUserInfo(sysUser);

    }

    /*
        新建领域，信息包括：
        1）领域名称; 2）领域描述; 3）创建者id
        DomainName\DomainDescription\CreatorId
    */
//    没调通
    @RequestMapping("/credom")
    @ResponseBody
    public Map<String, Object> createDomain(@Valid @RequestBody Map<String, Object> domainInfo) {

        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(domainInfo);
        Domain domain = new Domain(jsonObject.getString("Name"), jsonObject.getString("Description"), jsonObject.getLong("CreatorId"));

        return domainService.newDomain(domain);
    }

    /*
        新建项目，信息包括：
        1）项目名称； 2）项目描述； 3）项目所属领域; 4）创建者
        ProjectName\ProjectDescription\DomainId\CreatorId
    */

    @RequestMapping("/crepro")
    @ResponseBody
    public Map<String, Object> createProject(@Valid @RequestBody Map<String, Object> projectInfo) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(projectInfo);
        Project project = new Project(jsonObject.getString("ProjectName"), jsonObject.getString("ProjectDescription"), jsonObject.getLong("DomainId"), jsonObject.getLong("CreatorId"));

        return projectService.newProject(project);
    }

    /*
        站内信
        暂时不做
    */

    /*
        查看管理的项目，展示信息包括：
        1）项目名称； 2）项目创建人名称；
    */
    @RequestMapping("/proman")
    @ResponseBody
    public List<Map<String, Object>> projectManagedInfo(@Valid @RequestBody Map<String, Object> projectManagedInfo) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(projectManagedInfo);
        Long sysUserId = jsonObject.getLong("SysUserId");
        return userProjectManService.manProject(sysUserId);
    }

    /*
        查看参与的项目，展示信息包括：
        1）项目名称； 2）项目创建人名称；
    */
    @RequestMapping("/propar")
    @ResponseBody
    public List<Map<String, Object>> projectParticipateInfo(@Valid @RequestBody Map<String, Object> projectParticipateInfo) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(projectParticipateInfo);
        Long sysUserId = jsonObject.getLong("SysUserId");
        return userProjectRoleService.parProject(sysUserId);
    }

    @RequestMapping("/proall")
    @ResponseBody
    public List<Map<String, Object>> projectAllInfo(Model model) {
        return projectService.allProject();
    }
}

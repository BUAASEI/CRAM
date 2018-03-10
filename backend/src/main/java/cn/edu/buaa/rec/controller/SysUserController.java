package cn.edu.buaa.rec.controller;

import cn.edu.buaa.rec.model.Domain;
import cn.edu.buaa.rec.model.Project;
import cn.edu.buaa.rec.model.SysUser;
import cn.edu.buaa.rec.service.*;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
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
//    @Autowired
//    @Qualifier("MailService")
//    private MailService mailService;

    /**
     * 跳转到个人中心界面，本接口只返回用户的名称默认显示其 参与 的项目
     * 这只是项目上方的其它信息：用户名
     * 预加载的时候，还应该加载参与的项目中的接口返回数据【前端ajax实现】
     *
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> homePage(@Valid @RequestBody Map<String, Object> userInfo) {
        // 返回的是参与的项目的简介界面
        // 添加 user_project表
        // 应该使用user_project_man表
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(userInfo);
        Map<String, Object> m = new HashMap<>();
        m.put("Msg", sysUserService.selectById(jsonObject.getLong("UserId")).getName());
        return m;
    }

    /**
     * 修改用户信息
     * 根据用户id修改，且不能修改用户名
     *
     * @param sysUserInfo
     * @return
     */
    @RequestMapping(value = "/modinfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modifyInformation(@Valid @RequestBody Map<String, Object> sysUserInfo) {

        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(sysUserInfo);
        Long sysUserId = jsonObject.getLong("UserId");
        String sysUserName = jsonObject.getString("Name");
        SysUser sysUser = new SysUser(sysUserId, sysUserName, jsonObject.getString("Phone"), jsonObject.getString("Email"), jsonObject.getString("Password"), jsonObject.getString("FamiliarDomain"), jsonObject.getString("ProjectExp"));
        return sysUserService.modSysUserInfo(sysUser);
    }

    /**
     * 新建领域：领域名称
     *
     * @param domainInfo：领域名称、领域描述和创建者id
     * @return
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

    /**
     * 先项目时，预加载数据库中所有领域信息
     *
     * @return List<DomainInfo>
     */
    @RequestMapping(value = "/getdomain", method = RequestMethod.GET)
    @ResponseBody
    public List<Domain> getDomain() {
        return domainService.getDomain();
    }

    /**
     * 存入新建项目的数据信息
     *
     * @param projectInfo
     * @return
     */
    @RequestMapping(value = "/crepro", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> createProject(@Valid @RequestBody Map<String, Object> projectInfo) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(projectInfo);
        Project project = new Project(jsonObject.getString("ProjectName"), jsonObject.getString("Description"),
                jsonObject.getLong("DomainId"), jsonObject.getLong("CreatorId"));
        System.out.println(project.toString());

       return projectService.newProject(project);
    }

    /**
     * 查看管理的项目
     *
     * @param projectManagedInfo：userId
     * @return 项目名称、简介和所属领域
     */
    @RequestMapping(value = "/proman", method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> projectManagedInfo(@Valid @RequestBody Map<String, Object> projectManagedInfo) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(projectManagedInfo);
        Long userId = jsonObject.getLong("UserId");
        return userProjectManService.manProject(userId);
    }

    /**
     * 查看参与的项目
     *
     * @param projectParticipateInfo
     * @return 项目名称、简介和所属领域
     */
    @RequestMapping(value = "/propar", method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> projectParticipateInfo(@Valid @RequestBody Map<String, Object> projectParticipateInfo) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(projectParticipateInfo);
        Long userId = jsonObject.getLong("UserId");
        return userProjectRoleService.parProject(userId);
    }

    /**
     * 查看未参与的项目信息
     *
     * @param projectOtherInfo
     * @return 项目名称、简介和所属领域
     */
    @RequestMapping(value = "/proother", method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> projectOtherInfo(@Valid @RequestBody Map<String, Object> projectOtherInfo) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(projectOtherInfo);
        Long userId = jsonObject.getLong("UserId");
        return projectService.otherProject(userId);
    }

    /**
     * 返回个人信息
     *
     * @param info：userId
     * @return
     */
    @RequestMapping(value = "/getuser", method = RequestMethod.POST)
    @ResponseBody
    public SysUser getUserById(@Valid @RequestBody Map<String, Object> info) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
        Long userId = jsonObject.getLong("userId");
        if (userId == null) {
            return null;
        }
        SysUser user = sysUserService.selectById(userId);
        System.out.println("user:" + user.toString());
        return user;
    }
}

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
    private MailService mailService;


    /*
        跳转到个人中心界面，默认显示其参与的项目
        这只是项目上方的其它信息：用户名
        预加载的时候，还应该加载参与的项目中的接口返回数据【前端ajax实现】
    */
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> homePage(@Valid @RequestBody Map<String, Object> userInfo) {
        //      返回的是参与的项目的简介界面
        //        添加 user_project表
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(userInfo);
        Map<String, Object> m = new HashMap<>();
        m.put("Msg", sysUserService.selectById(jsonObject.getLong("UserId")).getName());
        return m;
    }

    /*
        修改用户信息
        目前只根据id进行修改，不能修改用户名
    */
    @RequestMapping(value = "/modinfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modifyInformation(@Valid @RequestBody Map<String, Object> sysUserInfo) {

        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(sysUserInfo);
        Long sysUserId = jsonObject.getLong("UserId");
        String sysUserName = jsonObject.getString("Name");
        SysUser sysUser = new SysUser(sysUserId, sysUserName, jsonObject.getString("Phone"), jsonObject.getString("Email"), jsonObject.getString("Password"), jsonObject.getString("FamiliarDomain"), jsonObject.getString("ProjectExp"));
//        System.out.println("modinfo"+sysUser.toString());
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
    @RequestMapping(value = "/crepro", method = RequestMethod.POST)
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
        分两部分：这部分的接口为预加载，返回管理的projectId
        查看所管理的项目的项目管理员和角色申请
    */
    @RequestMapping(value = "/mail/manproinfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<Long, String> getManProjectInfo(@Valid @RequestBody Map<String, Object> userIdInfo) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(userIdInfo);
        Long userId = jsonObject.getLong("UserId");

        return sysUserService.getManProjectId(userId);
    }

    //    加载每个项目中的申请：管理员、角色
    @RequestMapping(value = "/mail/applyinfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, List<Map<String, Object>>> getApplication(@Valid @RequestBody Map<String, Object> projectIdInfo) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(projectIdInfo);
        Long projectId = jsonObject.getLong("projectId");

        return sysUserService.getApply(projectId);
    }

    //    根据管理员和角色的审批结果，修改数据库相应记录
    @RequestMapping(value = "/mail/approve")
    @ResponseBody
    public Map<String, Object> updateApproveResult(@Valid @RequestBody Map<String, Object> approveInfo) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(approveInfo);
        Map<String, Object> result = new HashMap<>();

        List<Map<String, Object>> manApprovedResults = (List) jsonObject.get("Man");

        System.out.println("manApprovedResults" + manApprovedResults.toString());
        int manUpdateResult = 0;
        for (Map<String, Object> manApprovedResult : manApprovedResults
                ) {
            //  o是申请中，1是同意，2是拒绝

            //  如果前端传过来的是不带L的，默认转化成Integer
            //  一定要注意这个问题
            Long applyId = Long.parseLong(manApprovedResult.get("applyId").toString());
            Integer isApproved = Integer.parseInt(manApprovedResult.get("isApproved").toString());
            manUpdateResult = userProjectManService.updateByApprove(applyId, isApproved);
        }

        List<Map<String, Object>> roleApprovedResults = (List) jsonObject.get("Role");
        int roleUpdateResult = 0;
        for (Map<String, Object> roleApprovedResult : roleApprovedResults
                ) {
            //        o是申请中，1是同意，2是拒绝
            Long applyId = Long.parseLong(roleApprovedResult.get("applyId").toString());
            Integer isApproved = Integer.parseInt(roleApprovedResult.get("isApproved").toString());
            roleUpdateResult = userProjectRoleService.updateByApprove(applyId, isApproved);
        }

        if (manUpdateResult == 1 && roleUpdateResult == 1) {
            result.put("Msg", "Success");
        } else {
            result.put("Msg", "Error");
        }

        return result;
    }

    /*
        查看管理的项目
        传入参数：SysUserId，展示信息包括：1）项目名称； 2）项目简介； 3）所属领域
    */
    @RequestMapping(value = "/proman", method = RequestMethod.POST)
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
    @RequestMapping(value = "/propar", method = RequestMethod.POST)
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
    @RequestMapping(value = "/proother", method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> projectOtherInfo(@Valid @RequestBody Map<String, Object> projectOtherInfo) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(projectOtherInfo);
        Long userId = jsonObject.getLong("UserId");
        return projectService.otherProject(userId);
    }

    //查看个人信息，用于修改个人信息初始化页面
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

    @RequestMapping(value="/getdomain",method= RequestMethod.POST)
    @ResponseBody
    public List<Domain> getDomain(){
        return domainService.getDomain();
    }
}

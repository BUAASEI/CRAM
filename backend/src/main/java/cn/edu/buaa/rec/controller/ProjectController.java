package cn.edu.buaa.rec.controller;

import cn.edu.buaa.rec.entity.SolutionEntity;
import cn.edu.buaa.rec.model.*;
import cn.edu.buaa.rec.service.*;
import cn.edu.buaa.rec.service.impl.UsecaseServiceImpl;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: suruo
 * @Email: csuruo@gmail.com
 * @Date: Created on 下午8:44 2018/02/06
 * @Modified by:
 */

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    @Qualifier("ProjectService")
    private ProjectService projectService;

    @Autowired
    @Qualifier("UserProjectRoleService")
    private UserProjectRoleService userProjectRoleService;

    @Autowired
    @Qualifier("BusinessRoleDataService")
    private BusinessRoleDataService businessRoleDataService;

    @Autowired
    @Qualifier("UsecaseRoleDataService")
    private UsecaseRoleDataService usecaseRoleDataService;


    @Autowired
    @Qualifier("UserProjectManService")
    private UserProjectManService userProjectManService;

    @Autowired
    @Qualifier("UserProjectService")
    private UserProjectService userProjectService;

    @Autowired
    @Qualifier("SysUserService")
    private SysUserService sysUserService;

    @Autowired
    @Qualifier("DomainService")
    private DomainService domainService;

    @Autowired
    @Qualifier("RoleService")
    private RoleService roleService;

    @Autowired
    @Qualifier("UsecaseRoleService")
    private UsecaseRoleService usecaseRoleService;
    @Autowired
    @Qualifier("UsecaseService")
    private UseCaseService useCaseService;

    @Autowired
    @Qualifier("DataService")
    private DataService dataService;
    /**
     * 查的表不对，不应该用三元表【重新写】
     * 项目中心，暂时默认显示场景,默认所有角色的场景和用例信息
     */
    @RequestMapping("/home")
    @ResponseBody
    public Map<String, Object> ProjectHomePage(@Valid @RequestBody Map<String, Object> info) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
        Long projectId = jsonObject.getLong("ProjectId");
        Long userId = jsonObject.getLong("UserId");
//        List<Long> roleIds = userProjectRoleService.getUserRoleId(projectId, userId);
//        System.out.println("roleId:"+roleIds);
//        List<Long> usecaseIds = usecaseRoleService.getUsecaseIdsByRoleIds(roleIds);
//        System.out.println("usecase"+usecaseIds);
//        List<Map<String,Object>> usecaseForms = useCaseService.getUsecaseForm(usecaseIds);
//        System.out.println(roleIds.toString());
//        if (roleIds == null && roleIds.size() == 0) {
//            return null;
//        }
        int manger = userProjectManService.checkIsManger(projectId,userId);
        System.out.println("manger:"+manger);
        List<Map<String, Object>> usecaseForms = new LinkedList<>();

        if(manger==1) {
            usecaseForms = useCaseService.getUsecaseByprojectId(projectId);
        }
        Map<String, Object> result = new HashMap<>();
//        List<BusinessRoleData> businessRoleData = businessRoleDataService.getBusinessRoleDataByRoleIds(roleIds);
//        List<Map<String, Object>> businessForms = new LinkedList<>();
//        if (businessRoleData != null) {
//            businessForms = businessRoleDataService.getBusinessForm(businessRoleData);
//        }
//        result.put("businessForms", businessForms);
        result.put("usecaseForms", usecaseForms);
        return result;
    }


    /**
     * 项目中心预加载项目基本信息
     */
    @RequestMapping("/basicinfo")
    @ResponseBody
    public Map<String, Object> basicInfo(@Valid @RequestBody Map<String, Object> info) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
        Long projectId = jsonObject.getLong("projectId");
        System.out.println(projectId);
        Project project = projectService.getProjectById(projectId);
        System.out.println(project.toString());
        Long creatorId = project.getCreatorId();
        System.out.println(creatorId);
        String creatorName = sysUserService.getNameById(creatorId);
        Long domainId = project.getDomainId();
        String domainName = domainService.getNameById(domainId);
        Long userId = jsonObject.getLong("userId");
        List<Long> roleIds = userProjectRoleService.getUserRoleId(projectId, userId);
        if (roleIds == null && roleIds.size() == 0) {
            return null;
        }
        List<Map<String, Object>> roles = roleService.getNameAndIdById(roleIds);
        Map<String, Object> result = new HashMap<>();

        result.put("projectId", project.getId());
        result.put("projectName", project.getName());
        result.put("domainId", domainId);
        result.put("domainName", domainName);
        result.put("creatorId", creatorId);
        result.put("creatorName", creatorName);
        result.put("role", roles);
        return result;
    }

    /**
     * 申请角色
     *
     * @param applyRoleInfo
     * @return
     */
    @RequestMapping("/roleapply")
    @ResponseBody
    public Map<String, Object> applyRole(@Valid @RequestBody Map<String, Object> applyRoleInfo) {
        return projectService.applyRole(applyRoleInfo);
    }

    /**
     * 申请管理员
     *
     * @param applyManagerInfo
     * @return
     */
    @RequestMapping("/manapply")
    @ResponseBody
    public Map<String, Object> applyManager(@Valid @RequestBody Map<String, Object> applyManagerInfo) {
        return projectService.applyManager(applyManagerInfo);
    }

    /**
     * 申请加入项目
     */
    @RequestMapping("/join")
    @ResponseBody
    public Map<String, Object> applyProject(@Valid @RequestBody Map<String, Object> applyProjectInfo) {

        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(applyProjectInfo);
        UserProject userProject = new UserProject(1L, jsonObject.getLong("userId"), jsonObject.getLong("projectId"), 0);

        return userProjectService.applyProject(userProject);
    }


    /**
     * 关系改变，重新写
     *
     * 预加载所有场景（业务场景和用例）的基本信息
     * 项目管理员使用这个接口查看所有的场景信息
     */
    @RequestMapping("/scenes")
    @ResponseBody
    public List<Map<String, Object>> showScenes(@Valid @RequestBody String projectName) {
        return projectService.getScenes(projectName);
    }

    /**
     * 显示项目中心的角色项
     */
    @RequestMapping(value = "/role", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> showRole(@Valid @RequestBody Map<String, Object> info) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);

        Long projectId = jsonObject.getLong("projectId");
        Long userId = jsonObject.getLong("userId");
        Map<String, Object> m = new HashMap<String, Object>();

        List<Role> roles = projectService.getRole(projectId);
        List<Long> roleIds = userProjectRoleService.getUserRoleId(projectId, userId);
        List<Role> userRoles = new LinkedList<Role>();
        List<Role> listRoles = new LinkedList<Role>();
        for (Role r : roles) {
            Boolean flag = false;
            for (Long id : roleIds) {
                if (id == r.getId()) {
                    userRoles.add(r);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                listRoles.add(r);
            }
        }
        m.put("listRoles", listRoles);
        System.out.println("listRoles:"+listRoles);
        m.put("userRoles", userRoles);
        System.out.println("userRoles:"+userRoles);
        return m;
    }

    /**
     * 项目中心的数据展示界面
     *
     * @param info
     * @return
     */
    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> showData(@Valid @RequestBody Map<String, Object> info) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
        Long projectId = jsonObject.getLong("projectId");
        Long userId = jsonObject.getLong("userId");
        Map<String, Object> map = new HashMap<>();
        List<Data> datas = projectService.getData(projectId);
        List<Data> listDatas = new LinkedList<>();
        List<Data> userDatas = new LinkedList<>();
        for (Data d : datas) {
            if (d.getCreatorId()!=userId) {
                System.out.println("listd:"+d.toString()+userId);
                listDatas.add(d);
            } else {
                userDatas.add(d);
                System.out.println("userd:"+d.toString());
            }
        }

        map.put("listDatas", listDatas);
        map.put("userDatas", userDatas);
        return map;
    }


    /**
     * 根据角色切换，显示不同的场景和用例信息
     *
     * @param info
     * @return
     */
    @RequestMapping("/changerole")
    @ResponseBody
    public Map<String, Object> changeRole(@Valid @RequestBody Map<String, Object> info) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
        Long roleId = jsonObject.getLong("roleId");
        List<Long> roleIds = new LinkedList<>();
        roleIds.add(roleId);
        Map<String, Object> result = new HashMap<>();
        List<BusinessRoleData> businessRoleData = businessRoleDataService.getBusinessRoleDataByRoleIds(roleIds);
        List<Map<String, Object>> businessForms = new LinkedList<>();
        if (businessRoleData != null) {
            businessForms = businessRoleDataService.getBusinessForm(businessRoleData);
        }
        result.put("businessForms", businessForms);
        List<UsecaseRoleData> usecaseRoleData = usecaseRoleDataService.getUsecaseRoleDataByRoleIds(roleIds);
        List<Map<String, Object>> usecaseForms = new LinkedList<>();
        if (usecaseRoleData != null) {
            usecaseForms = usecaseRoleDataService.getUsecaseForm(usecaseRoleData);
        }
        result.put("usecaseForms", usecaseForms);
        return result;
    }

    @RequestMapping("/getRoleAndData")
    @ResponseBody
    public Map<String,Object> getRoleAndData(@Valid @RequestBody Map<String,Object>info){
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
        Long projectId = jsonObject.getLong("projectId");
        List<Map<String,Object>> roles = roleService.getNameAndIdByProjectId(projectId);

        Map<String,Object> map = new HashMap<>();
        map.put("role",roles);

        List<Map<String,Object>> datas = dataService.getNameAndIdByprojectId(projectId);
        map.put("data",datas);
        return map;
    }

}
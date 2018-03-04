package cn.edu.buaa.rec.controller;

import cn.edu.buaa.rec.model.Project;
import cn.edu.buaa.rec.model.Role;
import cn.edu.buaa.rec.service.ProjectService;
import cn.edu.buaa.rec.service.impl.RuleCheckImpl;
import cn.edu.buaa.rec.service.impl.UserProjectRoleServiceImpl;
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
    @Qualifier("RuleCheckService")
    private RuleCheckImpl ruleCheckService;

    @Autowired
    @Qualifier("UserProjectRoleService")
    private UserProjectRoleServiceImpl userProjectRoleService;

    @RequestMapping("/home")
    @ResponseBody
    public Map<String, Object> ProjectHomePage(@Valid @RequestBody String projectName){
        return projectService.getProjectInfo(projectName);
    }

    @RequestMapping("/roleapply")
    @ResponseBody
    public Map<String, Object> applyRole(@Valid @RequestBody Map<String, Object> applyRoleInfo){
        return projectService.applyRole(applyRoleInfo);
    }

    @RequestMapping("/manapply")
    @ResponseBody
    public Map<String, Object> applyManager(@Valid @RequestBody Map<String, Object> applyManagerInfo){
        return projectService.applyManager(applyManagerInfo);
    }

    @RequestMapping("/scenes")
    @ResponseBody
    public List<Map<String, Object>> showScenes(@Valid @RequestBody String projectName){
//        return projectService.getScenes(projectName);
//        关系改变，重新写
        return null;
    }

    @RequestMapping(value = "/uc/new",method = RequestMethod.POST)
    @ResponseBody
    public String showCheckResult(@Valid @RequestBody String rucmModel){
        String result = ruleCheckService.ruleCheckResult(rucmModel);
        return result;
    }

    @RequestMapping(value = "/role",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> showRole(@Valid @RequestBody Map<String, Object> info){
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
        Long projectId = jsonObject.getLong("projectId");
        Long userId = jsonObject.getLong("userId");
        Map<String,Object> m = new HashMap<String,Object>();
        List<Role> listRoles = projectService.getRole(projectId);
        m.put("listRoles",listRoles);
        List<Long> roleIds = userProjectRoleService.getUserRoleId(projectId,userId);
        List<Role>  userRoles =new LinkedList<Role>();
        for (Role r : listRoles){
            for (Long id : roleIds ){
                if (id == r.getId()){
                    userRoles.add(r);
                }
            }
        }

        m.put("userRoles",userRoles);
        return m;
    }

    @RequestMapping("/data")
    @ResponseBody
    public String showData(@Valid @RequestBody String projectName){
        return projectService.getData(projectName);
    }

    @RequestMapping("/question")
    @ResponseBody
    public String showQuestion(@Valid @RequestBody String projectName){
        return projectService.getQuestion(projectName);
    }

    @RequestMapping("/solution")
    @ResponseBody
    public String showSolution(@Valid @RequestBody String projectName){
        return projectService.getSolution(projectName);
    }
}

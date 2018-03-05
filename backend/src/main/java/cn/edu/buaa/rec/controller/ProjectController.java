package cn.edu.buaa.rec.controller;

import cn.edu.buaa.rec.model.*;
import cn.edu.buaa.rec.service.BusinessRoleService;
import cn.edu.buaa.rec.service.ProjectService;
import cn.edu.buaa.rec.service.impl.BusinessRoleServiceImpl;
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

    @Autowired
    @Qualifier("BusinessRoleService")
    private BusinessRoleServiceImpl businessRoleService;

    @RequestMapping("/home")
    @ResponseBody
    public Map<String, Object> ProjectHomePage(@Valid @RequestBody Map<String, Object> info){
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
        Long projectId = jsonObject.getLong("projectId");
        Long userId = jsonObject.getLong("userId");
        Map<String,Object> m = new HashMap<String,Object>();
        List<Long> roleIds = userProjectRoleService.getUserRoleId(projectId,userId);
        List<Role> roles = projectService.getRole(projectId);
        List<Long> bIds = businessRoleService.getBusinessId(roleIds);
        System.out.println(bIds.toString());
        List<Business> listBusiness = projectService.getBusinessByIds(bIds);




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
        List<Role> roles = projectService.getRole(projectId);

        List<Long> roleIds = userProjectRoleService.getUserRoleId(projectId,userId);
        List<Role>  userRoles = new LinkedList<Role>();
        List<Role>  listRoles = new LinkedList<Role>();
        for (Role r : roles){
            Boolean flag = false ;
            for (Long id : roleIds ){
                if (id == r.getId()){
                    userRoles.add(r);
                    flag = true;
                    break;
                }
            }
            if (!flag){
                listRoles.add(r);
            }
        }
        m.put("listRoles",listRoles);
        m.put("userRoles",userRoles);
        return m;
    }

    @RequestMapping(value = "/data",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> showData(@Valid @RequestBody Map<String,Object> info){
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
        Long projectId = jsonObject.getLong("projectId");
        Long userId = jsonObject.getLong("userId");
        Map<String,Object> map = new HashMap<String,Object>();
        List<Data> datas = projectService.getData(projectId);
        List<Data> listDatas = new LinkedList<Data>();
        List<Data> userDatas = new LinkedList<Data>();
        for (Data d : datas){
            if (d.getCreatorId()!=userId){
                System.out.println(d.toString());
                listDatas.add(d);
            }else{
                userDatas.add(d);
            }
        }

        map.put("listDatas", datas);
        map.put("userDatas", userDatas);
        return map;
    }

    @RequestMapping("/question")
    @ResponseBody
    public Map<String, Object> showQuestion(@Valid @RequestBody Map<String, Object> info){
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
        Long projectId = jsonObject.getLong("projectId");
        Long userId = jsonObject.getLong("userId");
        Map<String,Object> map = new HashMap<String,Object>();
        List<Question> questions = projectService.getQuestion(projectId);
        List<Question> listQuestions = new LinkedList<Question>();
        List<Question> userQuestions = new LinkedList<Question>();
        for (Question q : questions){
            if (q.getCreatorId()!=userId){
                System.out.println(q.toString());
                listQuestions.add(q);
            }else{
                userQuestions.add(q);
            }
        }
        map.put("listQuestions", questions);
        map.put("userQuestions", userQuestions);
        return map;
    }

    @RequestMapping(value = "/solution",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> showSolution(@Valid @RequestBody Map<String,Object> info){
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
        Long projectId = jsonObject.getLong("projectId");
        Long userId = jsonObject.getLong("userId");
        Map<String,Object> map = new HashMap<String,Object>();
        List<Solution> solutions = projectService.getSolution(projectId);
        List<Solution> listSolutions = new LinkedList<Solution>();
        List<Solution> userSolutions = new LinkedList<Solution>();
        for (Solution s : solutions){
            if (s.getCreatorId()!=userId){
                System.out.println(s.toString());
                listSolutions.add(s);
            }else{
                userSolutions.add(s);
            }
        }

        map.put("listSolutions", solutions);
        map.put("userSolutions", userSolutions);
        return map;
    }
}

package cn.edu.buaa.rec.controller;

import cn.edu.buaa.rec.service.ProjectService;
import cn.edu.buaa.rec.service.impl.RuleCheckImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
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

    @RequestMapping("/role")
    @ResponseBody
    public String showRole(@Valid @RequestBody String projectName){
        return projectService.getRole(projectName);
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

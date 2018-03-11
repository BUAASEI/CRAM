package cn.edu.buaa.rec.controller;

import cn.edu.buaa.rec.entity.SolutionEntity;
import cn.edu.buaa.rec.model.Solution;
import cn.edu.buaa.rec.service.SolutionService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/solution")
public class SolutionController {

    @Autowired
    @Qualifier("SolutionService")
    private SolutionService solutionService;

    /**
     * 新建解决方案
     *
     * @param info
     * @return
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> newSolution(@Valid @RequestBody Map<String, Object> info) {

        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
        System.out.println(jsonObject.getString("title").toString());
        Solution solution = new Solution(jsonObject.getString("title"), jsonObject.getString("content"), jsonObject.getLong("creatorId"), jsonObject.getLong("projectId"), jsonObject.getInteger("type"), jsonObject.getInteger("flag"));

        System.out.println(solution.toString());
        return solutionService.newSolution(solution);
    }

    /**
     * 获取用户以及用户所属项目相关的解决方案列表
     * @return 方案列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getAllSolutionsOfProjectAndUser(@Valid @RequestBody Map<String, Object> params) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(params);
        long projectId = jsonObject.getLong("projectId");
        long userId = jsonObject.getLong("userId");
        System.out.println(projectId + " " + userId);
        Map<String,Object>  result = new HashMap<>();
        List<Map<String,Object>> solutionsOfProjectAndUser = solutionService.getAllSolutionsOfProjectAndUser(projectId, userId);
        List<Map<String,Object>> solutionOfProject = solutionService.getAllSolutionsOfProject(projectId);
        result.put("listSolutions",solutionOfProject);
        result.put("userSolutions",solutionsOfProjectAndUser);
        return result;
    }

    /**
     * 更新方案
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateSolution(@Valid @RequestBody Map<String, Object> params) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(params);
        Solution solution = new Solution();
        solution.setId(jsonObject.getLong("id"));
        solution.setTitle(jsonObject.getString("title"));
        solution.setContent(jsonObject.getString("content"));
        return  solutionService.updateSolution(solution);
    }
    /**
     * 删除方案
     *
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteSolution(@Valid @RequestBody Map<String, Object> params) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(params);
        long id = jsonObject.getLong("id");
        return solutionService.deleteSolution(id);
    }

}

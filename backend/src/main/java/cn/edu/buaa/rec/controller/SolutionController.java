package cn.edu.buaa.rec.controller;

import cn.edu.buaa.rec.model.Solution;
import cn.edu.buaa.rec.service.SolutionService;
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
import java.util.Map;

@Controller
@RequestMapping("/solution")
public class SolutionController {
    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    @Qualifier("SolutionService")
    private SolutionService solutionService;

    //    新建解决方案
    @RequestMapping(value="/new", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> newSolution(@Valid @RequestBody Map<String, Object> info) {

        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
        System.out.println(jsonObject.getString("title").toString());
        Solution solution = new Solution(jsonObject.getString("title"), jsonObject.getString("content"), jsonObject.getLong("creatorId"),jsonObject.getLong("projectId"),jsonObject.getInteger("type"),jsonObject.getInteger("flag"));

        System.out.println(solution.toString());
        return solutionService.newSolution(solution);
    }
}

package cn.edu.buaa.rec.controller;

import cn.edu.buaa.rec.model.Question;
import cn.edu.buaa.rec.service.QuestionService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: suruo
 * @Email: csuruo@gmail.com
 * @Date: Created on 上午1:30 2018/01/25
 * @Modified by:
 */

@RestController
@RequestMapping("/question")
//@EnableAutoConfiguration
public class QuestionController {
    @Autowired
    @Qualifier("QuestionService")
    private QuestionService questionService;

    /**
     * 新建问题
     *
     * @param info
     * @return
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> newQuestion(@Valid @RequestBody Map<String, Object> info) {

        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
        Question question = new Question(jsonObject.getString("title"), jsonObject.getString("content"), jsonObject.getLong("creatorId"), jsonObject.getLong("projectId"), jsonObject.getInteger("type"));

        System.out.println(question.toString());

        return questionService.newQuestion(question);
    }

    /**
     * 老的添加问题接口，废弃
     *
     * @param questionId
     * @return
     */
    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> newQuestion1(@RequestParam("id") long questionId) {

        Question question = new Question();
        question.setId(questionId);
        return questionService.newQuestion(question);
    }

    public Map<String, Object> updateQuestion(@Valid @RequestBody Map<String, Object> questInfo) {
        return null;
    }

    /**
     * 获取问题列表
     * @param questInfo
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> questList(@Valid @RequestBody Map<String, Object> questInfo) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(questInfo);
        long projectId = jsonObject.getLong("projectId");
        long userId = jsonObject.getLong("userId");

        List<List<Map<String, Object>>> questions = new ArrayList<>(2);

        List<Map<String, Object>>  questsOfUserAndProject = questionService.getAllQuestionsOfProjectAndUser(projectId, userId);
        List<Map<String, Object>> questsOfProject = questionService.getAllQuestionsOfProject(projectId);
        Map<String,Object> m = new HashMap<>();
        m.put("listQuestions",questsOfProject);
        m.put("userQuestions",questsOfUserAndProject);
        return m;

    }
}

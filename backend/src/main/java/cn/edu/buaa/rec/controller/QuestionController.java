package cn.edu.buaa.rec.controller;

import cn.edu.buaa.rec.model.Question;
import cn.edu.buaa.rec.model.Role;
import cn.edu.buaa.rec.service.QuestionService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
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
@EnableAutoConfiguration
public class QuestionController {
    @Autowired
    @Qualifier("QuestionService")
    private QuestionService questionService;

    @Autowired
    public QuestionService getQuestionService() {
        return questionService;
    }

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @RequestMapping(value="/new",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> newQuestion(@Valid @RequestBody Map<String, Object> info) {

        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
        Question question = new Question(jsonObject.getString("title"), jsonObject.getString("content"), jsonObject.getLong("creatorId"),jsonObject.getLong("projectId"),jsonObject.getInteger("type"));

        System.out.println(question.toString());
        return questionService.newQuestion(question);
    }

    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> newQuestion1(@RequestParam("id") long questionId) {

        Question question = new Question();
        question.setId(questionId);
        return questionService.newQuestion(question);
    }
}

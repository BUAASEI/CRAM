package cn.edu.buaa.rec.controller;

import cn.edu.buaa.rec.model.Question;
import cn.edu.buaa.rec.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

    @RequestMapping("/new")
    @ResponseBody
    public boolean newQuestion() {
//        Question question = new Question();
//        question.setId(2L);
//        question.setBuildTime(new Date());
//        question.setCreatorId(1L);
//        question.setProjectId(1L);
//        question.setDescription("hello world");
//        question.setTitle("linux");
//        question.setType(1);
//        question.setUpdateTime(new Date());
//        return questionService.newQuestion(question);
        return false;
    }

    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    @ResponseBody
    public boolean newQuestion1(@RequestParam("id") long questionId) {

        Question question = new Question();
        question.setId(questionId);
        return questionService.newQuestion(question);
    }
}

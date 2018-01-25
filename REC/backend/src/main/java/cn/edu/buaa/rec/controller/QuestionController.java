package cn.edu.buaa.rec.controller;

import cn.edu.buaa.rec.model.Question;
import cn.edu.buaa.rec.service.QuestionService;
import cn.edu.buaa.rec.service.impl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;
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


    public QuestionService getQuestionService() {
        return questionService;
    }


    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    @Qualifier("QuestionService")
    private QuestionService questionService;

    @RequestMapping("/new")
    @ResponseBody
    public boolean newQuestion() {
        Question question = new Question();
        question.setQuestionId(2L);
        question.setBuildTime(new Date());
        question.setCreatorId(1L);
        question.setProjectId(1L);
        question.setQuestionDescription("hello world");
        question.setQuestionTitle("linux");
        question.setType(1L);
        question.setUpdateTime(new Date());
        return questionService.newQuestion(question);
    }

    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    @ResponseBody
    public boolean newQuestion1(@RequestParam("id") long questionId) {

        Question question = new Question();
        question.setQuestionId(questionId);
        return questionService.newQuestion(question);
    }
}

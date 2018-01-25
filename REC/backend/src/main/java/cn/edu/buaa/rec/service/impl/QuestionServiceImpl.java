package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.QuestionMapper;
import cn.edu.buaa.rec.model.Question;
import cn.edu.buaa.rec.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: suruo
 * @Email: csuruo@gmail.com
 * @Date: Created on 上午1:25 2018/01/25
 * @Modified by:
 */
@Service("QuestionService")
public class QuestionServiceImpl implements QuestionService {

    public QuestionMapper getQuestionMapper() {
        return questionMapper;
    }

    @Autowired
    public void setQuestionMapper(QuestionMapper questionMapper) {
        System.out.println(questionMapper);
        this.questionMapper = questionMapper;
    }


    private QuestionMapper questionMapper;

    public QuestionServiceImpl() {
        System.out.println(questionMapper);
        System.out.println("hello world");
    }

    @Override
    public boolean newQuestion(Question question) {
        return questionMapper.insert(question) == 1;
    }
}

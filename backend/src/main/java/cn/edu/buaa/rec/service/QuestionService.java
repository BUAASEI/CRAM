package cn.edu.buaa.rec.service;

import cn.edu.buaa.rec.model.Question;

import java.util.Map;

/**
 * @Description:
 * @Author: suruo
 * @Email: csuruo@gmail.com
 * @Date: Created on 上午1:23 2018/01/25
 * @Modified by:
 */

public interface QuestionService {
    Map<String,Object> newQuestion(Question question);
}

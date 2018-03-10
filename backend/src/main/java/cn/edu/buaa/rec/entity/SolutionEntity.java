package cn.edu.buaa.rec.entity;

import cn.edu.buaa.rec.model.Solution;

/**
 * @Description:
 * @Author: suruo
 * @Email: csuruo@gmail.com
 * @Date: Created on 下午9:40 2018/03/10
 * @Modified by:
 */

public class SolutionEntity extends Solution{

    private String question;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public SolutionEntity() {
    }
}

package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.QuestionMapper;
import cn.edu.buaa.rec.model.Question;
import cn.edu.buaa.rec.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: suruo
 * @Email: csuruo@gmail.com
 * @Date: Created on 上午1:25 2018/01/25
 * @Modified by:
 */
@Service("QuestionService")
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public Map newQuestion(Question question) {
        if (question == null) {
            return null;
        }

        String title = question.getTitle();
        Long projectid = question.getProjectId();
        int count = questionMapper.checkByTitleAndProjectId(title, projectid);
        Map<String, Object> m = new HashMap<>();
        if (count > 0) {
            m.put("Msg", "该问题已经存在！");
        } else {
            Long questionIdMax = questionMapper.selectMaxId();
            question.setId((questionIdMax == null) ? 1 : questionIdMax + 1);
            question.setBuildTime(new Date());
            int r = questionMapper.insert(question);
            if (r == 1) {
                m.put("Msg", "新问题创建成功！");
            } else {
                m.put("Msg", "请检查数据格式！");
            }
        }
        return m;
    }
}

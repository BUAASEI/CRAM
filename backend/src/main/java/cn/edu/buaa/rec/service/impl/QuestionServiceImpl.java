package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.QuestionMapper;
import cn.edu.buaa.rec.model.Question;
import cn.edu.buaa.rec.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

    @Override
    public List<Map<String, Object>> getAllQuestionsOfProject(long projectId) {
        List<Map<String, Object>> questWithhBussiness = questionMapper.selectByProjectIdToBussiness(projectId);
        List<Map<String, Object>> questWithUsecase = questionMapper.selectByProjectIdToUsecase(projectId);
        questWithhBussiness.addAll(questWithUsecase);
        return questWithhBussiness;
    }

    @Override
    public List<Map<String, Object>> getAllQuestionsOfProjectAndUser(long projectId, long userId) {
        List<Map<String, Object>> questWithBussiness =  questionMapper.selectByProjectIdAndUserIdToBussiness(projectId, userId);
        List<Map<String, Object>> questWithUsecase = questionMapper.selectByProjectIdAndUserIdToUsecase(projectId, userId);
        questWithBussiness.addAll(questWithUsecase);
        return questWithBussiness;
    }

    @Override
    public Map<String, Object> updateQuestion(Question question) {
        Map<String, Object> result = new HashMap<>();
        int res = questionMapper.update(question);
        if (res == 1) {
            result.put("Msg", "更新解决方案成功");
        } else {
            result.put("Msg", "更新方案失败");
        }
        return result;
    }

    @Override
    public Map<String, Object> deleteQuestion(Long id) {
        Map<String, Object> result = new HashMap<>();
        int res = questionMapper.delete(id);
        if (res == 1) {
            result.put("Msg", "删除解决方案成功");
        } else {
            result.put("Msg", "删除方案失败");
        }
        return result;
    }
}

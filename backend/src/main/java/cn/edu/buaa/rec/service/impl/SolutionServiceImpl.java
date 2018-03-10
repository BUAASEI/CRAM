package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.SolutionMapper;
import cn.edu.buaa.rec.entity.SolutionEntity;
import cn.edu.buaa.rec.model.Solution;
import cn.edu.buaa.rec.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("SolutionService")
public class SolutionServiceImpl implements SolutionService {
    @Autowired
    private SolutionMapper solutionMapper;

    @Override
    public Map newSolution(Solution solution) {
        if (solution == null) {
            return null;
        }
        String title = solution.getTitle();
        Long projectid = solution.getProjectId();
        int count = solutionMapper.checkByTitleAndProjectId(title, projectid);
        Map<String, Object> m = new HashMap<>();
        if (count > 0) {
            m.put("Msg", "该解决方案已经存在！");
        } else {
            Long solutionIdMax = solutionMapper.selectMaxId();
            solution.setId((solutionIdMax == null) ? 1 : solutionIdMax + 1);
            solution.setBuildTime(new Date());
            int r = solutionMapper.insert(solution);
            if (r == 1) {
                m.put("Msg", "新解决方案创建成功！");
            } else {
                m.put("Msg", "请检查数据格式！");
            }
        }

        return m;
    }

    @Override
    public List<Map<String,Object>> getAllSolutionsOfProject(long projectId) {
        return solutionMapper.selectByProjectId(projectId);
    }

    @Override
    public List<Map<String,Object>> getAllSolutionsOfProjectAndUser(long projectId, long userId) {

        return solutionMapper.selectByProjectIdAndUserId(projectId, userId);
    }

    @Override
    public Map<String, Object> updateSolution(Solution solution) {
        Map<String, Object> result = new HashMap<>();
        int res = solutionMapper.update(solution);
        if (res == 1) {
            result.put("Msg", "更新解决方案成功");
        } else {
            result.put("Msg", "更新方案失败");
        }
        return result;
    }
    @Override
    public Map<String, Object> deleteSolution(Long id) {
        Map<String, Object> result = new HashMap<>();
        int res = solutionMapper.delete(id);
        if (res == 1) {
            result.put("Msg", "删除解决方案成功");
        } else {
            result.put("Msg", "删除方案失败");
        }
        return result;
    }

}


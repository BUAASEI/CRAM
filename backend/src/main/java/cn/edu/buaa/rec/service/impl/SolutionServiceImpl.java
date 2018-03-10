package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.SolutionMapper;
import cn.edu.buaa.rec.model.Solution;
import cn.edu.buaa.rec.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
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
}

package cn.edu.buaa.rec.service;

import cn.edu.buaa.rec.entity.SolutionEntity;
import cn.edu.buaa.rec.model.Solution;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface SolutionService {
    Map<String, Object> newSolution(Solution solution);

    List<Map<String,Object>> getAllSolutionsOfProject(long projectId);

    List<Map<String,Object>> getAllSolutionsOfProjectAndUser(long projectId, long userId);

    Map<String, Object> updateSolution(Solution solution);

    Map<String, Object> deleteSolution(Long id);
}



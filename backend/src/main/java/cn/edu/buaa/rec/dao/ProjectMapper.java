package cn.edu.buaa.rec.dao;

import cn.edu.buaa.rec.model.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectMapper {

    int insert(Project record);

    Long selectMaxId();

    Project selectByName(String name);

    List<Project> selectAllProjects();

    Project selectById(Long projectId);

    List<String> selectNameByIds(@Param("ids")List<Long> ids);
}
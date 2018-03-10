package cn.edu.buaa.rec.dao;

import cn.edu.buaa.rec.model.UserProject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserProjectMapper {
    int insert(UserProject record);

    List<Long> selectByUserId(Long userId);

    Long selectExistOrNot(@Param("projectId")Long projectId, @Param("userId") Long userId);

    Long selectMaxId();

    List<UserProject> selectProApplyByProjectId(Long projectId);

    int updateApprovedById(@Param("id") Long id, @Param("isapproved") Integer isapproved);
}
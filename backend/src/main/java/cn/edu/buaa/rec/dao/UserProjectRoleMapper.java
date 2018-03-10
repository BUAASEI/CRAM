package cn.edu.buaa.rec.dao;

import cn.edu.buaa.rec.model.UserProjectRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserProjectRoleMapper {

    int insert(UserProjectRole record);

    int insertSelective(UserProjectRole record);
    List<UserProjectRole> selectBySysUserId(Long sysUserId);

    List<Long> selectByProjectIdAndUserId(@Param("projectId")Long projectId,@Param("userId") Long userId);

    List<Long> selectUserByProjectId(@Param("projectIds") List<Long> projectid);

    List<Long> selectProjectByUserIdWithNoApproved(@Param("userId") Long userId);

//    根据项目Id，检索申请角色的记录对应的UserId
    List<UserProjectRole> selectRoleApplyByProjectId(@Param("projectId") Long projectId);

//    <!-- 根据Id修改角色申请的审批状态 -->
    int updateApprovedById(@Param("id") Long id, @Param("isapproved") Integer isapproved);

    Long selectMaxId();

    int checkIsManger(@Param("projectId")Long projectId,@Param("userId")Long userId);

}
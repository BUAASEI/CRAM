package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.ProjectMapper;
import cn.edu.buaa.rec.dao.SysUserMapper;
import cn.edu.buaa.rec.dao.UserProjectManMapper;
import cn.edu.buaa.rec.model.Project;
import cn.edu.buaa.rec.model.SysUser;
import cn.edu.buaa.rec.model.UserProjectMan;
import cn.edu.buaa.rec.service.UserProjectManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description:
 * @Author: suruo
 * @Email: csuruo@gmail.com
 * @Date: Created on 下午4:41 2018/02/06
 * @Modified by:
 */

@Service("UserProjectManService")
public class UserProjectManServiceImpl implements UserProjectManService {

    @Autowired
    private UserProjectManMapper userProjectManMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<Map<String, Object>> manProject(Long userId) {

        List<Map<String, Object>> m = new LinkedList<>();

//        根据用户id，查询user_project_man表，返回其管理的项目的UserProjectMan信息
        List<Long> manProjectsIdList = userProjectManMapper.selectProjectByUserId(userId);

        for (Long projectId : manProjectsIdList
                ) {
            Project project = projectMapper.selectById(projectId);
            Map<String, Object> temp = new HashMap<>();
            temp.put("id",project.getId());
            temp.put("ProjectName", project.getName());
            temp.put("ProjectDescription", project.getName());

            //将该项目的信息，加入返回的List中
            m.add(temp);
        }
        return m;
    }

    /**
     * 根据审批结果，修改数据库后台isApproved字段r
     *
     * @param id
     * @param isapproved
     * @return
     */
    @Override
    public int updateByApprove(Long id, Integer isapproved) {
        return userProjectManMapper.updateApprovedById(id, isapproved);
    }

    @Override
    public List<Map<String, Object>> getApply(Long projectId) {
        List<Map<String, Object>> manApplyInfo = new ArrayList<>();
//        检索出申请该用户管理的项目的项目管理员的UserProjectMan关系记录
        List<UserProjectMan> userProjectMans = userProjectManMapper.selectManApplyByProjectId(projectId);
        for (UserProjectMan userProjectMan : userProjectMans
                ) {
//            拿到申请用户的个人信息：名字、擅长领域和项目经验
            SysUser applyUser = sysUserMapper.selectById(userProjectMan.getUserId());
            Map<String, Object> manApplyEachInfo = new HashMap<>();
            manApplyEachInfo.put("applyId", userProjectMan.getId());
            manApplyEachInfo.put("roleName", "项目管理员");
            manApplyEachInfo.put("userName", applyUser.getName());
            manApplyEachInfo.put("familiarDomain", applyUser.getFamiliardomain());
            manApplyEachInfo.put("projectExp", applyUser.getProjectexp());
            manApplyEachInfo.put("planation", userProjectMan.getExplanation());

            manApplyInfo.add(manApplyEachInfo);
        }
        return manApplyInfo;
    }

    @Override
    public int checkIsManger(Long projectId, Long userId) {

        return userProjectManMapper.checkIsManger(projectId,userId);
    }
}

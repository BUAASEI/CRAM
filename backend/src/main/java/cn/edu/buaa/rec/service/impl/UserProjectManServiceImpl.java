package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.ProjectMapper;
import cn.edu.buaa.rec.dao.SysUserMapper;
import cn.edu.buaa.rec.dao.UserProjectManMapper;
import cn.edu.buaa.rec.model.Project;
import cn.edu.buaa.rec.service.UserProjectManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
            temp.put("ProjectName", project.getName());
            temp.put("ProjectDescription", project.getName());

            //将该项目的信息，加入返回的List中
            m.add(temp);
        }
        return m;
    }

    //    根据审批结果，修改数据库后台isapproved字段
    @Override
    public int updateByApprove(Long id, Integer isapproved) {
        return userProjectManMapper.updateApprovedById(id, isapproved);
    }
}

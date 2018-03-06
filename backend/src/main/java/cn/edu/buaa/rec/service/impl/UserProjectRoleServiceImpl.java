package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.ProjectMapper;
import cn.edu.buaa.rec.dao.SysUserMapper;
import cn.edu.buaa.rec.dao.UserProjectRoleMapper;
import cn.edu.buaa.rec.model.Project;
import cn.edu.buaa.rec.model.SysUser;
import cn.edu.buaa.rec.model.UserProjectRole;
import cn.edu.buaa.rec.service.UserProjectRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description:
 * @Author: suruo
 * @Email: csuruo@gmail.com
 * @Date: Created on 下午7:28 2018/02/06
 * @Modified by:
 */

@Service("UserProjectRoleService")
public class UserProjectRoleServiceImpl implements UserProjectRoleService {

    @Autowired
    private UserProjectRoleMapper userProjectRoleMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public List<Map<String, Object>> parProject(Long sysUserId) {

        List<Map<String, Object>> m = new LinkedList<>();

//        根据用户id，查询user_project_man表，返回其管理的项目的UserProjectMan信息
        List<UserProjectRole> userProjectProList = userProjectRoleMapper.selectBySysUserId(sysUserId);
        Iterator<UserProjectRole> iterator = userProjectProList.iterator();
        while (iterator.hasNext()) {
//            对每个项目，通过项目id，查询项目Project信息
            Project project = projectMapper.selectById(iterator.next().getProjectId());
            SysUser sysUser = sysUserMapper.selectById(project.getCreatorId());

            Map<String, Object> temp = new HashMap<>();
            temp.put("ProjectName", project.getName());
            temp.put("CreatorName", sysUser.getName());

            //将 该项目的信息，加入返回的List中
            m.add(temp);
        }

        return m;
    }

    @Override
    public List<Long> getUserRoleId(Long projectId, Long userId) {
        //根据用户Id和项目Id查寻角色Id
        if (projectId == 0 || userId == 0) {
            return null;
        }

        List<Long> list = userProjectRoleMapper.selectByProjectIdAndUserId(projectId, userId);
        return list;
    }
}

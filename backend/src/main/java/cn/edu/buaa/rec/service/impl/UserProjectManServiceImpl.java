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
    public List<Map<String, Object>> manProject(Long sysUserId) {

        List<Map<String, Object>> m = new LinkedList<>();

//        根据用户id，查询user_project_man表，返回其管理的项目的UserProjectMan信息
        List<UserProjectMan> userProjectManList = userProjectManMapper.selectBySysUserId(sysUserId);
        Iterator<UserProjectMan> iterator = userProjectManList.iterator();
        while (iterator.hasNext()) {
//            对每个项目，通过项目id，查询项目Project信息
            Long projectId = iterator.next().getProjectId();
            Project project = projectMapper.selectById(projectId);
            SysUser sysUser = sysUserMapper.selectById(project.getCreatorId());

            Map<String, Object> temp = new HashMap<>();
            temp.put("ProjectName", project.getName());
            temp.put("CreatorName", sysUser.getName());

            //将 该项目的信息，加入返回的List中
            m.add(temp);
        }

        return m;
    }
}

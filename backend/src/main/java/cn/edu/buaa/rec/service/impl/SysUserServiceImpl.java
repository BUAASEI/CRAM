package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.*;
import cn.edu.buaa.rec.model.Project;
import cn.edu.buaa.rec.model.SysUser;
import cn.edu.buaa.rec.model.UserProjectMan;
import cn.edu.buaa.rec.model.UserProjectRole;
import cn.edu.buaa.rec.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: suruo
 * @Email: csuruo@gmail.com
 * @Date: Created on 上午6:23 2018/02/01
 * @Modified by:
 */

//这儿要声明*Service的注解，要不到找不到对应的Service
@Service("SysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserProjectMapper userProjectMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private UserProjectManMapper userProjectManMapper;
    @Autowired
    private UserProjectRoleMapper userProjectRoleMapper;

    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    /**
     * 新建系统用户
     */
    @Override
    public Map<String, Object> newSysUser(SysUser sysUser) {
        //        保存并返回从数据库查询出的结果数据
        Map<String, Object> m = new HashMap<>();
        String sysUserName = sysUser.getName();
        if (noExist(sysUserName)) {
            //            反馈用户id
            //            如果之前存在记录，那么id+1
            //            如果不存在，id设置为1
            Long sysUserIdMax = sysUserMapper.selectMaxId();
            sysUser.setId((sysUserIdMax == null) ? 1 : sysUserIdMax + 1);

            if (sysUserMapper.insert(sysUser) != 1) {
                m.put("Msg", "请检查输入数据格式");
            } else {
                m.put("Msg", "新用户注册成功");
            }
        } else {
            m.put("Msg", "该用户名已存在");
        }
        return m;
    }

    //    修改系统用户信息
    @Override
    public Map<String, Object> modSysUserInfo(SysUser userInfo) {
        Map<String, Object> m = new HashMap<>();
        if (sysUserMapper.updateById(userInfo) != 1) {
            m.put("Msg", "请检查输入数据格式");
        } else {
            m.put("Msg", "用户信息更新成功 @^@ ");
        }
        return m;
    }

    //    根据Id，返回相应的SysUser信息
    @Override
    public SysUser selectById(Long sysUserId) {
        return sysUserMapper.selectById(sysUserId);
    }

    //    查询该用户所管理的项目中涉及的管理员和角色申请
    @Override
    public Map<String, List<Map<String, Object>>> getApply(Long projectId) {

        Map<String, List<Map<String, Object>>> result = new HashMap<>();

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

            manApplyInfo.add(manApplyEachInfo);
        }

        List<Map<String, Object>> roleApplyInfo = new ArrayList<>();
        List<UserProjectRole> roleApplyUserInfos = userProjectRoleMapper.selectRoleApplyByProjectId(projectId);
        for (UserProjectRole userProjectRole : roleApplyUserInfos
                ) {
//            拿到申请角色的个人信息：名字、擅长领域和项目经验，还有所申请的角色的名字
            SysUser applyUser = sysUserMapper.selectById(userProjectRole.getUserId());
            Map<String, Object> roleApplyEachInfo = new HashMap<>();
            roleApplyEachInfo.put("applyId", userProjectRole.getId());
            roleApplyEachInfo.put("roleName", roleMapper.selectById(userProjectRole.getRoleId()).getName());
            roleApplyEachInfo.put("userName", applyUser.getName());
            roleApplyEachInfo.put("familiarDomain", applyUser.getFamiliardomain());
            roleApplyEachInfo.put("projectExp", applyUser.getProjectexp());

            roleApplyInfo.add(roleApplyEachInfo);
        }

        result.put("Man", manApplyInfo);
        result.put("Role", roleApplyInfo);

        return result;
    }

    @Override
    public Map<Long, String>
    getManProjectId(Long userId) {

        Map<Long, String> result = new HashMap<>();

//        检索出该用户管理的project的id
        List<Long> manProjectIds = userProjectManMapper.selectProjectByUserId(userId);

        for (Long projectId : manProjectIds
                ) {
            Project manProjects = projectMapper.selectById(projectId);
            result.put(manProjects.getId(), manProjects.getName());
        }

        return result;
    }

    //    检查该用户名是否已经存在于数据库中
    @Override
    public boolean noExist(String name) {

//        如果用户名已存在，则返回false
        return sysUserMapper.selectByName(name) == null;
    }

    //    通过用户名，查找用户相关信息
    @Override
    public SysUser getByName(String name) {
        return sysUserMapper.selectByName(name);
    }

    @Override
    public String getNameById(Long creatorId) {
        if (creatorId == null || creatorId == 0) {
            return null;
        }
        String name = sysUserMapper.selectNameById(creatorId);
        return name;
    }

}

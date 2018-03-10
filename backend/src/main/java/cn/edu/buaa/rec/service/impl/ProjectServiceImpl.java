package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.*;
import cn.edu.buaa.rec.model.*;
import cn.edu.buaa.rec.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description:
 * @Author: suruo
 * @Email: csuruo@gmail.com
 * @Date: Created on 上午1:29 2018/02/06
 * @Modified by:
 */

@Service("ProjectService")
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private DomainMapper domainMapper;
    @Autowired
    private UsecaseMapper usecaseMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private DataMapper dataMapper;
    @Autowired
    private UsecaseDataMapper usecaseDataMaper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private SolutionMapper solutionMapper;
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private UserProjectMapper userProjectMapper;

    @Autowired
    private UserProjectManMapper userProjectManMapper;

    @Override
    public Project getProjectById(Long projectId) {
        if (projectId == null || projectId == 0) {
            return null;
        }

        Project project = projectMapper.selectById(projectId);
        return project;
    }

    @Override
    public Map<String, Object> newProject(Project project) {
        //        需要重新写
        //        保存并返回从数据库查询出的结果数据
        Map<String, Object> m = new HashMap<>();
        String projectName = project.getName();
        if (noExist(projectName)) {
            Long projectIdMax = projectMapper.selectMaxId();
            project.setId((projectIdMax == null) ? 1 : (projectIdMax + 1));

            UserProject userProject = new UserProject();

            Long upIdMax = userProjectMapper.selectMaxId();
            userProject.setId((upIdMax == null) ? 1 : (upIdMax + 1));
            userProject.setUserId(project.getCreatorId());
            userProject.setProjectId(project.getId());
            userProjectMapper.insert(userProject);

            if (projectMapper.insert(project) != 1) {
                UserProjectMan userProjectMan = new UserProjectMan();
                userProjectMan.setId(userProjectManMapper.selectMaxId());
                userProjectMan.setIsapproved(1);
                userProjectMan.setProjectId(project.getId());
                userProjectMan.setUserId(project.getCreatorId());
                userProjectMan.setIsMan(1);
                userProjectManMapper.insert(userProjectMan);
                m.put("Msg", "请检查输入数据格式");
            } else {
                m.put("Msg", "创建项目成功");
            }
        } else {
            m.put("Msg", "该项目名已存在");
        }
        return m;
    }

    @Override
    public boolean noExist(String name) {
        return projectMapper.selectByName(name) == null;
    }

    @Override
    public List<Map<String, Object>> allProject() {
        List<Project> projects = projectMapper.selectAllProjects();
        List<Map<String, Object>> m = new LinkedList<>();

//        前后端交互，List中使用的Map格式
//        这里需要将Project对象转化成Map格式
        Iterator<Project> iterator = projects.iterator();
        while (iterator.hasNext()) {
            Project projectInfo = iterator.next();
            SysUser sysUser = sysUserMapper.selectById(projectInfo.getCreatorId());

            Map<String, Object> temp = new HashMap<>();
            temp.put("ProjectId", projectInfo.getId());
            temp.put("ProjectName", projectInfo.getName());
            temp.put("CreatorName", sysUser.getName());

            m.add(temp);
        }
        return m;
    }

    /**
     * 查询没有参与的项目
     * 思路:
     * 1）查询所有项目id
     * 2）对于每个id，查询user_project表，如果不存在，则放进来
     */
    @Override
    public List<Map<String, Object>> otherProject(Long userId) {
        List<Project> allProjects = projectMapper.selectAllProjects();
        List<Map<String, Object>> m = new LinkedList<>();

        for (Project projectInfo : allProjects
                ) {
            Long projectId = projectInfo.getId();

//            如果在user_project关系表中，查不到user和相关项目的对应记录，返回0
//            对于这种项目，应该加入返回结果
            if (userProjectMapper.selectExistOrNot(projectId, userId) == 0) {
                Map<String, Object> temp = new HashMap<>();
                temp.put("ProjectId", projectInfo.getId());
                temp.put("ProjectName", projectInfo.getName());
                temp.put("ProjectDescription", projectInfo.getDescription());
                m.add(temp);
            }
        }
        return m;
    }

    @Override
    public Map<String, Object> getProjectInfo(String projectName) {
        Map<String, Object> m = new HashMap<>();

        Project project = getProject(projectName);

        m.put("BuildTime", project.getBuildTime());
        SysUser sysUser = sysUserMapper.selectById(project.getCreatorId());
        System.out.println(sysUser);
        m.put("CreatorName", sysUser.getName());
        return m;
    }

    //    数据库中添加：user-project-role关联
    @Override
    public Map<String, Object> applyRole(Map<String, Object> applyRoleInfo) {
        return null;
    }

    //    数据库中添加：user-project-man关联
    @Override
    public Map<String, Object> applyManager(Map<String, Object> applyManagerInfo) {
        return null;
    }


    //    展示Project中涉及的Role的界面信息
    @Override
    public List<Role> getRole(Long projectId) {
        if (projectId == null) {
            return null;
        }
        List<Role> roleList = roleMapper.selectByProjectId(projectId);
        return roleList;
    }

    //    展示Project中涉及的Data的界面信息
    @Override
    public List<Data> getData(Long projectId) {
        if (projectId == null) {
            return null;
        }
        System.out.println(projectId);
        List<Data> listDatas = dataMapper.selectByProjectId(projectId);
        return listDatas;
    }

    @Override
    public List<Data> getUserDatas(Long projectId, Long userId) {
        if (projectId == null || userId == null) {
            return null;
        }

        List<Data> userDatas = dataMapper.selectByProjectIdAndUserId(projectId, userId);
        return userDatas;
    }




    private Project getProject(String name) {
        System.out.println("name:" + name);
        Project pro = projectMapper.selectByName(name);
        System.out.println(pro.toString());
        return pro;
    }

    @Override
    public List<Map<String, Object>> getScenes(String projectName) {
        return null;
    }

//    @Override
//    public List<Business> getBusinessByIds(List<Long> bIds) {
//        if (bIds == null){
//            return null;
//        }
//        List<Business> listBusiness = businessMapper.selectBusinessByIds(bIds);
//        return listBusiness;
//    }
}

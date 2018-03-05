package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.*;
import cn.edu.buaa.rec.model.*;
import cn.edu.buaa.rec.service.ProjectService;
import com.alibaba.fastjson.JSON;
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
//    @Autowired
//    private ScenarioMapper scenarioMapper;
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

    @Override
    public Map<String, Object> newProject(Project project) {
//        需要重新写
        //        保存并返回从数据库查询出的结果数据
        Map<String, Object> m = new HashMap<>();
        String projectName = project.getName();
        if (noExist(projectName)) {
            Long projectIdMax = projectMapper.selectMaxId();
            project.setId((projectIdMax == null) ? 1 : (projectIdMax + 1));

            if (projectMapper.insert(project) != 1) {
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
        List<Project> projects = projectMapper.selectAll();
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

    @Override
    public Map<String, Object> getProjectInfo(String projectName) {
        Map<String, Object> m = new HashMap<>();

        Project project = getProject(projectName);

//        项目和领域的对应 关系变了 重新写
//        m.put("DomainName", domainMapper.selectById(project.getDomainId()).getName());
        m.put("BuildTime", project.getBuildTime());
        SysUser sysUser = sysUserMapper.selectById(project.getCreatorId());
        System.out.println(sysUser);
        m.put("CreatorName", sysUser.getName());
        return m;
    }

    //    数据库中添加：user-project-role关联
    //    暂时不做
    @Override
    public Map<String, Object> applyRole(Map<String, Object> applyRoleInfo) {
        return null;
    }

    //    数据库中添加：user-project-man关联
    //    暂时不做
    @Override
    public Map<String, Object> applyManager(Map<String, Object> applyManagerInfo) {
        return null;
    }

    //    索引该项目包含的所有业务场景 & 用况场景
    //  关系改变，重新写
//    @Override
//    public List<Map<String, Object>> getScenes(String projectName) {
//
//        List<Map<String, Object>> m = new LinkedList<>();
//
//        Long projectId = projectMapper.selectByName(projectName).getId();
//        List<Scenario> scenarioList = scenarioMapper.selectByProjectId(projectId);
//        List<Usecase> usecaseList = usecaseMapper.selectByProjectId(projectId);
//
////        索引业务场景数据
//        Iterator<Scenario> scenarioIterator = scenarioList.iterator();
//        while (scenarioIterator.hasNext()) {
//            //            项目中涉及的每一条业务场景数据
//            Scenario scenario = scenarioIterator.next();
//            //            存储返回List中的Map
//            Map<String, Object> temp = new HashMap<>();
//            temp.put("ScenarioName", scenario.getName());
//            Long scenarioId = scenarioMapper.selectByName(scenario.getName()).getId();
//
//            //            索引业务场景中涉及的Role
//            //            一个业务场景可能对应多个Role
//            Map<String, Object> relatedRoleMap = new HashMap<>();
//            List<ScenarioRole> scenarioRoleList = scenarioRoleMapper.selectByScenarioId(scenarioId);
//            for (ScenarioRole scenarioRole : scenarioRoleList) {
//                Long roleId = scenarioRole.getRoleId();
//                //                将每一个Role的id和name，都放到map中
//                relatedRoleMap.put(roleId + "", roleMapper.selectById(roleId).getName());
//            }
//            temp.put("ScenarioRelatedRole", relatedRoleMap);
//
//            //            索引业务场景中涉及的Data
//            //            一个业务场景中，可能涉及多个Data
//            Map<String, Object> relateDataMap = new HashMap<>();
//            List<ScenarioData> scenarioDataList = scenarioDataMapper.selectByScenarioId(scenarioId);
//            for (ScenarioData scenarioData : scenarioDataList) {
//                Long dataId = scenarioData.getDataId();
//                relateDataMap.put(dataId + "", dataMapper.selectById(dataId).getName());
//            }
//            temp.put("ScenarioRelatedData", relateDataMap);
//        }
//
////        用例和角色由一对多编程多对多，新建中间表，重新写
//////        索引用例场景数据
////        Iterator<Usecase> usecaseIterator = usecaseList.iterator();
////        while (usecaseIterator.hasNext()) {
////            //            项目中涉及的每一条用例数据
////            Usecase usecase = usecaseIterator.next();
////
////            //            存储返回List中的Map
////            Map<String, Object> temp = new HashMap<>();
////            temp.put("UsecaseName", usecase.getName());
////            Long usecaseId = usecaseMapper.selectByName(usecase.getName()).getId();
////
////            //            索引用例中涉及的Role
////            //            一个用例只对应一个Role
////            temp.put("UsecaseRelatedRole", roleMapper.selectById(usecase.getRoleId()));
////
////            //            索引业务场景中涉及的Data
////            //            一个业务场景中，可能涉及多个Data
////            Map<String, Object> relateDataMap = new HashMap<>();
////            List<UsecaseData> usecaseDataList = usecaseDataMaper.selectByUsecaseId(usecaseId);
////            for (UsecaseData usecaseData : usecaseDataList) {
////                Long dataId = usecaseData.getDataId();
////                relateDataMap.put(dataId + "", dataMapper.selectById(dataId).getName());
////            }
////            temp.put("UsecaseRelatedData", relateDataMap);
////        }
//
//        return m;
//    }

    //    展示Project中涉及的Role的界面信息
    @Override
    public  List<Role> getRole(Long projectId) {
        if (projectId==null){
            return null;
        }
        List<Role> roleList = roleMapper.selectByProjectId(projectId);
        return roleList;
    }

    //    展示Project中涉及的Data的界面信息
    @Override
    public List<Data> getData(Long projectId) {
        if (projectId==null){
            return null;
        }
        List<Data> listDatas = dataMapper.selectByProjectId(projectId);
        return listDatas;
    }

    @Override
    public List<Data> getUserDatas(Long projectId, Long userId) {
        if (projectId==null || userId == null){
            return null;
        }

        List<Data> userDatas = dataMapper.selectByProjectIdAndUserId(projectId, userId);
        return userDatas;
    }

    @Override
    public List<Question> getQuestion(Long projectId) {
        if (projectId==null){
            return null;
        }
        List<Question> listQuestion = questionMapper.selectByProjectId(projectId);

        return listQuestion;
    }

    @Override
    public List<Solution> getSolution(Long projectId) {

        if (projectId==null){
            return null;
        }
        System.out.println(projectId);
        List<Solution> listSolutions = solutionMapper.selectByProjectId(projectId);
        return listSolutions;
    }

    @Override
    public List<Solution> getUserSolution(Long projectId, Long userId) {
        if (projectId==null || userId == null){
            return null;
        }

        List<Solution> userSolutions = solutionMapper.selectByProjectIdAndUserId(projectId,userId);
        return userSolutions;
    }

    private Project getProject(String name){
        System.out.println("name:" + name);
        Project pro =  projectMapper.selectByName(name);
        System.out.println(pro);
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

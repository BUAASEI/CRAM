package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.*;
import cn.edu.buaa.rec.model.*;
import cn.edu.buaa.rec.service.ProjectService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    private ProjectMapper projectMapper;

    @Autowired
    public ProjectMapper getProjectMapper() {
        return projectMapper;
    }

    @Autowired
    public void setProjectMapper(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    private SysUserMapper sysUserMapper;

    @Autowired
    public SysUserMapper getSysUserMapper() {
        return sysUserMapper;
    }

    @Autowired
    public void setSysUserMapper(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    private DomainMapper domainMapper;

    @Autowired
    @Qualifier("domainMapper")
    public DomainMapper getDomainMapper() {
        return domainMapper;
    }

    @Autowired
    @Qualifier("domainMapper")
    public void setDomainMapper(DomainMapper domainMapper) {
        this.domainMapper = domainMapper;
    }

    private ScenarioMapper scenarioMapper;
    private ScenarioRoleMapper scenarioRoleMapper;
    private UsecaseMapper usecaseMapper;
    private RoleMapper roleMapper;
    private ScenarioDataMapper scenarioDataMapper;
    private DataMapper dataMapper;
    private UsecaseDataMapper usecaseDataMaper;
    private QuestionMapper questionMapper;
    private SolutionMapper solutionMapper;
    @Autowired
    public ScenarioMapper getScenarioMapper() {
        return scenarioMapper;
    }

    @Autowired
    public void setScenarioMapper(ScenarioMapper scenarioMapper) {
        this.scenarioMapper = scenarioMapper;
    }

    @Autowired
    public ScenarioRoleMapper getScenarioRoleMapper() {
        return scenarioRoleMapper;
    }

    @Autowired
    public void setScenarioRoleMapper(ScenarioRoleMapper scenarioRoleMapper) {
        this.scenarioRoleMapper = scenarioRoleMapper;
    }

    @Autowired
    public UsecaseMapper getUsecaseMapper() {
        return usecaseMapper;
    }

    @Autowired
    public void setUsecaseMapper(UsecaseMapper usecaseMapper) {
        this.usecaseMapper = usecaseMapper;
    }

    @Autowired
    public RoleMapper getRoleMapper() {
        return roleMapper;
    }

    @Autowired
    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Autowired
    public ScenarioDataMapper getScenarioDataMapper() {
        return scenarioDataMapper;
    }

    @Autowired
    public void setScenarioDataMapper(ScenarioDataMapper scenarioDataMapper) {
        this.scenarioDataMapper = scenarioDataMapper;
    }

    @Autowired
    public DataMapper getDataMapper() {
        return dataMapper;
    }

    @Autowired
    public void setDataMapper(DataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    @Autowired
    public UsecaseDataMapper getUsecaseDataMaper() {
        return usecaseDataMaper;
    }

    @Autowired
    public void setUsecaseDataMaper(UsecaseDataMapper usecaseDataMaper) {
        this.usecaseDataMaper = usecaseDataMaper;
    }

    @Autowired
    public QuestionMapper getQuestionMapper() {
        return questionMapper;
    }

    @Autowired
    public void setQuestionMapper(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    @Autowired
    public SolutionMapper getSolutionMapper() {
        return solutionMapper;
    }

    @Autowired
    public void setSolutionMapper(SolutionMapper solutionMapper) {
        this.solutionMapper = solutionMapper;
    }

    @Override
    public Map<String, Object> newProject(Project project) {
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

        Project project = projectMapper.selectByName(projectName);

        m.put("DomainName", domainMapper.selectById(project.getDomainId()).getName());
        m.put("BuildTime", project.getBuildTime());
        m.put("CreatorName", sysUserMapper.selectById(project.getCreatorId()).getName());
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
    @Override
    public List<Map<String, Object>> getScenes(String projectName) {

        List<Map<String, Object>> m = new LinkedList<>();

        Long projectId = projectMapper.selectByName(projectName).getId();
        List<Scenario> scenarioList = scenarioMapper.selectByProjectId(projectId);
        List<Usecase> usecaseList = usecaseMapper.selectByProjectId(projectId);

//        索引业务场景数据
        Iterator<Scenario> scenarioIterator = scenarioList.iterator();
        while (scenarioIterator.hasNext()) {
            //            项目中涉及的每一条业务场景数据
            Scenario scenario = scenarioIterator.next();
            //            存储返回List中的Map
            Map<String, Object> temp = new HashMap<>();
            temp.put("ScenarioName", scenario.getName());
            Long scenarioId = scenarioMapper.selectByName(scenario.getName()).getId();

            //            索引业务场景中涉及的Role
            //            一个业务场景可能对应多个Role
            Map<String, Object> relatedRoleMap = new HashMap<>();
            List<ScenarioRole> scenarioRoleList = scenarioRoleMapper.selectByScenarioId(scenarioId);
            for (ScenarioRole scenarioRole : scenarioRoleList) {
                Long roleId = scenarioRole.getRoleId();
                //                将每一个Role的id和name，都放到map中
                relatedRoleMap.put(roleId + "", roleMapper.selectById(roleId).getName());
            }
            temp.put("ScenarioRelatedRole", relatedRoleMap);

            //            索引业务场景中涉及的Data
            //            一个业务场景中，可能涉及多个Data
            Map<String, Object> relateDataMap = new HashMap<>();
            List<ScenarioData> scenarioDataList = scenarioDataMapper.selectByScenarioId(scenarioId);
            for (ScenarioData scenarioData : scenarioDataList) {
                Long dataId = scenarioData.getDataId();
                relateDataMap.put(dataId + "", dataMapper.selectById(dataId).getName());
            }
            temp.put("ScenarioRelatedData", relateDataMap);
        }

//        索引用例场景数据
        Iterator<Usecase> usecaseIterator = usecaseList.iterator();
        while (usecaseIterator.hasNext()) {
            //            项目中涉及的每一条用例数据
            Usecase usecase = usecaseIterator.next();

            //            存储返回List中的Map
            Map<String, Object> temp = new HashMap<>();
            temp.put("UsecaseName", usecase.getName());
            Long usecaseId = usecaseMapper.selectByName(usecase.getName()).getId();

            //            索引用例中涉及的Role
            //            一个用例只对应一个Role
            temp.put("UsecaseRelatedRole", roleMapper.selectById(usecase.getRoleId()));

            //            索引业务场景中涉及的Data
            //            一个业务场景中，可能涉及多个Data
            Map<String, Object> relateDataMap = new HashMap<>();
            List<UsecaseData> usecaseDataList = usecaseDataMaper.selectByUsecaseId(usecaseId);
            for (UsecaseData usecaseData : usecaseDataList) {
                Long dataId = usecaseData.getDataId();
                relateDataMap.put(dataId + "", dataMapper.selectById(dataId).getName());
            }
            temp.put("UsecaseRelatedData", relateDataMap);
        }

        return m;
    }

    //    展示Project中涉及的Role的界面信息
    @Override
    public String getRole(String projectName) {
        List<Role> roleList = roleMapper.selectByProjectId(projectMapper.selectByName(projectName).getId());

        return JSON.toJSONString(roleList);
    }

    //    展示Project中涉及的Data的界面信息
    @Override
    public String getData(String projectName) {
        List<Data> dataList = dataMapper.selectByProjectId(projectMapper.selectByName(projectName).getId());
        return JSON.toJSONString(dataList);
    }

    @Override
    public String getQuestion(String projectName) {
        List<Question> questionList = questionMapper.selectByProjectId(projectMapper.selectByName(projectName).getId());
        return JSON.toJSONString(questionList);
    }

    @Override
    public String getSolution(String projectName) {
        List<Solution> solutionList = solutionMapper.selectByProjectId(projectMapper.selectByName(projectName).getId());
        return JSON.toJSONString(solutionList);
    }
}

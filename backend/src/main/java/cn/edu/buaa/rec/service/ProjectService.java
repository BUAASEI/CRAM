package cn.edu.buaa.rec.service;

import cn.edu.buaa.rec.model.*;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: suruo
 * @Email: csuruo@gmail.com
 * @Date: Created on 上午1:23 2018/02/06
 * @Modified by:
 */

public interface ProjectService {

    Map<String, Object> newProject(Project project);

    /**
     * 查询数据库中是否已经存在该项目名称
     */
    boolean noExist(String name);

    List<Map<String, Object>> allProject();

    List<Map<String, Object>> otherProject(Long userId);

    Map<String, Object> getProjectInfo(String projectName);

    Map<String, Object> applyRole(Map<String, Object> applyRoleInfo);

    Map<String, Object> applyManager(Map<String, Object> applyManagerInfo);

    List<Map<String, Object>> getScenes(String projectName);

    List<Role> getRole(Long projectId);

    List<Data> getData(Long projectId);

    List<Data> getUserDatas(Long projectId, Long userId);


    Project getProjectById(Long projectId);

//    List<Business> getBusinessByIds(List<Long> bIds);
}

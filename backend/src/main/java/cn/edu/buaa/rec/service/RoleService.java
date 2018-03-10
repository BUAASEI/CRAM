package cn.edu.buaa.rec.service;

import cn.edu.buaa.rec.model.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {

    Map<String, Object> newRole(Role role);

    List<Map<String, Object>> getNameAndIdById(List<Long> roleIds);

    List<Long> getIdsByName(List<String> roleNames, Long projectId);

    List<Map<String,Object>> getNameAndIdByProjectId(Long projectId);


}

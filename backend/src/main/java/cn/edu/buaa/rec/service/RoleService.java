package cn.edu.buaa.rec.service;

import java.util.List;
import java.util.Map;

import cn.edu.buaa.rec.model.Role;

public interface RoleService {

    Map<String, Object> newRole(Role role);

    List<Map<String,Object>> getNameAndIdById(List<Long>roleIds);

    List<Long>  getIdsByName(List<String> roleNames,Long projectId);



}

package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.DataMapper;
import cn.edu.buaa.rec.dao.RoleMapper;
import cn.edu.buaa.rec.dao.UsecaseMapper;
import cn.edu.buaa.rec.dao.UsecaseRoleDataMapper;
import cn.edu.buaa.rec.model.Data;
import cn.edu.buaa.rec.model.Role;
import cn.edu.buaa.rec.model.Usecase;
import cn.edu.buaa.rec.model.UsecaseRoleData;
import cn.edu.buaa.rec.service.UsecaseRoleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service("UsecaseRoleDataService")
public class UsecaseRoleDataServiceImpl implements UsecaseRoleDataService {

    @Autowired
    private UsecaseRoleDataMapper usecaseRoleDataMapper;
    @Autowired
    private UsecaseMapper usecaseMapper;
//    @Autowired
//    private RoleMapper roleMapper;
//    @Autowired
//    private DataMapper dataMapper;

    @Override
    public List<UsecaseRoleData> getUsecaseRoleDataByRoleIds(List<Long> rIds) {
        if (rIds == null && rIds.size() == 0) {
            return null;
        }
        List<UsecaseRoleData> uRDList = usecaseRoleDataMapper.selectUsecaseRoleDataByRoleIds(rIds);
        return uRDList;


    }

    @Override
    public List<Map<String, Object>> getUsecaseForm(List<UsecaseRoleData> uRDId) {
        if (uRDId == null) {
            return null;
        }
        List<Map<String, Object>> uRDForms = new LinkedList<>();
        for (UsecaseRoleData urd : uRDId) {
            Map<String, Object> usecaseForm = new HashMap<>();
            Usecase usecase = usecaseMapper.selectById(urd.getUsecaseId());
//            Role role = roleMapper.selectById(urd.getRoleId());
//            Data data = dataMapper.selectById(urd.getDataId());
            usecaseForm.put("id", urd.getId());
            usecaseForm.put("usecaseId", usecase.getId());
            usecaseForm.put("usecaseName", usecase.getName());
            usecaseForm.put("usecaseDes", usecase.getDescription());
//            usecaseForm.put("roleId", role.getId());
//            usecaseForm.put("roleName", role.getName());
//            usecaseForm.put("dataId", data.getId());
//            usecaseForm.put("dataName", data.getName());
            uRDForms.add(usecaseForm);
        }
        return uRDForms;


    }
}

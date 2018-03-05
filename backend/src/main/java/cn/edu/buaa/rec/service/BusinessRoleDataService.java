package cn.edu.buaa.rec.service;

import cn.edu.buaa.rec.model.BusinessRoleData;

import java.util.List;
import java.util.Map;

public interface BusinessRoleDataService {

    List<BusinessRoleData> getBusinessRoleDataByRoleIds(List<Long> rIds);

    List<Map<String, Object>> getBusinessForm(List<BusinessRoleData> bRDId);

}

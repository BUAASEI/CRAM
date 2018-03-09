package cn.edu.buaa.rec.service;

import cn.edu.buaa.rec.model.UsecaseRoleData;

import java.util.List;
import java.util.Map;

public interface UsecaseRoleDataService {
    List<UsecaseRoleData> getUsecaseRoleDataByRoleIds(List<Long> rIds);

    List<Map<String, Object>> getUsecaseForm(List<UsecaseRoleData> bRDId);
}

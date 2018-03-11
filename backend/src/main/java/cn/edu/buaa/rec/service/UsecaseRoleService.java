package cn.edu.buaa.rec.service;

import java.util.List;

public interface UsecaseRoleService {

    int creatUsecaseRole(List<Long> roleIds, Long usecaseId);
//    List<Long> getUsecaseIdsByRoleIds(List<Long> roleIds);
}

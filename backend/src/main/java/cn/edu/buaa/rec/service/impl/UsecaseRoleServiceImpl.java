package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.UsecaseRoleMapper;
import cn.edu.buaa.rec.model.UsecaseRole;
import cn.edu.buaa.rec.service.UsecaseRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UsecaseRoleService")
public class UsecaseRoleServiceImpl implements UsecaseRoleService {


    @Autowired
    private UsecaseRoleMapper usecaseRoleMapper;

//    @Override
//    public List<Long> getUsecaseIdsByRoleIds(List<Long> roleIds) {
//        if (roleIds==null||roleIds.size()==0){
//            return null;
//        }
//
//        return usecaseRoleMapper.selectUsecaseIdsByRoleIds(roleIds);
//    }
    @Override
    public int creatUsecaseRole(List<Long> roleIds, Long usecaseId) {
        if (roleIds == null || roleIds.size() == 0) {
            return -1;
        }
        if (usecaseId < 0 || usecaseId == null) {

            return -2;
        }
        Long usecaseIdMax = usecaseRoleMapper.selectMaxId();
        Long id = ((usecaseIdMax == null) ? 1 : usecaseIdMax + 1);
        for (Long rid : roleIds) {
            UsecaseRole ur = new UsecaseRole();
            ur.setId(id++);
            ur.setRoleId(rid);
            ur.setUsecaseId(usecaseId);
            usecaseRoleMapper.insert(ur);
        }


        return 1;

    }
}

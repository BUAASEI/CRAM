package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.BusinessRoleMapper;
import cn.edu.buaa.rec.service.BusinessRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BusinessRoleService")
public class BusinessRoleServiceImpl implements BusinessRoleService {

    @Autowired
    private BusinessRoleMapper businessRoleMapper;

    @Override
    public List<Long> getBusinessId(List<Long> roleIds) {
        if (roleIds == null) {
            return null;
        }

        List<Long> bIds = businessRoleMapper.selectBusinessId(roleIds);
        return bIds;
    }
}

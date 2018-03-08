package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.RoleMapper;
import cn.edu.buaa.rec.model.Role;
import cn.edu.buaa.rec.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("RoleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public Map<String, Object> newRole(Role role) {
        if (role == null){
            return null;
        }
        String roleName = role.getName();
        Long projectid = role.getProjectId();
        int  count = roleMapper.checkByNameAndProjectId(roleName,projectid);
        Map<String,Object> m = new HashMap<>();
        if (count>0){
            m.put("Msg", "该项目角色已经存在！");
        }else{
            Long roleIdMax = roleMapper.selectMaxId();
            role.setId((roleIdMax == null) ? 1 : roleIdMax + 1);
            role.setBuildTime(new Date());
            int r = roleMapper.insert(role);
            if (r==1){
                m.put("Msg", "新角色创建成功！");
            }else{
                m.put("Msg", "请检查数据格式！");
            }

        }
        return m;
    }

    @Override
    public List<Map<String, Object>> getNameAndIdById(List<Long> roleIds) {
       if (roleIds==null|| roleIds.size()==0){
           return null;
       }

       return roleMapper.selectNameAndIdByIds(roleIds);

    }

    @Override
    public List<Long> getIdsByName(List<String> roleNames,Long projectId) {
       if(roleNames==null|| roleNames.size()==0||projectId==0){
           return null;
       }
       List<Long> roleIds = roleMapper.selectIdsByName(roleNames,projectId);
       return roleIds;
    }
}

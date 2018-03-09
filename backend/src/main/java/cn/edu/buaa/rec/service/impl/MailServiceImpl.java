//package cn.edu.buaa.rec.service.impl;
//
//import cn.edu.buaa.rec.dao.ProjectMapper;
//import cn.edu.buaa.rec.dao.SysUserMapper;
//import cn.edu.buaa.rec.dao.UserProjectManMapper;
//import cn.edu.buaa.rec.dao.UserProjectRoleMapper;
//import cn.edu.buaa.rec.model.SysUser;
//import cn.edu.buaa.rec.model.UserProjectMan;
//import cn.edu.buaa.rec.service.MailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by menghan on 2018/3/6.
// */
//@Service("MailService")
//public class MailServiceImpl implements MailService {
//    @Autowired
//    private UserProjectManMapper userProjectManMapper;
//
//    @Autowired
//    private UserProjectRoleMapper userProjectRoleMapper;
//
//    @Autowired
//    private SysUserMapper sysUserMapper;
//
//    @Autowired
//    private ProjectMapper projectMapper;
//
//
//    @Override
//    public Map<String, Object> getProApplyName(Long userId) {
//        Map<String, Object> map = new HashMap<>();
//        List<Map<String, Object>> manMap = new ArrayList<>();
//        List<Map<String, Object>> roleMap = new ArrayList<>();
//        map.put("manApply", manMap);
//        map.put("roleApply", roleMap);
//        List<Long> projects = userProjectManMapper.selectProjectByUserId(userId);
//        if (projects == null || projects.size() == 0) return map;
//        List<Long> usersMan = userProjectManMapper.selectUserByProjectId(projects);
//        List<Long> usersRole = userProjectRoleMapper.selectUserByProjectId(projects);
//        if (usersMan != null)
//            for (Long id : usersMan) {
//                SysUser sysUser = sysUserMapper.selectById(id);
//                String fDomain = sysUser.getFamiliardomain();
//                String proExp = sysUser.getProjectexp();
//                String name = sysUser.getName();
//                Map<String, Object> submap = new HashMap<>();
//                submap.put("name", name);
//                submap.put("familiarDomain", fDomain);
//                submap.put("projectExp", proExp);
//                List<Long> applyProjects = userProjectManMapper.selectProjectByUserIdWithNoApproved(id);
//                List<String> applyNames = projectMapper.selectNameByIds(applyProjects);
//                submap.put("proApplyName", applyNames);
//                manMap.add(submap);
//            }
//        if (usersRole != null)
//            for (Long id : usersRole) {
//                SysUser sysUser = sysUserMapper.selectById(id);
//                String fDomain = sysUser.getFamiliardomain();
//                String proExp = sysUser.getProjectexp();
//                String name = sysUser.getName();
//                Map<String, Object> submap = new HashMap<>();
//                submap.put("name", name);
//                submap.put("familiarDomain", fDomain);
//                submap.put("projectExp", proExp);
//                List<Long> applyProjects = userProjectRoleMapper.selectProjectByUserIdWithNoApproved(id);
//                List<String> applyNames = projectMapper.selectNameByIds(applyProjects);
//                submap.put("proApplyName", applyNames);
//                manMap.add(submap);
//            }
//        return map;
//    }
//
//    @Override
//    public Map<String, Object> acceptRequest(Long id, String chartName) {
//        if (chartName.equals("role")) {
//            userProjectManMapper.updateApproved(id);
//        } else if (chartName.equals("man")) {
//            userProjectRoleMapper.updateApproved(id);
//        }
//        Map<String, Object> map = new HashMap<>();
//        map.put("msg", "已同意");
//        return map;
//    }
//
//    @Override
//    public Map<String, Object> refuseRequest(Long id, String chartName) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("msg", "已拒绝");
//        return map;
//    }
//
//    @Override
//    public List<Long> getManProjectId(Long userId) {
//        return null;
//    }
//
//    public List<UserProjectMan> getManApplyDetail(List<Long> manProjectId) {
//        return null;
//    }
//}

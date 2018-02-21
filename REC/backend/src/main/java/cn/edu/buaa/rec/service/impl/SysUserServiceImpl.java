package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.SysUserMapper;
import cn.edu.buaa.rec.model.SysUser;
import cn.edu.buaa.rec.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: suruo
 * @Email: csuruo@gmail.com
 * @Date: Created on 上午6:23 2018/02/01
 * @Modified by:
 */
@Service("SysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    //    新建系统用户
    @Override
    public Map<String, Object> newSysUser(SysUser sysUser) {
        //        保存并返回从数据库查询出的结果数据
        Map<String, Object> m = new HashMap<>();
        String sysUserName = sysUser.getName();
        if (noExist(sysUserName)) {
            //            反馈用户id
            //            如果之前存在记录，那么id+1
            //            如果不存在，id设置为1
            Long sysUserIdMax = sysUserMapper.selectMaxId();
            sysUser.setId(( sysUserIdMax== null) ? 1 : sysUserIdMax+1);
            if (sysUserMapper.insert(sysUser) != 1) {
                m.put("Msg", "请检查输入数据格式");
            } else {
                m.put("Msg", "新用户注册成功");
            }
        } else {
            m.put("Msg", "该用户名已存在");
        }
        return m;
    }

    //    修改系统用户信息
    @Override
    public Map<String, Object> modSysUserInfo(SysUser sysUserInfo) {
        Map<String, Object> m = new HashMap<>();
        String sysUserName = sysUserInfo.getName();
        if (noExist(sysUserName)) {
            m.put("Msg", "不存在这个用户诶 @_@ ");
        } else {
            if (sysUserMapper.updateByName(sysUserInfo) != 1) {
                m.put("Msg", "请检查输入数据格式");
            } else {
                m.put("Msg", "用户信息更新成功 @^@ ");
            }
        }
        return m;
    }

//    根据Id，返回相应的SysUser信息
    @Override
    public SysUser selectById(Long sysUserId) {
        return sysUserMapper.selectById(sysUserId);
    }

    //    检查该用户名是否已经存在于数据库中
    @Override
    public boolean noExist(String name) {
        return sysUserMapper.selectByName(name) == null;
    }

    //    通过用户名，查找用户相关信息
    @Override
    public SysUser getByName(String name) {
        return sysUserMapper.selectByName(name);
    }
}

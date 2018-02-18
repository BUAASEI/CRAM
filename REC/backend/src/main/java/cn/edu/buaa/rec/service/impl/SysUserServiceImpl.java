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

//这儿要声明*Service的注解，要不到找不到对应的Service
@Service("SysUserService")
public class SysUserServiceImpl implements SysUserService {

    private SysUserMapper sysUserMapper;

    @Autowired
    public SysUserMapper getSysUserMapper() {
        return sysUserMapper;
    }

    @Autowired
    public void setSysUserMapper(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Override
    public Map<String, Object> newSysUser(SysUser sysUser) {
        //        保存并返回从数据库查询出的结果数据
        Map<String, Object> m = new HashMap<>();
        String sysUserName = sysUser.getName();
        if (noExist(sysUserName)) {
            //            反馈用户id
            //            如果之前存在记录，那么id+1
            //            如果不存在，id设置为1
            Long userIdMax = sysUserMapper.selectMaxId();
            sysUser.setId(((userIdMax==null)? 0 : userIdMax) + 1);

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

    @Override
    public Map<String, Object> modSysUserInfo(SysUser sysUserInfo) {
        Map<String, Object> m = new HashMap<>();
        String sysUserName = sysUserInfo.getName();
        if (noExist(sysUserName)) {
            m.put("Msg", "莫名其妙的错误");
        }else {
            if(sysUserMapper.updateByName(sysUserInfo) != 1){
                m.put("Msg", "请检查输入数据格式");
            }else {
                m.put("Msg", "用户信息更新成功");
            }
        }
        return m;
    }

    //    检查该用户名是否已经存在于数据库中
    @Override
    public boolean noExist(String name) {
//        如果用户名已存在，则返回false
        return (sysUserMapper.selectByName(name) != null) ? false : true;
    }

    //    通过用户名，查找用户相关信息
    @Override
    public SysUser getByName(String name) {
        return sysUserMapper.selectByName(name);
    }
}

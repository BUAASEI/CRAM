package cn.edu.buaa.rec.dao;

import cn.edu.buaa.rec.model.SysUser;

public interface SysUserMapper {

    int insert(SysUser record);

    int insertSelective(SysUser record);

    Long selectMaxId();

    SysUser selectByName(String name);

    int updateByName(SysUser sysUserInfo);

    SysUser selectById(Long id);

    int updateById(SysUser sysUserInfo);

    String selectNameById(Long id);
}
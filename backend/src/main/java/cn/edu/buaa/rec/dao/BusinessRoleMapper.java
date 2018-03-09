package cn.edu.buaa.rec.dao;

import cn.edu.buaa.rec.model.BusinessRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessRoleMapper {

    int insert(BusinessRole record);

    int insertSelective(BusinessRole record);

    List<Long> selectBusinessId(@Param("roleIds") List<Long> roleIds);


}
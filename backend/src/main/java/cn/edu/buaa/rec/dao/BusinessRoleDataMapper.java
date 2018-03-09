package cn.edu.buaa.rec.dao;

import cn.edu.buaa.rec.model.BusinessRoleData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessRoleDataMapper {

    int insert(BusinessRoleData record);

    int insertSelective(BusinessRoleData record);

    List<BusinessRoleData> selectBusinessRoleDataByRoleIds(@Param("rIds") List<Long> rIds);
    List<BusinessRoleData> selectBusinessRoleDataByRoleId(@Param("rId") Long rId);

}
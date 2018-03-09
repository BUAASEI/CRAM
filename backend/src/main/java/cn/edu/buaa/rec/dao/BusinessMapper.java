package cn.edu.buaa.rec.dao;

import cn.edu.buaa.rec.model.Business;
import org.apache.ibatis.annotations.Param;

public interface BusinessMapper {

    int insert(Business record);

    int insertSelective(Business record);

    Business selectBusiness(Long businessId);

    int  checkByNameAndProjectId(@Param("name")String name,@Param("projectId")Long projectid);

    Long selectMaxId();

    int updateById(@Param("business")Business business);


}
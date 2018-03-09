package cn.edu.buaa.rec.dao;

import cn.edu.buaa.rec.model.BusinessData;
import org.apache.ibatis.annotations.Mapper;

@Mapper()
public interface BusinessDataMapper {

    int insert(BusinessData record);

    int insertSelective(BusinessData record);
}
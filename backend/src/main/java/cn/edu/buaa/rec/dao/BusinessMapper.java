package cn.edu.buaa.rec.dao;

import cn.edu.buaa.rec.model.Business;

public interface BusinessMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table business
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    int insert(Business record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table business
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    int insertSelective(Business record);

    Business selectBusiness(Long businessId);



}
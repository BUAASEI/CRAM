package cn.edu.buaa.rec.dao;

import cn.edu.buaa.rec.model.UserProjectMan;

import java.util.List;

public interface UserProjectManMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_project_man
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    int insert(UserProjectMan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_project_man
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    int insertSelective(UserProjectMan record);
    List<UserProjectMan> selectBySysUserId(Long sysUserId);
}
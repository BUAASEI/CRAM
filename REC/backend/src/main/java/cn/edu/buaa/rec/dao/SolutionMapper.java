package cn.edu.buaa.rec.dao;

import cn.edu.buaa.rec.model.Solution;

import java.util.List;

public interface SolutionMapper {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table solution
     *
     * @mbggenerated Sun Feb 18 09:09:16 CST 2018
     */
    int insert(Solution record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table solution
     *
     * @mbggenerated Sun Feb 18 09:09:16 CST 2018
     */
    int insertSelective(Solution record);

    List<Solution> selectByProjectId(Long id);
}
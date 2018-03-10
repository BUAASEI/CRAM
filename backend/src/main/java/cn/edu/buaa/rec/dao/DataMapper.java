package cn.edu.buaa.rec.dao;

import cn.edu.buaa.rec.model.Data;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DataMapper {

    int insert(Data record);

    int insertSelective(Data record);
    Data selectById(Long dataId);

    List<Data> selectByProjectId(Long id);

    List<Data> selectByProjectIdAndUserId(@Param("projectId")Long projectId, @Param("userId")Long userId);

    int checkByNameAndProjectId(@Param("name")String name,@Param("projectId")Long projectid);
    Long selectMaxId();

    List<Long> selectIdsByName(@Param("dataNames")List<String> dataNames,@Param("projectId")Long projectId);

}
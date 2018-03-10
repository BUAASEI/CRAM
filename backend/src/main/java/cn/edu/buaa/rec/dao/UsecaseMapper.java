package cn.edu.buaa.rec.dao;

import cn.edu.buaa.rec.model.Usecase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsecaseMapper {

    int insert(Usecase usecase);
    Usecase selectById(Long uId);

    List<Long> selectUseCases(Long businessId);

    String selectRucmSpecByUseCase(Long useCaseId);

    Long selectMaxId();

    int updateById(Usecase usecase);
    int checkByNameAndProjectId(@Param("name") String name, @Param("projectId")Long projectid);

    List<Usecase> selectByIds(@Param("ids") List<Long> usecaseIds);

    List<Usecase> selectByProjectId(@Param("projectId")Long projectId);
}
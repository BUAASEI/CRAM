package cn.edu.buaa.rec.dao;

import java.util.List;

public interface UsecaseMapper {

    List<Long> selectUseCases(Long businessId);

    String selectRucmSpecByUseCase(Long useCaseId);
}
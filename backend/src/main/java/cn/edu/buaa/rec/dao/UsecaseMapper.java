package cn.edu.buaa.rec.dao;

import cn.edu.buaa.rec.model.Usecase;

import java.util.List;

public interface UsecaseMapper {

    Usecase selectById(Long uId);

    List<Long> selectUseCases(Long businessId);

    String selectRucmSpecByUseCase(Long useCaseId);
}
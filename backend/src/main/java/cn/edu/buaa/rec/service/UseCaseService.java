package cn.edu.buaa.rec.service;

import cn.edu.buaa.rec.model.Usecase;

import java.util.List;
import java.util.Map;

public interface UseCaseService {

    Usecase getUsecaseById(Long uId);

    Map<String, Object> updateUsecase(Usecase usecase);

    Map<String, Object> newUsecase(Usecase usecase);

    Long selectMaxId();
    List<Map<String,Object>> getUsecaseForm(List<Long> usecaseIds);

    Usecase getById(Long usecaseId);

    List<Map<String,Object>> getUsecaseByprojectId(Long projectId);


}

package cn.edu.buaa.rec.service;

import cn.edu.buaa.rec.model.SysUser;
import cn.edu.buaa.rec.model.Usecase;

import java.util.Map;

public interface UseCaseService {

    Usecase getUsecaseById(Long uId);

    Map<String,Object> updateUsecase(Usecase usecase);
}

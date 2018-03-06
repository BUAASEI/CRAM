package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.UsecaseMapper;
import cn.edu.buaa.rec.model.Usecase;
import cn.edu.buaa.rec.service.UseCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UsecaseService")
public class UsecaseServiceImpl implements UseCaseService {
    @Autowired
    private UsecaseMapper usecaseMapper;

    @Override
    public Usecase getUsecaseById(Long uId) {
        if (uId == null){
            return null;
        }
        Usecase u = usecaseMapper.selectById(uId);
        return u;
    }
}

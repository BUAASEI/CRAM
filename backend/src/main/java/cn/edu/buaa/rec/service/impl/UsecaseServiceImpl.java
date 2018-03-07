package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.UsecaseMapper;
import cn.edu.buaa.rec.model.Usecase;
import cn.edu.buaa.rec.service.UseCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    public Map<String, Object> updateUsecase(Usecase usecase) {
        if(usecase==null){
            return null;
        }
        usecase.setUpdateTime(new Date());
        Map<String,Object> m = new HashMap<>();
        int r = usecaseMapper.updateById(usecase);
        System.out.println("r--:"+r);
        if (r != 1) {
            m.put("Msg", "请检查输入数据格式");
        } else {

            m.put("Msg", "信息更新成功 @^@ ");
        }
        return m;

    }
}

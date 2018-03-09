package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.UsecaseMapper;
import cn.edu.buaa.rec.model.Usecase;
import cn.edu.buaa.rec.service.UseCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("UsecaseService")
public class UsecaseServiceImpl implements UseCaseService {
    @Autowired
    private UsecaseMapper usecaseMapper;


    @Override
    public Long selectMaxId() {
        return usecaseMapper.selectMaxId();
    }

    @Override
    public Usecase getUsecaseById(Long uId) {
        if (uId == null) {
            return null;
        }
        Usecase u = usecaseMapper.selectById(uId);
        return u;
    }

    @Override
    public Map<String, Object> updateUsecase(Usecase usecase) {
        if (usecase == null) {
            return null;
        }
        usecase.setUpdateTime(new Date());
        Map<String, Object> m = new HashMap<>();
        int r = usecaseMapper.updateById(usecase);
        System.out.println("r--:" + r);
        if (r != 1) {
            m.put("Msg", "请检查输入数据格式");
        } else {

            m.put("Msg", "信息更新成功 @^@ ");
        }
        return m;

    }


    @Override
    public Map<String, Object> newUsecase(Usecase usecase) {
        if (usecase == null) {
            return null;
        }
        String name = usecase.getName();
        Long projectid = usecase.getProjectId();
        int count = usecaseMapper.checkByNameAndProjectId(name, projectid);
        Map<String, Object> m = new HashMap<>();
        if (count > 0) {
            m.put("Msg", "该项目用例已经存在！");
        } else {
            if (usecase.getId() == null) {
                Long usecaseIdMax = usecaseMapper.selectMaxId();
                Long id = (usecaseIdMax == null) ? 1 : usecaseIdMax + 1;
                usecase.setId(id);
            }

            usecase.setBuildTime(new Date());
            int r = usecaseMapper.insert(usecase);

            if (r != 1) {
                m.put("Msg", "请检查输入数据格式");
            } else {

                m.put("Msg", "新建用例成功 @^@ ");
            }
        }

        return m;
    }
}

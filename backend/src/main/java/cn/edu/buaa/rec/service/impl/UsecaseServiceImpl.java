package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.UsecaseMapper;
import cn.edu.buaa.rec.model.Usecase;
import cn.edu.buaa.rec.service.UseCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("UsecaseService")
public class UsecaseServiceImpl implements UseCaseService {

    @Autowired
    private UsecaseMapper usecaseMapper;

    @Override
    public List<Map<String, Object>> getUsecaseForm(List<Long> usecaseIds) {
        if (usecaseIds==null||usecaseIds.size()==0){
            return null;
        }
        List<Usecase> usecasesList = usecaseMapper.selectByIds(usecaseIds);
        Map<String,Object> usecaseForm = new HashMap<>();
        List<Map<String,Object>> r = new LinkedList<>();
        for (Usecase u:usecasesList){
            usecaseForm.put("usecaseId", u.getId());
            usecaseForm.put("usecaseName", u.getName());
            usecaseForm.put("description",u.getDescription());
            System.out.println();
            r.add(usecaseForm);
            System.out.println("usecase:"+usecaseForm.toString());
        }
        return r;
    }

    @Override
    public Usecase getById(Long usecaseId) {
        if (usecaseId==null||usecaseId<0){
            return null;
        }

        return usecaseMapper.selectById(usecaseId);

    }

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

    @Override
    public List<Map<String, Object>> getUsecaseByprojectId(Long projectId) {
        if (projectId==null){
            return null;
        }

       List<Usecase> us = usecaseMapper.selectByProjectId(projectId);
        List<Map<String,Object>> usform = new LinkedList<>();
        for (Usecase u:us){
            Map<String,Object> f = new HashMap<>();
            f.put("usecaseId", u.getId());
            f.put("usecaseName", u.getName());
            f.put("description",u.getDescription());
            usform.add(f);
        }

        return usform;
    }
}

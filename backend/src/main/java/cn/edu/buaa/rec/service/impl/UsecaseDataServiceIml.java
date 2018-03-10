package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.UsecaseDataMapper;
import cn.edu.buaa.rec.model.UsecaseData;
import cn.edu.buaa.rec.service.UsecaseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UsecaseDataService")
public class UsecaseDataServiceIml implements UsecaseDataService {
    @Autowired
    private UsecaseDataMapper usecaseDataMapper;

    @Override
    public int creatUsecaseData(List<Long> dataIds, Long usecaseId) {
        if (dataIds == null || dataIds.size() == 0) {
            return -1;
        }
        if (usecaseId < 0 || usecaseId == null) {

            return -2;
        }
        Long usecaseIdMax = usecaseDataMapper.selectMaxId();
        Long id = ((usecaseIdMax == null) ? 1 : usecaseIdMax + 1);
        for (Long rid : dataIds) {
            UsecaseData ur = new UsecaseData();
            ur.setId(id++);
            ur.setDataId(rid);
            ur.setUsecaseId(usecaseId);
            usecaseDataMapper.insert(ur);
        }


        return 1;

    }
}

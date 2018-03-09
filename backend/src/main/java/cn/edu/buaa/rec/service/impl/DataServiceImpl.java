package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.DataMapper;
import cn.edu.buaa.rec.model.Data;
import cn.edu.buaa.rec.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("DataService")
public class DataServiceImpl implements DataService {

    @Autowired
    private DataMapper dataMapper;

    @Override
    public Map<String, Object> newData(Data data) {
        if (data == null) {
            return null;
        }
        String name = data.getName();
        Long projectid = data.getProjectId();
        int count = dataMapper.checkByNameAndProjectId(name, projectid);
        Map<String, Object> m = new HashMap<>();
        if (count > 0) {
            m.put("Msg", "该项目数据已经存在！");
        } else {
            Long dataIdMax = dataMapper.selectMaxId();
            data.setId((dataIdMax == null) ? 1 : dataIdMax + 1);
            data.setBuildTime(new Date());
            int r = dataMapper.insert(data);
            if (r == 1) {
                m.put("Msg", "新数据创建成功！");
            } else {
                m.put("Msg", "请检查数据格式！");
            }
        }

        return m;

    }

    @Override
    public List<Long> getIdsByName(List<String> dataNames, Long projectId) {
        if (dataNames == null || dataNames.size() == 0) {
            return null;
        }
        if (projectId == null || projectId < 0) {
            return null;
        }

        List<Long> dataIds = dataMapper.selectIdsByName(dataNames, projectId);
        return dataIds;

    }
}

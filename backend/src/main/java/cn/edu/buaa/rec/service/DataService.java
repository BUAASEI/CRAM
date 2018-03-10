package cn.edu.buaa.rec.service;

import cn.edu.buaa.rec.model.Data;

import java.util.List;
import java.util.Map;

public interface DataService {

    Map<String, Object> newData(Data data);

    List<Long> getIdsByName(List<String> dataNames, Long projectId);

    List<Map<String,Object>> getNameAndIdByprojectId(Long projectId);

    List<List<Map<String, Object>>> listData(long projectId, long userId);

}

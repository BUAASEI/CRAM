package cn.edu.buaa.rec.service;

import cn.edu.buaa.rec.model.Data;

import java.util.List;
import java.util.Map;

public interface DataService {

    Map<String, Object> newData(Data data);

    List<Long> getIdsByName(List<String>dataNames,Long projectId);
}

package cn.edu.buaa.rec.service;

import cn.edu.buaa.rec.model.Business;

import java.util.Map;

public interface BusinessService {

    Map<String, Object> newBusiness(Business business);

    Map<String, Object> updateBusiness(Business business);

}

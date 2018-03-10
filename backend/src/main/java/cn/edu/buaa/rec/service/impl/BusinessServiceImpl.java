package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.BusinessMapper;
import cn.edu.buaa.rec.model.Business;
import cn.edu.buaa.rec.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("BusinessService")
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public Map<String, Object> newBusiness(Business business) {
        if (business == null) {
            return null;
        }
        String name = business.getName();
        Long projectid = business.getProjectId();
        int count = businessMapper.checkByNameAndProjectId(name, projectid);
        Map<String, Object> m = new HashMap<>();
        if (count > 0) {
            m.put("Msg", "该项目用例已经存在！");
        } else {
            Long usecaseIdMax = businessMapper.selectMaxId();
            business.setId((usecaseIdMax == null) ? 1 : usecaseIdMax + 1);
            business.setBuildTime(new Date());
            int r = businessMapper.insert(business);
//        System.out.println("r--:"+r);
            if (r != 1) {
                m.put("Msg", "请检查输入数据格式");
            } else {

                m.put("Msg", "新建场景成功 @^@ ");
            }
        }

        return m;
    }

    @Override
    public Map<String, Object> updateBusiness(Business business) {
        if (business == null) {
            return null;
        }
        business.setUpdateTime(new Date());
        Map<String, Object> m = new HashMap<>();
        int r = businessMapper.updateById(business);
        System.out.println("r--:" + r);
        if (r != 1) {
            m.put("Msg", "请检查输入数据格式");
        } else {

            m.put("Msg", "信息更新成功 @^@ ");
        }
        return m;

    }
}

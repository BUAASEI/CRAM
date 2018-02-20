package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.DomainMapper;
import cn.edu.buaa.rec.model.Domain;
import cn.edu.buaa.rec.service.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: suruo
 * @Email: csuruo@gmail.com
 * @Date: Created on 下午9:18 2018/02/05
 * @Modified by:
 */
@Service("DomainService")
public class DomainServiceImpl implements DomainService {

    private DomainMapper domainMapper;

    @Autowired
    public void setDomainMapper(DomainMapper domainMapper) {
        this.domainMapper = domainMapper;
    }

    @Autowired
    public DomainMapper getDomainMapper() {
        return domainMapper;
    }

    //    新建领域
    @Override
    public Map<String, Object> newDomain(Domain domain) {
        //        保存并返回从数据库查询出的结果数据
        Map<String, Object> m = new HashMap<>();
        String domainName = domain.getName();
        if (noExist(domainName)) {
            Long domainIdMax = domainMapper.selectMaxId();
            domain.setId((domainIdMax == null) ? 1 : domainIdMax + 1);

            if (domainMapper.insert(domain) != 1) {
                m.put("Msg", "请检查输入数据格式");
            } else {
                m.put("Msg", "领域新建成功");
            }
        } else {
            m.put("Msg", "该领域已存在");
        }
        return m;
    }

    //    查询数据库中是否已经存在该领域
    @Override
    public boolean noExist(String name) {
        Domain temp = domainMapper.selectByName(name);

        return (temp == null);
//        return (domainMapper.selectByName(name) != null) ? false : true;
    }

    //    通过名字，返回领域相关信息
    @Override
    public Domain getByName(String name) {
        return domainMapper.selectByName(name);
    }
}

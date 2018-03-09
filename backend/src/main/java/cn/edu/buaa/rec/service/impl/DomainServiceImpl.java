package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.DomainMapper;
import cn.edu.buaa.rec.model.Domain;
import cn.edu.buaa.rec.service.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private DomainMapper domainMapper;

    //    新建领域
    @Override
    public Map<String, Object> newDomain(Domain domain) {
        //        保存并返回从数据库查询出的结果数据
        Map<String, Object> m = new HashMap<>();
        String domainName = domain.getName();

        if (noExist(domainName)) {
            Long domainIdMax = domainMapper.selectMaxId();
            domain.setId((domainIdMax == null) ? 1 : domainIdMax + 1);

            System.out.println("domainIdMax" + domainIdMax + ",domainid:" + domain.getId());
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
        return (domainMapper.selectByName(name) == null);
    }

    @Override
    public Domain selectByName(String name) {
        return domainMapper.selectByName(name);
    }

    @Override
    public List<Domain> getDomain() {
        return domainMapper.selectDomain();
    }

    @Override
    public String getNameById(Long domainId) {
        if (domainId == null || domainId == 0) {
            return null;
        }

        String domainName = domainMapper.selectNameById(domainId);
        return domainName;

    }
}

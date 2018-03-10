package cn.edu.buaa.rec.service;

import cn.edu.buaa.rec.model.Domain;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: suruo
 * @Email: csuruo@gmail.com
 * @Date: Created on 下午9:18 2018/02/05
 * @Modified by:
 */

public interface DomainService {
    Map<String, Object> newDomain(Domain domain);

    //    查询数据库中是否已经存在该领域名称
    boolean noExist(String name);

    Domain selectByName(String name);

    List<Domain> getDomain();

    String getNameById(Long domainId);

}

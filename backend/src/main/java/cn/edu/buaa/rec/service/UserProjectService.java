package cn.edu.buaa.rec.service;

import cn.edu.buaa.rec.model.UserProject;

import java.util.Map;

/**
 * @Description:
 * @Author: suruo
 * @Email: csuruo@gmail.com
 * @Date: Created on 上午1:33 2018/03/09
 * @Modified by:
 */

public interface UserProjectService {
    Map<String, Object> applyProject(UserProject userProject);
}

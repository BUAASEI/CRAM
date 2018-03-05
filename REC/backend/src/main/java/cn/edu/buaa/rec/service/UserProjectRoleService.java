package cn.edu.buaa.rec.service;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: suruo
 * @Email: csuruo@gmail.com
 * @Date: Created on 下午7:25 2018/02/06
 * @Modified by:
 */

public interface UserProjectRoleService {
    List<Map<String,Object>> parProject(Long sysUserId);

    List<Long> getUserRoleId(Long projectId,Long userId);
}

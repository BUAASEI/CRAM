package cn.edu.buaa.rec.controller;

import cn.edu.buaa.rec.service.SysUserService;
import cn.edu.buaa.rec.service.UserProjectManService;
import cn.edu.buaa.rec.service.UserProjectRoleService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: suruo
 * @Email: csuruo@gmail.com
 * @Date: Created on 下午9:57 2018/03/08
 * @Modified by:
 */

@Controller
@RequestMapping("/man")
public class ManController {

    @Autowired
    @Qualifier("SysUserService")
    private SysUserService sysUserService;

    @Autowired
    @Qualifier("UserProjectManService")
    private UserProjectManService userProjectManService;

    @Autowired
    @Qualifier("UserProjectRoleService")
    private UserProjectRoleService userProjectRoleService;

    /**
     * 管理中心，这部分的接口为预加载，返回管理的projectId
     * 查看所管理的项目的项目管理员、角色申请和加入项目申请
     */
    @RequestMapping(value = "/manproinfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<Long, String> getManProjectInfo(@Valid @RequestBody Map<String, Object> userIdInfo) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(userIdInfo);
        Long userId = jsonObject.getLong("UserId");

        return sysUserService.getManProjectId(userId);
    }

    /**
     * 加载每个项目中的申请：管理员、角色
     *
     * @param projectIdInfo
     * @return
     */
    @RequestMapping(value = "/mail/applyinfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, List<Map<String, Object>>> getApplication(@Valid @RequestBody Map<String, Object> projectIdInfo) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(projectIdInfo);
        Long projectId = jsonObject.getLong("projectId");

        return sysUserService.getApply(projectId);
    }

    /**
     * 存储管理员和角色的审批结果
     * 如果前端传过来的是不带L的，默认转化成Integer
     *
     * @param approveInfo：o是申请中，1是同意，2是拒绝
     * @return
     */
    @RequestMapping(value = "/mail/approve")
    @ResponseBody
    public Map<String, Object> updateApproveResult(@Valid @RequestBody Map<String, Object> approveInfo) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(approveInfo);
        Map<String, Object> result = new HashMap<>();

        List<Map<String, Object>> manApprovedResults = (List) jsonObject.get("Man");

        System.out.println("manApprovedResults" + manApprovedResults.toString());
        int manUpdateResult = 0;
        for (Map<String, Object> manApprovedResult : manApprovedResults
                ) {
            Long applyId = Long.parseLong(manApprovedResult.get("applyId").toString());
            Integer isApproved = Integer.parseInt(manApprovedResult.get("isApproved").toString());
            manUpdateResult = userProjectManService.updateByApprove(applyId, isApproved);
        }

        List<Map<String, Object>> roleApprovedResults = (List) jsonObject.get("Role");
        int roleUpdateResult = 0;
        for (Map<String, Object> roleApprovedResult : roleApprovedResults
                ) {
            Long applyId = Long.parseLong(roleApprovedResult.get("applyId").toString());
            Integer isApproved = Integer.parseInt(roleApprovedResult.get("isApproved").toString());
            roleUpdateResult = userProjectRoleService.updateByApprove(applyId, isApproved);
        }

        if (manUpdateResult == 1 && roleUpdateResult == 1) {
            result.put("Msg", "Success");
        } else {
            result.put("Msg", "Error");
        }

        return result;
    }
}

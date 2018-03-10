package cn.edu.buaa.rec.controller;

import cn.edu.buaa.rec.model.UserProject;
import cn.edu.buaa.rec.service.SysUserService;
import cn.edu.buaa.rec.service.UserProjectManService;
import cn.edu.buaa.rec.service.UserProjectRoleService;
import cn.edu.buaa.rec.service.UserProjectService;
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

    @Autowired
    @Qualifier("UserProjectService")
    private UserProjectService userProjectService;

    /**
     * 查看管理的project
     * @return 管理的项目的基本信息：id和name
     * 查看所管理的项目的项目管理员、角色申请和加入项目申请
     */
    @RequestMapping(value = "/proinfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<Long, String> getManProjectInfo(@Valid @RequestBody Map<String, Object> userIdInfo) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(userIdInfo);
        Long userId = jsonObject.getLong("userId");

        return sysUserService.getManProjectId(userId);
    }

    /**
     * 加载某项目中的角色申请信息
     * @param projectIdInfo
     * @return 申请项目的名称、角色画像和申请理由
     */
    @RequestMapping(value = "/role/applyinfo", method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> getRoleApplyInfo(@Valid @RequestBody Map<String, Object> projectIdInfo) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(projectIdInfo);
        Long projectId = jsonObject.getLong("projectId");

        return userProjectRoleService.getApply(projectId);
    }

    /**
     * 加载某项目中的项目管理员申请信息
     *
     * @param projectIdInfo
     * @return 申请项目的名称、角色画像和申请理由
     */
    @RequestMapping(value = "/man/applyinfo", method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> getManApplyInfo(@Valid @RequestBody Map<String, Object> projectIdInfo) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(projectIdInfo);
        Long projectId = jsonObject.getLong("projectId");

        return userProjectManService.getApply(projectId);
    }

    /**
     * 加载某项目中的申请加入项目信息
     * @param projectIdInfo
     * @return
     */
    @RequestMapping(value = "/pro/applyinfo", method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> getProjectApplyInfo(@Valid @RequestBody Map<String, Object> projectIdInfo) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(projectIdInfo);
        Long projectId = jsonObject.getLong("projectId");

        return userProjectService.getApply(projectId);
    }


    /**
     * <废弃接口>
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
     * 将审核结果存储到数据库中
     * @param roleApprovedResults
     * @return
     */
    @RequestMapping(value = "/appre")
    @ResponseBody
    public Map<String, Object> updateApproveResult(@Valid @RequestBody Map<String, Object> roleApprovedResults) {

        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(roleApprovedResults);

        Map<String, Object> result = new HashMap<>();

        Long applyId = jsonObject.getLong("applyId");
        Integer applyResult = jsonObject.getInteger("applyResult");
        String applyType = jsonObject.getString("type");

        int updateResult;

        if(applyType.equals("role")){
            System.out.println(applyId+" "+applyResult);
            updateResult = userProjectRoleService.updateByApprove(applyId, applyResult);
            System.out.println("result:"+updateResult);
        }else if (applyType.equals("man")){
            updateResult = userProjectManService.updateByApprove(applyId, applyResult);
        }else {
            updateResult = userProjectService.updateByApprove(applyId, applyResult);
        }

        if( updateResult == 1){
            result.put("Msg", "处理成功-=-");
        }else {
            result.put("Msg", "请重新处理@@");
        }

        return result;
    }



    /**
     * <废弃接口>
     * 存储管理员角色的审批结果
     * 如果前端传过来的是不带L的，默认转化成Integer
     *
     * @param approveInfo：o是申请中，1是同意，2是拒绝
     * @return
     */
//    @RequestMapping(value = "/mail/approve")
//    @ResponseBody
//    public Map<String, Object> updateApproveResult(@Valid @RequestBody Map<String, Object> approveInfo) {
//        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(approveInfo);
//        Map<String, Object> result = new HashMap<>();
//
//        List<Map<String, Object>> manApprovedResults = (List) jsonObject.get("Man");
//
//        System.out.println("manApprovedResults" + manApprovedResults.toString());
//        int manUpdateResult = 0;
//        for (Map<String, Object> manApprovedResult : manApprovedResults
//                ) {
//            Long applyId = Long.parseLong(manApprovedResult.get("applyId").toString());
//            Integer isApproved = Integer.parseInt(manApprovedResult.get("isApproved").toString());
//            manUpdateResult = userProjectManService.updateByApprove(applyId, isApproved);
//        }
//
//        List<Map<String, Object>> roleApprovedResults = (List) jsonObject.get("Role");
//        int roleUpdateResult = 0;
//        for (Map<String, Object> roleApprovedResult : roleApprovedResults
//                ) {
//            Long applyId = Long.parseLong(roleApprovedResult.get("applyId").toString());
//            Integer isApproved = Integer.parseInt(roleApprovedResult.get("isApproved").toString());
//            roleUpdateResult = userProjectRoleService.updateByApprove(applyId, isApproved);
//        }
//
//        if (manUpdateResult == 1 && roleUpdateResult == 1) {
//            result.put("Msg", "Success");
//        } else {
//            result.put("Msg", "Error");
//        }
//
//        return result;
//    }
}

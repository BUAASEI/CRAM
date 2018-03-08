package cn.edu.buaa.rec.controller;

import cn.edu.buaa.rec.model.Usecase;
import cn.edu.buaa.rec.service.*;

import cn.edu.buaa.rec.service.impl.RuleCheckImpl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: suruo
 * @Email: csuruo@gmail.com
 * @Date: Created on 下午11:40 2018/03/05
 * @Modified by:
 */

@Controller
@RequestMapping("/usecase")
public class UsecaseController {

    @Autowired
    @Qualifier("UsecaseService")
    private UseCaseService usecaseService;
    @Autowired
    @Qualifier("RuleCheckService")
    private RuleCheckImpl ruleCheckService;

    @Autowired
    @Qualifier("RoleService")
    private RoleService roleService;

    @Autowired
    @Qualifier("UsecaseRoleService")
    private UsecaseRoleService usecaseRoleService;

    @Autowired
    @Qualifier("UsecaseDataService")
    private UsecaseDataService usecaseDataService;

    @Autowired
    @Qualifier("DataService")
    private DataService dataService;

    //获取用例信息初始化用例修改界面
    @RequestMapping(value = "/getusecase", method = RequestMethod.POST)
    @ResponseBody
    public Usecase getUsecase(@Valid @RequestBody Map<String, Object> info) {

        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
        Long uId = jsonObject.getLong("usecaseId");
        if (uId == null) {
            return null;
        }
        Usecase usecase = usecaseService.getUsecaseById(uId);
        System.out.println(usecase.toString());
        return usecase;
    }

//    //修改用例信息,
//    @RequestMapping(value="/update",method=RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> update(@Valid @RequestBody Map<String, Object> info){
//        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
//        Long usecaseId = jsonObject.getLong("id");
//        Usecase usecase = new Usecase(usecaseId,jsonObject.getString("name"),jsonObject.getString("description"),jsonObject.getLong("creatorId"),jsonObject.getLong("projectId"),
//                jsonObject.getString("input"),jsonObject.getString("output"),jsonObject.getString("useState"),
//                jsonObject.getString("rucmSoec"));
//
//        System.out.println("usecase:"+usecase);
//        Map<String,Object> m = usecaseService.updateUsecase(usecase);
//
//        System.out.println("m:" +m.toString());
//        return m ;
//
//    }
    //修改用例信息,
    @RequestMapping(value="/updateusecase",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateUsecase(@Valid @RequestBody Map<String, Object> info){
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
        Long usecaseId = jsonObject.getLong("id");
        Usecase usecase = new Usecase(usecaseId,jsonObject.getString("rucmSpec"));
        System.out.println("usecase:"+usecase);
        Map<String,Object> m = usecaseService.updateUsecase(usecase);

        System.out.println("m:" +m.toString());
        return m ;

    }
    //新建用例信息,
    @RequestMapping(value="/new",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> newUsecase(@Valid @RequestBody Map<String, Object> info){
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
        String actor = jsonObject.getString("actor");
        Long projectId = jsonObject.getLong("projectId");

        System.out.println("info"+info.toString());
        List<String> roleNames = Arrays.asList(actor.split(","));
        System.out.println("roleName"+roleNames.toString());
        List<Long>  roleIds= roleService.getIdsByName(roleNames,projectId);

        System.out.println("roIds"+roleIds.toString());
        String dictionary = jsonObject.getString("dictionary");
        List<String> dataNames = Arrays.asList(dictionary.split(","));
        List<Long> dataIds = dataService.getIdsByName(dataNames,projectId);

        Usecase usecase = new Usecase(jsonObject.getString("name"),jsonObject.getString("description"),jsonObject.getLong("projectId"),jsonObject.getLong("creatortId"),jsonObject.getString("brif"));
        System.out.println("usecase:"+usecase);

        Long usecaseIdMax = usecaseService.selectMaxId();
        Long id = (usecaseIdMax == null) ? 1 : usecaseIdMax + 1;
        usecase.setId(id);
        Map<String,Object> m = usecaseService.newUsecase(usecase);

        usecaseRoleService.creatUsecaseRole(roleIds,id);
        usecaseDataService.creatUsecaseData(dataIds,id);

        System.out.println("m:" +m.toString());
        return m ;

    }




    //检测缺陷
    @RequestMapping(value = "/detect", method = RequestMethod.POST)
    @ResponseBody
    public String showCheckResult(@Valid @RequestBody String rucmModel) {
        String result = ruleCheckService.ruleCheckResult(rucmModel);
        return result;
    }

}

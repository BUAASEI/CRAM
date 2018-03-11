package cn.edu.buaa.rec.controller;

import cn.edu.buaa.rec.model.Usecase;
import cn.edu.buaa.rec.service.*;
import cn.edu.buaa.rec.service.impl.RuleCheckImpl;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.*;

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

    /**
     * 预加载某用例实例的全部信息
     *
     * @param info：usecased
     * @return
     */
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

    /**
     * 存储修改后的用例信息
     *
     * @param info
     * @return
     */
    @RequestMapping(value = "/updateusecase", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateUsecase(@Valid @RequestBody Map<String, Object> info) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
        Long usecaseId = jsonObject.getLong("id");
//        Usecase use = usecaseService.getById(usecaseId);
//        String parentName = use.getName();
//        String nowName = jsonObject.getString("name");
        Map<String, Object> m = new HashMap<>();
//        if (parentName.equals(nowName)){
            Usecase usecase = new Usecase(usecaseId, jsonObject.getString("rucmSpec"));
            System.out.println("usecase:" + usecase);
            usecase.setUsestate("1");
            m = usecaseService.updateUsecase(usecase);
//        }else{
//            m = newUsecaseIner(info,"0");
//        }

        System.out.println("m:" + m.toString());
        return m;

    }

    /**
     * 存储新建的用例信息
     *
     * @param info
     * @return
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> newUsecase(@Valid @RequestBody Map<String, Object> info) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
        System.out.println("JsonInfo:"+info.toString());
        Long projectId = jsonObject.getLong("projectId");
        Long primaryActorId = jsonObject.getLong("primaryActorId");
        System.out.println("primaryId:"+primaryActorId.toString());
        JSONArray secondaryActorIds = jsonObject.getJSONArray("secondaryActorIds");
        System.out.println("secondaryId:"+secondaryActorIds.toString());
        List<Long> roleIds = new LinkedList<>();
        roleIds.add(primaryActorId);
        for (int i=0;i<secondaryActorIds.size();i++){
            Long sId = Long.valueOf(secondaryActorIds.get(i).toString());
            roleIds.add(sId);
        }

        JSONArray dictionary = jsonObject.getJSONArray("dictionary");

        System.out.println("datasId"+dictionary.toString());
        List<Long> dataIds = new LinkedList<>();
        for (int i=0;i<dictionary.size();i++){
            Long dId = Long.valueOf(dictionary.get(i).toString());
            dataIds.add(dId);
        }

        Usecase usecase = new Usecase(jsonObject.getString("name"), jsonObject.getString("description"), jsonObject.getLong("projectId"), jsonObject.getLong("creatorId"), jsonObject.getString("rucmSpec"));
        System.out.println("usecase:" + usecase);

        String useState = jsonObject.getString("useState");
        usecase.setUsestate(useState);

        Long usecaseIdMax = usecaseService.selectMaxId();
        Long id = (usecaseIdMax == null) ? 1 : usecaseIdMax + 1;
        usecase.setId(id);
        Map<String, Object> m = usecaseService.newUsecase(usecase);

        System.out.println("useId:" + usecase.getId());
        usecaseRoleService.creatUsecaseRole(roleIds, usecase.getId());
        usecaseDataService.creatUsecaseData(dataIds, usecase.getId());


        System.out.println("m:" + m.toString());
        return m;

    }

//    /**
//     * 存储新建的用例信息
//     *
//     * @param info
//     * @return
//     */
//    @RequestMapping(value = "/new", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> newUsecase(@Valid @RequestBody Map<String, Object> info) {
//        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
//        String actor = jsonObject.getString("actor");
//        Long projectId = jsonObject.getLong("projectId");
//
//        System.out.println("info" + info.toString());
//        List<String> roleNames = Arrays.asList(actor.split(","));
//        System.out.println("roleName" + roleNames.toString());
//        List<Long> roleIds = roleService.getIdsByName(roleNames, projectId);
//
//        System.out.println("roIds" + roleIds.toString());
//        String dictionary = jsonObject.getString("dictionary");
//        List<String> dataNames = Arrays.asList(dictionary.split(","));
//
//        System.out.println("dataNames:"+dataNames.toString());
//        List<Long> dataIds = dataService.getIdsByName(dataNames, projectId);
//
//        System.out.println("dataIds" + dataIds.toString());
//        Usecase usecase = new Usecase(jsonObject.getString("name"), jsonObject.getString("description"), jsonObject.getLong("projectId"), jsonObject.getLong("creatortId"), jsonObject.getString("brif"));
//        System.out.println("usecase:" + usecase);
//
//        Long usecaseIdMax = usecaseService.selectMaxId();
//        Long id = (usecaseIdMax == null) ? 1 : usecaseIdMax + 1;
//        usecase.setId(id);
//        Map<String, Object> m = usecaseService.newUsecase(usecase);
//
//        System.out.println("useId:" + usecase.getId());
//        usecaseRoleService.creatUsecaseRole(roleIds, usecase.getId());
//        usecaseDataService.creatUsecaseData(dataIds, usecase.getId());
//
//        System.out.println("m:" + m.toString());
//        return m;
//
//    }

    /**
     * 检测当前用例存在的缺陷
     *
     * @param rucmModel
     * @return
     */
    @RequestMapping(value = "/detect", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> showCheckResult(@Valid @RequestBody String rucmModel) {
        System.out.println("rucmModel:\n" + rucmModel);
        String result = ruleCheckService.ruleCheckResult(rucmModel);
        Map<String,Object> m = new HashMap<>();
        m.put("result",result);
        return m;
    }


    //内部创建Usecase的方法，便于修改uSeState

    public Map<String, Object> newUsecaseIner(Map<String, Object> info,String useState) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
        System.out.println("JsonInfo:"+info.toString());
        Long projectId = jsonObject.getLong("projectId");
        Long primaryActorId = jsonObject.getLong("primaryActorId");
        System.out.println("primaryId:"+primaryActorId.toString());

        JSONArray secondaryActorIds = jsonObject.getJSONArray("secondaryActorIds");
        System.out.println("secondaryId:"+secondaryActorIds.toString());
        List<Long> roleIds = new LinkedList<>();
        roleIds.add(primaryActorId);
        for (int i=0;i<secondaryActorIds.size();i++){
            Long sId = Long.valueOf(secondaryActorIds.get(i).toString());
            roleIds.add(sId);
        }

        JSONArray dictionary = jsonObject.getJSONArray("dictionary");

        List<Long> dataIds = new LinkedList<>();
        for (int i=0;i<dictionary.size();i++){
            Long dId = Long.valueOf(dictionary.get(i).toString());
            dataIds.add(dId);
        }

        Usecase usecase = new Usecase(jsonObject.getString("name"), jsonObject.getString("description"), jsonObject.getLong("projectId"), jsonObject.getLong("creatorId"), jsonObject.getString("rucmSpec"));
        System.out.println("usecase:" + usecase);

        usecase.setUsestate("0");

        Long usecaseIdMax = usecaseService.selectMaxId();
        Long id = (usecaseIdMax == null) ? 1 : usecaseIdMax + 1;
        usecase.setId(id);
        Map<String, Object> m = usecaseService.newUsecase(usecase);

        System.out.println("useId:" + usecase.getId());
        usecaseRoleService.creatUsecaseRole(roleIds, usecase.getId());
        usecaseDataService.creatUsecaseData(dataIds, usecase.getId());

        System.out.println("m:" + m.toString());
        return m;

    }

    @RequestMapping("getUsecaseByProjectId")
    @ResponseBody
    public Map<String,Object> getUsecaseByprojectId(@Valid@RequestBody Map<String,Object> info){

        return null;

    }



}

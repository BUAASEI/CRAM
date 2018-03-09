package cn.edu.buaa.rec.controller;

import cn.edu.buaa.rec.model.Business;
import cn.edu.buaa.rec.service.BusinessService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

/**
 * @Description:
 * @Author: suruo
 * @Email: csuruo@gmail.com
 * @Date: Created on 下午11:40 2018/03/05
 * @Modified by:
 */

@Controller
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    @Qualifier("BusinessService")
    private BusinessService businessService;

    /**
     * 新建业务场景
     *
     * @param info
     * @return
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> newBusiness(@Valid @RequestBody Map<String, Object> info) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
        // Long businessId = jsonObject.getLong("id");
        Business business = new Business(jsonObject.getString("name"), jsonObject.getString("description"), jsonObject.getLong("creatorId"), jsonObject.getLong("projectId"),
                jsonObject.getString("priority"), jsonObject.getString("preCOndition"), jsonObject.getString("postCondition"), jsonObject.getString("input"), jsonObject.getString("output"), jsonObject.getString("useState"),
                jsonObject.getString("data"), jsonObject.getString("role"), jsonObject.getString("include"), jsonObject.getString("des"));
        Map<String, Object> m = businessService.newBusiness(business);

        System.out.println("m:" + m.toString());
        return m;

    }

    /**
     * 修改场景信息
     *
     * @param info
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateBusiness(@Valid @RequestBody Map<String, Object> info) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
        System.out.println("business" + info.toString());
        Business business = new Business(jsonObject.getLong("id"), jsonObject.getString("name"), jsonObject.getString("description"), jsonObject.getLong("creatorId"), jsonObject.getLong("projectId"),
                jsonObject.getString("priority"), jsonObject.getString("preCondition"), jsonObject.getString("postCondition"), jsonObject.getString("input"), jsonObject.getString("output"), jsonObject.getString("useState"),
                jsonObject.getString("data"), jsonObject.getString("role"), jsonObject.getString("include"), jsonObject.getString("des"), jsonObject.getString("flow"));
        Map<String, Object> m = businessService.updateBusiness(business);

        System.out.println("m:" + m.toString());
        return m;

    }
//    //修改场景信息,
//    @RequestMapping(value="/update",method=RequestMethod.POST)
//    public Map<String, Object> updateBusiness(Business business){
//        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
//        System.out.println("business"+info.toString());
//        Business business = (Business) jsonObject.get("business");
//        System.out.println("business"+business.toString());
////        Business business = new Business(jsonObject.getLong("id"),jsonObject.getString("name"),jsonObject.getString("description"),jsonObject.getLong("creatorId"),jsonObject.getLong("projectId"),
////                jsonObject.getString("priority"),jsonObject.getString("preCondition"),jsonObject.getString("postCondition"),jsonObject.getString("input"),jsonObject.getString("output"),jsonObject.getString("useState"),
////                jsonObject.getString("data"),jsonObject.getString("role"),jsonObject.getString("include"),jsonObject.getString("des"),jsonObject.getString("flow"));
//        Map<String,Object> m = businessService.updateBusiness(business);
//
//        System.out.println("m:" +m.toString());
//        return m ;
//
//    }

}

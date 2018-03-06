package cn.edu.buaa.rec.controller;

import cn.edu.buaa.rec.model.Usecase;
import cn.edu.buaa.rec.service.UseCaseService;
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
        return usecase;
    }

    //检测缺陷
    @RequestMapping(value = "/detect", method = RequestMethod.POST)
    @ResponseBody
    public String showCheckResult(@Valid @RequestBody String rucmModel) {
        String result = ruleCheckService.ruleCheckResult(rucmModel);
        return result;
    }

}

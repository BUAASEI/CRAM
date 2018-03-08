package cn.edu.buaa.rec.controller;

import cn.edu.buaa.rec.model.Data;
import cn.edu.buaa.rec.model.SysUser;
import cn.edu.buaa.rec.service.DataService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/data")
public class DataController {

    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);
    @Autowired
    @Qualifier("DataService")
    private DataService dataService;

    //   新建数据
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> register(@Valid @RequestBody Map<String, Object> dataInfo) {

        System.out.println(dataInfo);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(dataInfo);

        Long projectId = jsonObject.getLong("projectId");
        System.out.println("projectId"+projectId);
        Data data = new Data(jsonObject.getString("name"), jsonObject.getString("description"),  jsonObject.getLong("creatorId"),jsonObject.getLong("projectId"),jsonObject.getString("useState"));

        return dataService.newData(data);
    }
}

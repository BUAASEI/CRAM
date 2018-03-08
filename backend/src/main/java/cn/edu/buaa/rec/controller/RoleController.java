package cn.edu.buaa.rec.controller;

import cn.edu.buaa.rec.model.Role;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.edu.buaa.rec.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    @Qualifier("RoleService")
    private RoleService roleService;

    //    新建角色
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> newRole(@Valid @RequestBody Map<String, Object> roleInfo) {

        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(roleInfo);
        Role role = new Role(jsonObject.getString("roleName"), jsonObject.getString("description"), jsonObject.getLong("creatorId"),jsonObject.getLong("projectId"),jsonObject.getString("input"),jsonObject.getString("output"),jsonObject.getString("behavior"),jsonObject.getString("useState"));

        System.out.println(role.toString());
        return roleService.newRole(role);
    }

}

package cn.edu.buaa.rec.controller;

import cn.edu.buaa.rec.service.QuestionService;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: suruo
 * @Email: csuruo@gmail.com
 * @Date: Created on 上午12:29 2018/01/22
 * @Modified by:
 */

@RestController
@RequestMapping("/")
public class DefaultController{

    private QuestionService questionService;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        System.out.println("hello world!");
        return "hello 你妹啊。";
    }

    @RequestMapping("/home")
    public String index(Model model) {
        System.out.println("hello index");
        return "index.html";
    }
}

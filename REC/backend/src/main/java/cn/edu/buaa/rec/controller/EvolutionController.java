package cn.edu.buaa.rec.controller;

import cn.edu.buaa.rec.service.EvolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: suruo
 * @Email: csuruo@gmail.com
 * @Date: Created on 下午4:52 2018/02/07
 * @Modified by:
 */

@RestController
@RequestMapping("/evolution")
public class EvolutionController {
    @Autowired
    private EvolutionService evolutionService;

    @Autowired
    public EvolutionService getEvolutionService() {
        return evolutionService;
    }

    @Autowired
    public void setEvolutionService(EvolutionService evolutionService) {
        this.evolutionService = evolutionService;
    }

    @RequestMapping("/")
    @ResponseBody
    public boolean newQuestion() { return true;}

//    演化场景
    @RequestMapping(value = "/sceneevo", method = {RequestMethod.GET})
    @ResponseBody
    public String evolutScene(@RequestParam("id") long questionId) {

        return "true";
    }

//    查看演化历史
    @RequestMapping(value = "/scenehis", method = {RequestMethod.GET})
    @ResponseBody
    public String getSceneEvolutionHistory(@RequestParam("id") long questionId) {

        return "true";
    }

}
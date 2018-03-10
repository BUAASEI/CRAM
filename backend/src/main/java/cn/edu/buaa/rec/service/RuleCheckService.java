package cn.edu.buaa.rec.service;

import cn.edu.buaa.rec.model.RucmModel;

import java.util.Map;

/**
 * Created by menghan on 2018/2/27.
 */
public interface RuleCheckService {
    public Map<String, Object> rule1Check(RucmModel content);

    public Map<String, Object> rule5And6Check(RucmModel content);

    public Map<String, Object> rule3Check(RucmModel content);

    public Map<String, Object> rule6Check(String content);

    public Map<String, Object> rule7Check(RucmModel content);

    public Map<String,Object> rule9Check(RucmModel content);

    public Map<String, Object> rule11And12Check(RucmModel content);



}

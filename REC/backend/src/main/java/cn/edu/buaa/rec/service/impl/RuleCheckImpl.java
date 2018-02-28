package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.model.AlternativeFlow;
import cn.edu.buaa.rec.model.BasicFlow;
import cn.edu.buaa.rec.model.Flow;
import cn.edu.buaa.rec.model.RucmModel;
import cn.edu.buaa.rec.service.RuleCheckService;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by menghan on 2018/2/27.
 */
public class RuleCheckImpl implements RuleCheckService {

    public String ruleCheckResult(RucmModel rucmModel){
        return checkResult(rucmModel);
    }
    public String checkResult(RucmModel rucmModel ){
        //transfer rucmModel to object
        StringBuilder sb = new StringBuilder();
        RucmModel model = rucmModel;
        //rule1
        Map<String,Object> rule1Map = rule1Check(model);
        if((Integer)rule1Map.get("status")==1)
            sb.append(rule1Map.get("result"));
        //rule3
        Map<String,Object> rule3Map = rule3Check(model);
        if((Integer)rule3Map.get("status")==1)
            sb.append(rule3Map.get("result"));
        //rule7
        Map<String,Object> rule7Map = rule7Check(model);
        if((Integer)rule7Map.get("status")==1)
            sb.append(rule7Map.get("result"));
        return sb.toString();
    }

    @Override
    public Map<String, Object> rule1Check(RucmModel content) {
        Map<String,Object> map = new HashMap<>();
        if(content.getBasicFlow().getPostCondition()==null||content.getBasicFlow().getPostCondition().length()==0){
            map.put("status",1);
            map.put("result","BasicFLow触发了规则1：控制流必须有起始和退出节点\n");
        }
        for(int i = 1;i <= content.getAlternativeFlows().size();i++ ){
            if(content.getAlternativeFlows().get(i-1).getPostCondition()==null||content.getAlternativeFlows().get(i-1).getPostCondition().length()==0){
                map.put("status",1);
                String errorInfo="AlternativeFLow-"+i+"触发了规则1：控制流必须有起始和退出节点\n";
                if(map.get("result")!=null){
                    String str = (String)map.get("result");
                    str+= errorInfo;
                    map.put("result",str);
                }else map.put("result",errorInfo);
            }
        }
        if(map.get("status")==null) map.put("status",0);
        return map;
    }

    @Override
    public Map<String, Object> rule3Check(RucmModel rucmModel) {
        Map<String,Object> map = new HashMap<>();
        BasicFlow basicFlow= rucmModel.getBasicFlow();
        List<String> steps = basicFlow.getSteps();
        for(int i = 1;i<=steps.size();i++){
            if(steps.get(i-1).contains("VALIDATE THAT")){
                int j = 0;
                for(;j<rucmModel.getAlternativeFlows().size();j++){
                    String rfs = rucmModel.getAlternativeFlows().get(j).getRfs();
                    if(isNumeric(rfs)&&Integer.parseInt(rfs)==i) break;
                }
                if(j==rucmModel.getAlternativeFlows().size()||rucmModel.getAlternativeFlows().size()==0){
                    map.put("status",1);
                    String errorInfo = "BasicFlow-步骤"+i+"触发了规则3:没有对应的分支节点\n";
                    if(map.get("result")!=null){
                        String str = (String)map.get("result");
                        str+= errorInfo;
                        map.put("result",str);
                    }else map.put("result",errorInfo);
                }

            }
        }
        if(map.get("status")==null) map.put("status",0);
        return map;
    }

    @Override
    public Map<String, Object> rule6Check(String content) {
        return null;
    }

    @Override
    public Map<String, Object> rule7Check(RucmModel rucmModel) {
        Map<String,Object> map = new HashMap<>();
        BasicFlow basicFlow= rucmModel.getBasicFlow();
        List<String> steps = basicFlow.getSteps();
        for(int i=1;i<=steps.size();i++){
            String step = steps.get(i-1);
            if(step.contains("ACTION")){
                String errorInfo = "BasicFlow-步骤"+i+"触发了规则7：动作节点的操作和对象不为空\n";
                if(!step.contains("OP")){
                    map.put("status",1);
                    if(map.get("result")!=null){
                        String str = (String)map.get("result");
                        str+= errorInfo;
                        map.put("result",str);
                    }else map.put("result",errorInfo);
                }
                else{
                    String operation = step.substring(step.indexOf("ACTION")+6,step.indexOf("OP")+2).trim();
                    String object = step.substring(step.indexOf("OP")+2).trim();
                    if(operation.length()==0||object.length()==0){
                        map.put("status",1);
                        if(map.get("result")!=null){
                            String str = (String)map.get("result");
                            str+= errorInfo;
                            map.put("result",str);
                        }else map.put("result",errorInfo);
                    }
                }
            }
        }
        if(map.get("status")==null) map.put("status",0);
        return map;
    }
    public static boolean isNumeric(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
}

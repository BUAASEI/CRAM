package cn.edu.buaa.rec.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by menghan on 2018/2/27.
 */
public class BasicFlow extends Flow{
    public BasicFlow(JSONObject object){
        JSONArray bfSteps = object.getJSONArray("Steps");
        this.steps = new ArrayList<>();
        for(int i=0;i<bfSteps.size();i++){
            steps.add((String)bfSteps.getJSONObject(i).get("StepContent"));
        }
        this.setPostCondition((String)object.get("PostCondition"));
    }

}

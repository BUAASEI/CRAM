package cn.edu.buaa.rec.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

/**
 * Created by 苏若 on 2018/2/27.
 */
public class BasicFlow extends Flow {

    public BasicFlow() {
    }

    public BasicFlow(JSONObject object) {
        JSONArray bfSteps = object.getJSONArray("data");
        this.steps = new ArrayList<>();
        for (int i = 0; i < bfSteps.size(); i++) {
            steps.add((String) bfSteps.get(i));
        }
        this.setPostCondition((String) object.get("PostCondition"));
    }

}

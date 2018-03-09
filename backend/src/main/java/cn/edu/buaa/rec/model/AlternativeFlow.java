package cn.edu.buaa.rec.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

/**
 * Created by 苏若 on 2018/2/27.
 */
public class AlternativeFlow extends Flow {
    private String rfs;
    private String guardCondition;
    private String name;

    public AlternativeFlow() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRfs() {
        return rfs;
    }

    public void setRfs(String rfs) {
        this.rfs = rfs;
    }

    public String getGuardCondition() {
        return guardCondition;
    }

    public void setGuardCondition(String guardCondition) {
        this.guardCondition = guardCondition;
    }

    public AlternativeFlow(JSONObject flow, String name) {
        this.name = name;
        this.steps = new ArrayList<>();
        JSONArray rucmSteps = flow.getJSONArray("data");
        for (int i = 0; i < rucmSteps.size(); i++) {
            steps.add((String) rucmSteps.get(i));
        }
        if (name.equals("SpecificAlternativeFlow") || name.equals("BoundedAlternativeFlow")) {
            this.setRfs((String) flow.get("RFS"));
        } else if (name.equals("GlobalAlternativeFlow"))
            this.setGuardCondition((String) flow.get("GuardCondition"));
    }

}

package cn.edu.buaa.rec.model;

import java.util.List;

/**
 * Created by suruo on 2018/2/27.
 */
public class Flow {
    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }


    protected List<String> steps;

    public String getPostCondition() {
        return postCondition;
    }

    public void setPostCondition(String postCondition) {
        this.postCondition = postCondition;
    }

    protected String postCondition;

    public Flow() {
    }
}

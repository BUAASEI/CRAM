package cn.edu.buaa.rec.model;

import java.util.List;
import java.util.Map;

/**
 * Created by menghan on 2018/2/27.
 */
public class AlternativeFlow extends Flow{
    private String rfs;
    private String guardCondition;

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
}

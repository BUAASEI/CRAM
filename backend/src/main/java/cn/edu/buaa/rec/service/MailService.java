package cn.edu.buaa.rec.service;

import java.util.Map;

/**
 * Created by menghan on 2018/3/6.
 */
public interface MailService {
    public Map<String,Object> getProApplyName(Long userId);

    public Map<String,Object> acceptRequest(Long id,String chartName);

    public Map<String,Object> refuseRequest(Long id,String chartName);

}

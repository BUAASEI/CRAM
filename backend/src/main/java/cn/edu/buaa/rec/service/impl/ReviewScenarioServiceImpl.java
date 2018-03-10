package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.ReviewScenarioMapper;
import cn.edu.buaa.rec.model.ReviewScenario;
import cn.edu.buaa.rec.service.ReviewScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("ReviewScenarioService")
public class ReviewScenarioServiceImpl implements ReviewScenarioService {

    @Autowired
    private ReviewScenarioMapper reviewScenarioMapper;
    @Override
    public Map<String,Object> newReviewScnario(ReviewScenario reviewScenario) {
        if (reviewScenario==null){
            return null;
        }

        Long reviewIdMax = reviewScenarioMapper.selectMaxId();
        reviewScenario.setId((reviewIdMax == null) ? 1 : reviewIdMax + 1);
        Map<String,Object> m =new HashMap<>();
        if (reviewScenarioMapper.insert(reviewScenario)!=1){
            m.put("Msg", "请检查输入数据格式");
        } else {
            m.put("Msg", "成功");
        }
        return m;
    }
}

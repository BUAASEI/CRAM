package cn.edu.buaa.rec.controller;

import cn.edu.buaa.rec.model.Review;
import cn.edu.buaa.rec.model.ReviewScenario;
import cn.edu.buaa.rec.service.ReviewScenarioService;
import cn.edu.buaa.rec.service.ReviewService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewScenarioService reviewScenarioService;
    @RequestMapping("/new")
    @ResponseBody
    public Map<String, Object> newReivew(@RequestBody Map<String,Object>info){

        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(info);
       int ts =  jsonObject.getInteger("typeScenario");
       Long scId= jsonObject.getLong("scenarioId");
       Review review = new Review(jsonObject.getString("creatorName"), jsonObject.getString("content"),ts);

        Long reviewIdMax = reviewService.selectMaxId();
        review.setId((reviewIdMax == null) ? 1 : reviewIdMax + 1);
        Map<String,Object> m = reviewService.newReview(review);

        ReviewScenario rs = new ReviewScenario();
        rs.setReviewId(review.getId());
        if (ts == 1){//用例评论
            rs.setUsecaseId(scId);
        }else if (ts == 2){//场景评论
            rs.setBusinessId(scId);

        }

        reviewScenarioService.newReviewScnario(rs);

        return m;
    }
}

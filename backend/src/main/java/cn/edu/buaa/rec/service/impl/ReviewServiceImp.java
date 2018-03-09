package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.ReviewMapper;
import cn.edu.buaa.rec.model.Review;
import cn.edu.buaa.rec.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("ReviewServie")
public class ReviewServiceImp implements ReviewService{
    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public Long selectMaxId() {
        Long reviewIdMax = reviewMapper.selectMaxId();
        return (reviewIdMax == null) ? 1 : reviewIdMax + 1;
    }

    @Override
    public Map<String, Object> newReview(Review review) {
        if (review==null){
            return null;
        }

        review.setTime(new Date());
        if (review.getId()==null){
            Long reviewIdMax = reviewMapper.selectMaxId();
            review.setId((reviewIdMax == null) ? 1 : reviewIdMax + 1);
        }

        Map<String,Object> m =new HashMap<>();
        if (reviewMapper.insert(review)!=1){
            m.put("Msg", "请检查输入数据格式");
        } else {
            m.put("Msg", "评论成功");
        }

        return m;
    }
}

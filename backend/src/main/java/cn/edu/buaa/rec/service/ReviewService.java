package cn.edu.buaa.rec.service;

import cn.edu.buaa.rec.model.Review;

import java.util.Map;

public interface ReviewService {
    Map<String,Object> newReview(Review review);
    Long selectMaxId();
}

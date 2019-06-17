package review.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import review.bean.Review;
import review.common.exception.CommonException;
import review.service.ReviewService;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class ReviewController extends BaseController{
    @Autowired
    private ReviewService reviewService;

    //获取个人评论列表
    @PostMapping("/lists")
    public Map<String,Object> profileGetList (final ServletRequest req){
        Object claims = req.getAttribute("claims");
        if(claims == null) {
            throw new CommonException(401,"用户token验证失败，请重新登录");
        }

        Map<String,Object> info = new HashMap<String,Object>();
        Object userid = req.getAttribute("userid");
        List<Map<String, Object>> review =reviewService.profileGetReviewList(Integer.parseInt(String.valueOf(userid)));
        info.put("review",review);
        return handleResponseData(0, info);

    }


    //添加一条评论
    @PostMapping("/create")
    public Map<String, Object> addReview(final ServletRequest req,@RequestParam("movieid") int movieid, @RequestParam("content") String content,@RequestParam("star") Double star){
        Object claims = req.getAttribute("claims");
        if(claims == null) {
            throw new CommonException(401,"用户token验证失败，请重新登录");
        }
        Object userid = req.getAttribute("userid");
        Review review = reviewService.creatReview(Integer.parseInt(String.valueOf(userid)),movieid,content,star);

        return handleResponseData(0, review);
    }

    /**
     * 获取某电影的评论列表
     * @param movieid
     * @return
     */
    @RequestMapping("/movieReviewLists")
    public Map<String,Object> getLikeStatus(@RequestParam("movieid") int movieid){
        Map<String,Object> listreview = reviewService.reviewList(movieid);
        return handleResponseData(0, listreview);
    }
}

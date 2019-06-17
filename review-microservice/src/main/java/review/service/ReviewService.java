package review.service;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import review.bean.Movie;
import review.bean.Review;
import review.common.exception.CommonException;
import review.repository.ReviewRepository;

import java.io.IOException;
import java.util.*;

@Transactional
@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private RestTemplate restTemplate;

    //根据userid查找到个人评论列表
    public List<Map<String, Object>> profileGetReviewList(int userid){
        List<Review> reviews =null;
        reviews = reviewRepository.findByUseridOrderByReviewidDesc(userid);

        List<Map<String, Object>> reviewList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i< reviews.size();i++){

            int movieid = reviews.get(i).getMovieid();
            // 调取 movie-microservice 服务的接口获取电影详情接口
            // RestTemplate restTemplate = new RestTemplate();
            String url = "http://movie-microservice/description"; // URL 构造格式: http://”+注册在Eureka上的服务名称+调用的接口名
            MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
            paramMap.add("movieid", movieid);
            // 使用postForObject请求接口
            String movieInfo = restTemplate.postForObject(url, paramMap, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode jsonRes = objectMapper.readTree(movieInfo);
                if (0 == jsonRes.findPath("code").asInt()) {
                    JsonNode data = jsonRes.findPath("data");
                    JsonNode movieNode = data.findPath("moviedescription");
                    List<Movie> movies = new ArrayList<Movie>();
                    if (movieNode.isArray()) {
                        movies = JSON.parseArray(movieNode.toString(), Movie.class); //把json转换为Java list 
                    }
                    if (movies != null && movies.size() > 0) {
                        Map<String, Object> review = new HashMap<String,Object>();
                        review.put("reviewid", reviews.get(i).getReviewid());
                        review.put("movieid",reviews.get(i).getMovieid());
                        review.put("userid", reviews.get(i).getUserid());
                        review.put("movie",movies.get(0));
                        reviewList.add(review);
                    }
                }
            }catch (IOException e) {
                e.printStackTrace();
            }

        }
        return reviewList;
    }

    //添加一条评论
    public Review creatReview( int userid,int movieid, String content,Double star) {
        Review review = new Review();
        review.setUserid(userid);
        review.setMovieid(movieid);
        review.setContent(content);
        review.setStar(star);
        review.setReviewtime(new Date());
        return reviewRepository.save(review);
    }

    /*
     * 电影详情界面评论列表
     */
    public Map<String,Object> reviewList(int movieid){
        List<Review> list = null;
        list = reviewRepository.findByMovieidOrderByReviewidDesc(movieid);
        List<Map<String, Object>> reviewList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i< list.size();i++){

            int uid = list.get(i).getUserid();

            // 调取 user-microservice 服务的接口获取评论用户的用户名
            // RestTemplate restTemplate = new RestTemplate();
            String url = "http://user-microservice/getUsername"; // URL 构造格式: http://”+注册在Eureka上的服务名称+调用的接口名
            MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
            paramMap.add("userid", uid);
            // 使用postForObject请求接口
            String userInfo = restTemplate.postForObject(url, paramMap, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode jsonRes = objectMapper.readTree(userInfo);
                if (0 == jsonRes.findPath("code").asInt()) {
                    String username = jsonRes.findPath("data").asText();
                    Map<String, Object> review = new HashMap<String,Object>();
                    review.put("star", list.get(i).getStar());
                    review.put("content",list.get(i).getContent());
                    review.put("username", username);
                    review.put("reviewtime",list.get(i).getReviewtime());
                    reviewList.add(review);
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        Map<String,Object> info = new HashMap<String,Object>();
        info.put("reviewList",reviewList);
        return info;
    }
}

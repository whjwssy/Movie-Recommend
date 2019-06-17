package collect.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import collect.bean.Rectab;
import collect.common.exception.CommonException;
import collect.repository.RectabRepository;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.util.LinkedMultiValueMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.alibaba.fastjson.JSON;
import collect.bean.Movie;
import java.io.IOException;
import java.util.*;

@Transactional
@Service
public class CollectService {
    @Autowired
    private RectabRepository rectabRepository;

    @Autowired
    private RestTemplate restTemplate;

    //根据userid查找到喜欢列表
    public List<Map<String, Object>> profileGetRectabList(int userid){
        List<Rectab> rectab = null;
        rectab = rectabRepository.findByUseridOrderByRectabidDesc(userid);
        List<Map<String, Object>> collectList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i< rectab.size();i++){

            int movieid = rectab.get(i).getMovieid();
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
                        Map<String, Object> collect = new HashMap<String,Object>();
                        collect.put("rectabid", rectab.get(i).getRectabid());
                        collect.put("movieid",rectab.get(i).getMovieid());
                        collect.put("userid", rectab.get(i).getUserid());
                        collect.put("movie",movies.get(0));
                        collectList.add(collect);
                    }
                }
            }catch (IOException e) {
                e.printStackTrace();
            }

        }
        return collectList;
    }

    //添加一条喜欢
    public Rectab createRectab(int userid,int movieid){
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
                if (movies == null && movies.size() <= 0) {
                    throw new CommonException(401, "你所收藏的电影不存在");
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        Rectab rectab =new Rectab();
        rectab.setUserid(userid);
        rectab.setMovieid(movieid);
        return rectabRepository.save(rectab);
    }

    /**
     * 获取对某个电影的喜欢状态
     * @param movieid
     * @return
     */
    public Boolean MovieLikeStatus(int movieid, int userid) {
        Boolean status = false;
        List<Rectab> info = rectabRepository.findByMovieidAndUserid(movieid, userid);
        if (info != null && info.size() != 0) {
            status = true;
        }
        return status;
    }
}

package recommend.controller;

import recommend.bean.Alstab;
import recommend.common.exception.CommonException;

import recommend.service.AlstabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class RecommendController extends BaseController{

    @Autowired
    private AlstabService alstabService;

    /**
     *
     * @return
     */
    @PostMapping("/movieLists")
    public Map<String, Object> getRecommend(final ServletRequest req) {
        final HttpServletRequest request = (HttpServletRequest) req;
        Object claims = req.getAttribute("claims");

        if (claims == null) {
            throw new CommonException(401, "用户token验证失败，请重新登录");
        }
        Object userid = req.getAttribute("userid");
        Map<String,Object> info = new HashMap<String,Object>();
        List<Alstab> list = alstabService.getUserRecommendMovie((Integer.parseInt(String.valueOf(userid))));
        info.put("movies", list);
        return handleResponseData(0, info);
    }
}

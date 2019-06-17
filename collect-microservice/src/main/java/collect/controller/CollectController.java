package collect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import collect.bean.Rectab;
import collect.common.exception.CommonException;
import collect.service.CollectService;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class CollectController extends BaseController{
    @Autowired
    private CollectService collectService;

    //获取Profile页面需要的参数
    @PostMapping("/lists")
    public Map<String,Object> profileGetList (final ServletRequest req){
        Object claims = req.getAttribute("claims");
        if(claims == null) {
            throw new CommonException(401,"用户token验证失败，请重新登录");
        }

        Map<String,Object> info = new HashMap<String,Object>();
        Object userid = req.getAttribute("userid");
        List<Map<String, Object>> rectab =collectService.profileGetRectabList(Integer.parseInt(String.valueOf(userid)));
        info.put("rectab",rectab);
        return handleResponseData(0, info);

    }


    //添加一条收藏
    @RequestMapping("/create")
    public Map<String,Object> addRectab(final ServletRequest req,@RequestParam("movieid") int movieid){
        Object claims = req.getAttribute("claims");
        if(claims == null) {
            throw new CommonException(401,"用户token验证失败，请重新登录");
        }
        Object userid = req.getAttribute("userid");
        Rectab rectab = collectService.createRectab(Integer.parseInt(String.valueOf(userid)),movieid);
        return handleResponseData(0,rectab);
    }

    /**
     * 是否收藏过某电影
     * @param req
     * @param movieid
     * @return
     */
    @RequestMapping("/ifLikeMovie")
    public Map<String,Object> getLikeStatus(final ServletRequest req,@RequestParam("movieid") int movieid){
        Object claims = req.getAttribute("claims");
        if(claims == null) {
            throw new CommonException(401,"用户token验证失败，请重新登录");
        }
        Object userid = req.getAttribute("userid");
        Boolean status = collectService.MovieLikeStatus(movieid, (Integer.parseInt(String.valueOf(userid))));
        return handleResponseData(0, status);
    }
}

package movie.controller;

import movie.bean.*;
import movie.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MovieController extends BaseController{
    @Autowired
    private MovieService movieService;
    @Autowired
    private SimilarService similarService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MoviecategoryService moviecategoryService;

    /**
     * 搜索
     * @retur
     */
    @PostMapping("/serchbyname")
    public Map<String, Object> search(@RequestParam("moviename") String moviename){
        List<Movie> movies = movieService.search(moviename);

        Map<String , Object> info = new HashMap<String , Object>();
        info.put("movies", movies);
        return handleResponseData(0, info);
    }

    /**
     * 相似
     * @return
     */
    @PostMapping("/getsimilar")
    public Map<String, Object> getsimilar(@RequestParam("itemid") Integer itemid){
        List<Similartab> similars = similarService.getsimilar(itemid);

        Map<String , Object> info = new HashMap<String , Object>();
        info.put("similars", similars);
        return handleResponseData(0, info);
    }

    /**
     * 电影分类标签获取
     * @return
     */
    @GetMapping("/category")
    public Map<String, Object>  category(){
        List<Category> category_tab= categoryService.categorytab();

        Map<String , Object> info = new HashMap<String , Object>();
        info.put("category_tab", category_tab);
        return handleResponseData(0, info);
    }

    /**
     * 按照电影分类标签获取电影列表
     * @return
     */
    @PostMapping("/movielist")
    public Map<String,Object> movieList(@RequestParam("categoryid") int categoryid, @RequestParam("page") int page, @RequestParam("size") int size){
        List<Moviecategory> movieList = moviecategoryService.searchTypeList(categoryid, page, size);
        Map<String , Object> info = new HashMap<String , Object>();
        info.put("movieList", movieList);
        return handleResponseData(0, info);
    }

    /**
     * 获取电影详情
     * @return
     */
    @PostMapping("/description")
    public Map<String,Object> description(@RequestParam("movieid") int movieid){
        List<Movie> moviedescription = movieService.description(movieid);
        Map<String,Object> info = new HashMap<String,Object>();
        info.put("moviedescription",moviedescription);
        return handleResponseData(0, info);
    }
}

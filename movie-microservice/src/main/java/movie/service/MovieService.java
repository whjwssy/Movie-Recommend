package movie.service;

import movie.bean.Movie;
import movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    /*
     * 搜索
     */
    public List<Movie> search(String moviename) {
        List<Movie> list = null;
        list = movieRepository.findFirst10ByMovienameLikeOrderByNationDesc("%"+moviename+"%");
        if (list == null || list.size() == 0) {
            // 返回登录失败
            return null;
        }
        return list;
    }

    /*
     * 电影详情
     */
    public List<Movie> description(int movieid){
        List<Movie> list = null;
        list = movieRepository.findByMovieid(movieid);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }
}

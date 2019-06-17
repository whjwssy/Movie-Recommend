package recommend.service;

import recommend.bean.Alstab;
import recommend.repository.AlstabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class AlstabService {
    @Autowired
    private AlstabRepository alstabRepository;

    /*
     * 获取用户的推荐电影列表
     */
    public List<Alstab> getUserRecommendMovie(int userid) {
        List<Alstab> list = null;
        list = alstabRepository.findFirst10ByUseridOrderByRatingDesc(userid);
        return list;
    }

}

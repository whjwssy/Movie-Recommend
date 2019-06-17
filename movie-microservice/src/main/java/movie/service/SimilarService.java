package movie.service;

import movie.bean.Similartab;
import movie.repository.SimilarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class SimilarService {
    @Autowired
    private SimilarRepository similarRepository;

    /*
     * 搜索
     */
    public List<Similartab> getsimilar(Integer itemid) {
        List<Similartab> list = null;
        list = similarRepository.findFirst7ByItemid1OrderBySimilarDesc(itemid);
        if (list == null || list.size() == 0) {
            // 返回登录失败
            return null;
        }
        return list;
    }

}

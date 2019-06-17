package movie.repository;

import movie.bean.Similartab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimilarRepository extends JpaRepository<Similartab, Integer> {

    /**
     * 根据itemid1查询
     * @param itemid1
     * @return
     */
    public List<Similartab> findFirst7ByItemid1OrderBySimilarDesc(Integer itemid1);


}

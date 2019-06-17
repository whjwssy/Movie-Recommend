package recommend.repository;

import recommend.bean.Alstab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlstabRepository extends JpaRepository<Alstab, Integer>{

    public List<Alstab> findFirst10ByUseridOrderByRatingDesc(int userid);
}

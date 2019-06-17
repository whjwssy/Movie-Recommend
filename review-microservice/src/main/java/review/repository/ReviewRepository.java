package review.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import review.bean.Review;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review,Integer> {

    /**
     * 根据movieid查询评论逆序
     * @param movieid
     * @return
     */
    public List<Review> findByMovieidOrderByReviewidDesc(int movieid);

    public List<Review> findByUseridOrderByReviewidDesc(int userid);

}

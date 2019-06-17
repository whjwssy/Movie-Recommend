package user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import user.bean.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 根据username查询
     * @param username
     * @return
     */
    public List<User> findByUsername(String username);

    /**
     * 根据userid查询
     * @param userid
     * @return
     */
    public List<User> findByUserid(int userid);

    @Modifying
    @Query(value = "update User u set u.password = ?1 where u.userid = ?2")
    int modifyPasswordByUserid(String password, int userid);
}

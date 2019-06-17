package recommend.bean;

import com.alibaba.fastjson.JSON;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicUpdate
@DynamicInsert
public class Alstab {
    //自增ID
    @Id
    @GeneratedValue
    private Integer id;

    private Integer userid;

    @Column(name="movieid",insertable=false,updatable=false)
    private Integer movieid;

    private Double rating;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="movieid", referencedColumnName =  "movieid")
    private Movie movie;

    //需要声明无参数的构造函数
    public Alstab(){  }
    public Integer getUserid() {
        return userid;
    }
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer userid) {
        this.id = id;
    }

    public Integer getMovieId() {
        return movieid;
    }
    public void setMovieId(Integer movieid) {
        this.movieid = movieid;
    }

    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

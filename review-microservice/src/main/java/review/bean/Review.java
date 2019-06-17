package review.bean;


import com.alibaba.fastjson.JSON;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@DynamicUpdate
@DynamicInsert

public class Review {

    @Id
    @GeneratedValue
    private Integer reviewid;
    private Integer userid;

    private Integer movieid;

    private String content;
    private Double star;
    private Date reviewtime;

    public Review(){}

    public Integer getReviewid() {
        return reviewid;
    }

    public Integer getUserid() {
        return userid;
    }

    public Integer getMovieid() {
        return movieid;
    }

    public String getContent() {
        return content;
    }

    public Double getStar() {
        return star;
    }

    public String getReviewtime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return reviewtime != null ? sdf.format(reviewtime) : "";
    }

    public void setReviewid(Integer reviewid) {
        this.reviewid = reviewid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public void setMovieid(Integer movieid) {
        this.movieid = movieid;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStar(Double star) {
        this.star = star;
    }

    public void setReviewtime(Date reviewtime) {
        this.reviewtime = reviewtime;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}

package movie.bean;

import com.alibaba.fastjson.JSON;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
@DynamicInsert
public class Similartab {

    //自增ID
    @Id
    @GeneratedValue
    private Integer id;
    private Integer itemid1;

    @Column(name="itemid2",insertable=false,updatable=false)
    private Integer itemid2;

    private Double similar;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="itemid2", referencedColumnName =  "movieid")
    private Movie movie;

    public Similartab() {}

    public Double getSimilar() {
        return similar;
    }

    public Integer getItemid1() {
        return itemid1;
    }



    public Integer getItemid2() {
        return itemid2;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }


    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}

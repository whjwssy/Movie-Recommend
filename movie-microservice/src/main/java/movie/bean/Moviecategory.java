package movie.bean;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
@DynamicInsert

public class Moviecategory {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer movcatid;

    @Column(name="movieid",insertable=false,updatable=false)
    private Integer movieid;

    private Integer categoryid;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movieid",referencedColumnName = "movieid")
    private Movie movie;

    public Integer getMovcatid() {
        return movcatid;
    }

    public void setMovcatid(Integer movcatid) {
        this.movcatid = movcatid;
    }

    public Integer getMovieid() {
        return movieid;
    }

    public void setMovieid(Integer movieid) {
        this.movieid = movieid;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Movie getMovie() {
        return movie;
    }
}

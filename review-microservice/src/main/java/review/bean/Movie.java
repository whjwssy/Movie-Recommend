package review.bean;

import com.alibaba.fastjson.JSON;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Movie {
    private Integer movieid;

    private String moviename;

    private Double averating;

    private String director;


    private Date showyear;

    private String picture;

    private Integer numrating;

    private String nation;

    private String leadactors;

    private String screenwriter;

    private String description;

    private String typelist;

    private String backpost;

    //需要声明无参数的构造函数
    public Movie(){  }

    public Integer getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid){
        this.movieid = movieid;
    }

    public void setMoviename(String moviename){
        this.moviename = moviename;
    }

    public void setAverating(double averating) {
        this.averating = averating;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setShowyear(Date showyear) {
        this.showyear = showyear;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setNumrating(int numrating) {
        this.numrating = numrating;
    }

    public String getMoviename() {
        return moviename;
    }

    public Integer getNumrating() {
        return numrating;
    }

    public String getShowyear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return showyear != null ? sdf.format(showyear) : "";
    }

    public String getPicture() {
        return picture;
    }

    public Double getAverating() {
        return averating;
    }

    public String getDirector() {
        return director;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }
    public String getLeadactors() {
        return leadactors;
    }

    public void setLeadactors(String leadactors) {
        this.leadactors = leadactors == null ? null : leadactors.trim();
    }

    public String getScreenwriter() {
        return screenwriter;
    }

    public void setScreenwriter(String screenwriter) {
        this.screenwriter = screenwriter == null ? null : screenwriter.trim();
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.replace("\"","\'") == null ? null : description.replace("\"","\'").trim();
    }

    public String getTypelist() {
        return typelist;
    }

    public void setTypelist(String typelist) {
        this.typelist = typelist == null ? null : typelist.trim();
    }

    public String getBackpost() {
        return backpost;
    }

    public void setBackpost(String backpost) {
        this.backpost = backpost == null ? null : backpost.trim();
    }


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

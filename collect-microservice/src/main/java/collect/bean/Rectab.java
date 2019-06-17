package collect.bean;

import com.alibaba.fastjson.JSON;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
@DynamicInsert
public class Rectab{
    //自增ID


    @Id
    @GeneratedValue
    private Integer rectabid;

    private Integer movieid;

    private Integer userid;

    public Integer getRectabid(){
        return rectabid;
    }

    public void setRectabid(Integer rectabid){
        this.rectabid=rectabid;
    }

    public Integer getMovieid(){
        return movieid;
    }

    public void setMovieid(Integer movieid){
        this.movieid=movieid;
    }

    public Integer getUserid(){
        return userid;
    }

    public void setUserid(Integer userid){
        this.userid=userid;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}

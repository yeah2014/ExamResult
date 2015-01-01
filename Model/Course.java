import java.io.Serializable;

/**
 * Created by hongyeah on 2014/12/21.
 */
public class Course  implements Serializable{
    public String WhichCourse;
    public Double Score;
    public String IDandname;
    public Course() {
    }

    public Course(String IDandname , Object score)
    {
        this.IDandname = IDandname;
        this.Score = (Double)score;
    }
    public String getWhichCourse() {
        return WhichCourse;
    }

    public void setWhichCourse(String whichCourse) {
        WhichCourse = whichCourse;
    }

    public Double getScore() {
        return Score;
    }

    public void setScore(Double score) {
        Score = score;
    }



}
